package gov.gwssi.csc.scms.service.scholarship;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.dao.scholarshipX.ScholarshipXDAO;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.ScholarshipXResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.scholarship.Scholarship;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipDetail;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipDetailRepository;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipRepository;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipXRepository;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by gc on 2015/7/24.
 * 奖学金评审服务类
 */
@Service("scholarshipXService")
public class ScholarshipXService extends ScholarshipXSpecs {
    @Autowired
    @Qualifier("scholarshipDetailRepository")
    private ScholarshipDetailRepository scholarshipDetailRepository;
    @Autowired
    @Qualifier("scholarshipRepository")
    private ScholarshipRepository scholarshipRepository;
    @Autowired
    @Qualifier("scholarshipXRepository")
    private ScholarshipXRepository scholarshipXRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private ScholarshipJService scholarshipJService;
    @Autowired
    private ScholarshipXDAO scholarshipXDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private BaseDAO baseDAO;

    //生成奖学金评审清单
    public Map<String,String> getScholarshipXList(User user) {

        List listParameter = new ArrayList();
        List<ScholarshipXResultObject> ScholarshipXResultObjectList;
        listParameter.add(user.getUserId());//传入userid
        scholarshipXDAO.doSt("p_scms_scholarship", listParameter);//调用存储生成当年需评审的奖学金记录
        int startPosition, pageSize;

        String sql = getSql(user);
        if (sql == null) {
            return null;
        }
        startPosition = FilterObject.OFFSETDEFULT;
        pageSize = FilterObject.PAGESIZEDEFULT;

        ScholarshipXResultObjectList = super.getBaseDao().getObjectListByHQL(sql, ScholarshipXResultObject.class, startPosition, pageSize);

        List<OperationLog> operationLogs = new ArrayList<OperationLog>();
        for (int i = 0; i < ScholarshipXResultObjectList.size(); i++) {//每个学生插入一条日志
            if (ScholarshipXResultObjectList.get(i).getSchReason() == null
                    && ScholarshipXResultObjectList.get(i).getSchStartTime() == null &&
                    ScholarshipXResultObjectList.get(i).getSchEndTime() == null) {//三项全为空的才插入
                OperationLog operationLog = new OperationLog();
                operationLog.setOptType("1");//新增
                operationLog.setModule("奖学金年度评审管理");
                operationLog.setModuleId("BG0006");
                operationLog.setStudentId(ScholarshipXResultObjectList.get(i).getStudentId());
                operationLog.setCscId(ScholarshipXResultObjectList.get(i).getCscId());
                operationLog.setPassportName(ScholarshipXResultObjectList.get(i).getPassportName());
                String after = baseDAO.getNameCHByTranslateId(ScholarshipXResultObjectList.get(i).getSchReview()) + "/" + baseDAO.getNameCHByTranslateId(ScholarshipXResultObjectList.get(i).getSchResult()) + "/" + "-" + "/" + "-" + "/" + "-";
                operationLog.setBefore("");
                operationLog.setAfter(after);
                operationLog.setColumnEN("");
                operationLog.setColumnCH("");
                operationLog.setTableEN("scms_scholarship_detail");
                operationLog.setTableCH("奖学金评审信息表");
                operationLog.setNodeId(user.getNode().getNodeId());
                operationLog.setNodeType(user.getNode().getNodeType());
                operationLog.setCreateBy(user.getUserId());
                operationLog.setCreateD(new java.util.Date());
                operationLogs.add(operationLog);
            }

        }
        operationLogService.saveOperationLog(operationLogs);//保存日志
        Map<String,String> result = new HashMap<String, String>();
        result.put("result","success");
        return result;

    }

    //查询获取奖学金管理列表--学校用户
    public List<ScholarshipXResultObject> getScholarshipXListByFilter(FilterObject filterObject, User user) {

        List<ScholarshipXResultObject> ScholarshipXResultObjectList;
        int startPosition, pageSize;

        String sql = getSqlByBody(filterObject, user);
        if (sql == null) {
            return null;
        }

        try {
            startPosition = Integer.parseInt(filterObject.getOffSet());
            pageSize = Integer.parseInt(filterObject.getPageSize());
        } catch (NumberFormatException ne) {
            ne.printStackTrace();
            startPosition = FilterObject.OFFSETDEFULT;
            pageSize = FilterObject.PAGESIZEDEFULT;
        }

        ScholarshipXResultObjectList = super.getBaseDao().getObjectListByHQL(sql, ScholarshipXResultObject.class, startPosition, pageSize);
        return ScholarshipXResultObjectList;


    }

    //查询获取奖学金管理列表--基金委用户
    public List<ScholarshipXResultObject> getScholarshipXListByFilterJ(FilterObject filterObject, User user, String school) {

        List<ScholarshipXResultObject> ScholarshipXResultObjectList;
        int startPosition, pageSize;

        String sql = getSqlByBodyJ(filterObject, user, school);
        if (sql == null) {
            return null;
        }

        try {
            startPosition = Integer.parseInt(filterObject.getOffSet());
            pageSize = Integer.parseInt(filterObject.getPageSize());
        } catch (NumberFormatException ne) {
            ne.printStackTrace();
            startPosition = FilterObject.OFFSETDEFULT;
            pageSize = FilterObject.PAGESIZEDEFULT;
        }

        ScholarshipXResultObjectList = super.getBaseDao().getObjectListByHQL(sql, ScholarshipXResultObject.class, startPosition, pageSize);
        return ScholarshipXResultObjectList;


    }

    //根据cscId查询奖学金记录
    public List<ScholarshipXResultObject> getScholarshipXListcscId(String cscId) {
        List<ScholarshipXResultObject> ScholarshipXResultObjectList;
        int startPosition, pageSize;

        String sql = getSqlbycscid(cscId);
        if (sql == null) {
            return null;
        }
        startPosition = FilterObject.OFFSETDEFULT;
        pageSize = FilterObject.PAGESIZEDEFULT;

        ScholarshipXResultObjectList = super.getBaseDao().getObjectListByHQL(sql, ScholarshipXResultObject.class, startPosition, pageSize);
        return ScholarshipXResultObjectList;

    }

    //根据cscId查询奖学金记录
    private String getSqlbycscid(String cscId) {
        StringBuilder sb = new StringBuilder();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
//        int year = ts.getYear() + 1900;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        sb.append(ScholarshipXResultObject.getResultObject());
        String tempSql = " from ScholarshipX ScholarshipX,Student student,BasicInfo basicInfo, SchoolRoll schoolRoll" +
                " where student.id = basicInfo.student " +
                "and student.id = schoolRoll.student   and student.id = ScholarshipX.student.id and ScholarshipX.cscSta='1' ";
        sb.append(tempSql);
        sb.append(" and ScholarshipX.cscId = '").append(cscId).append("'");
        return sb.toString();
    }

    //获取当前用户下的奖学金评审对应的字段数据 不加查询条件的sql
    private String getSql(User user) {
        StringBuilder sb = new StringBuilder();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String schoolId = user.getNode().getNodeId();
//        int year = ts.getYear() + 1900;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        sb.append(ScholarshipXResultObject.getResultObject());
        String tempSql = " from ScholarshipX ScholarshipX,Student student,BasicInfo basicInfo, SchoolRoll schoolRoll" +
                " where student.id = basicInfo.student " +
                "and student.id = schoolRoll.student   and student.id = ScholarshipX.student.id and ScholarshipX.year='" + year + "'";//默认进来查询当年的
        sb.append(tempSql);
        sb.append(" and ScholarshipX.school = '").append(schoolId).append("'");
        return sb.toString();
    }

    //获取奖学金评审列表对应的字段数据--学校用户
    private String getSqlByBody(FilterObject filterObject, User user) {
        if (filterObject == null)
            return null;
        StringBuilder sb = new StringBuilder();
        String schoolId = user.getNode().getNodeId();
        sb.append(ScholarshipXResultObject.getResultObject());
        String tempSql = " from ScholarshipX ScholarshipX, Student student,BasicInfo basicInfo, SchoolRoll schoolRoll " +
                " where student.id = basicInfo.student " +
                "and student.id = schoolRoll.student   and student.id = ScholarshipX.student.id ";
        sb.append(tempSql);
        sb.append(" and ScholarshipX.school = '").append(schoolId).append("'");
        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter(user, "ScholarshipX", ""));
        return sb.toString();
    }

    //获取奖学金评审列表对应的字段数据--基金委用户
    private String getSqlByBodyJ(FilterObject filterObject, User user, String school) {
        if (filterObject == null)
            return null;
        StringBuilder sb = new StringBuilder();
        sb.append(ScholarshipXResultObject.getResultObject());
        String tempSql = " from ScholarshipX ScholarshipX, Student student,BasicInfo basicInfo, SchoolRoll schoolRoll " +
                " where student.id = basicInfo.student " +
                "and student.id = schoolRoll.student   and student.id = ScholarshipX.student.id ";
        sb.append(tempSql);
        sb.append(" and ScholarshipX.school = '").append(school).append("'");
        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter(user, "ScholarshipX", ""));
        return sb.toString();
    }

    //保存修改后奖学金字表的值
    @Transactional
    public String saveScholarshipDetail(ScholarshipDetail scholarshipDetail, User user) {
        //记录日志
        List<OperationLog> operationLogs = new ArrayList<OperationLog>();
        ScholarshipX scholarshipX = findOne(scholarshipDetail.getId());
        OperationLog operationLog = new OperationLog();
        operationLog.setOptType("2");//修改
        operationLog.setModule("奖学金年度评审管理");
        operationLog.setModuleId("BG0006");
        operationLog.setStudentId(scholarshipDetail.getStudent().getId());
        operationLog.setCscId(scholarshipX.getCscId());
        operationLog.setPassportName(scholarshipX.getPassportName());
        //区分基金委用户还是学校用户
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//日期格式化
        String before = "";
        String after = "";
        if (user.getUserType().equals("1")) {//基金委用户
            String reasonB = (scholarshipX.getCscReason() != null) ? scholarshipX.getCscReason() : "-";//对null的情况进行转化
            String reasonA = (scholarshipDetail.getCscReason() != null) ? scholarshipDetail.getCscReason() : "-";//对null的情况进行转化
            String sTB = (scholarshipX.getCscStartTime() != null) ? formatter.format(scholarshipX.getCscStartTime()) : "-";
            String sTA = (scholarshipDetail.getCscStartTime() != null) ? formatter.format(scholarshipDetail.getCscStartTime()) : "-";
            String eTB = (scholarshipX.getCscEndTime() != null) ? formatter.format(scholarshipX.getCscEndTime()) : "-";
            String eTA = (scholarshipDetail.getCscEndTime() != null) ? formatter.format(scholarshipDetail.getCscEndTime()) : "-";
            before = baseDAO.getNameCHByTranslateId(scholarshipX.getCscReview()) + "/" + baseDAO.getNameCHByTranslateId(scholarshipX.getCscResult()) + "/" + reasonB + "/" + sTB + "/" + eTB;
            after = baseDAO.getNameCHByTranslateId(scholarshipDetail.getCscReview()) + "/" + baseDAO.getNameCHByTranslateId(scholarshipDetail.getCscResult()) + "/" + reasonA + "/" + sTA + "/" + eTA;
        } else if (user.getUserType().equals("2")) {//学校用户
            String reasonB = (scholarshipX.getSchReason() != null) ? scholarshipX.getSchReason() : "-";//对null的情况进行转化
            String reasonA = (scholarshipDetail.getSchReason() != null) ? scholarshipDetail.getSchReason() : "-";//对null的情况进行转化
            String sTB = (scholarshipX.getSchStartTime() != null) ? formatter.format(scholarshipX.getSchStartTime()) : "-";
            String sTA = (scholarshipDetail.getSchStartTime() != null) ? formatter.format(scholarshipDetail.getSchStartTime()) : "-";
            String eTB = (scholarshipX.getSchEndTime() != null) ? formatter.format(scholarshipX.getSchEndTime()) : "-";
            String eTA = (scholarshipDetail.getSchEndTime() != null) ? formatter.format(scholarshipDetail.getSchEndTime()) : "-";
            before = baseDAO.getNameCHByTranslateId(scholarshipX.getSchReview()) + "/" + baseDAO.getNameCHByTranslateId(scholarshipX.getSchResult()) + "/" + reasonB + "/" + sTB + "/" + eTB;
            after = baseDAO.getNameCHByTranslateId(scholarshipDetail.getSchReview()) + "/" + baseDAO.getNameCHByTranslateId(scholarshipDetail.getSchResult()) + "/" + reasonA + "/" + sTA + "/" + eTA;

        }

        if (!before.equals(after)) {//如果有修改before和after不一致，才进行日志的插入
            operationLog.setBefore(before);
            operationLog.setAfter(after);
            operationLog.setColumnEN("");
            operationLog.setColumnCH("");
            operationLog.setTableEN("scms_scholarship_detail");
            operationLog.setTableCH("奖学金评审信息表");
            operationLog.setNodeId(user.getNode().getNodeId());
            operationLog.setNodeType(user.getNode().getNodeType());
            operationLog.setCreateBy(user.getUserId());
            operationLog.setCreateD(new java.util.Date());
            operationLogs.add(operationLog);
            operationLogService.saveOperationLog(operationLogs);//保存日志
        }

        scholarshipDetailRepository.save(scholarshipDetail);
        return scholarshipDetail.getId();
    }

    //更新主表信息
    @Transactional
    public String saveScholarship(Scholarship scholarship, User user) {
        scholarshipRepository.save(scholarship);
        return scholarship.getId();
    }

    //更新主表信息,提交时，记录日志
    @Transactional
    public String saveScholarship(Scholarship scholarship, User user, String type) {
        //记录日志，以主表id找到所有对应字表中的学生，循环记录日志
        List detailList = scholarshipJService.findDetailListBy(scholarship.getId());//根据主表字段找到所有字表记录
        List<OperationLog> operationLogs = new ArrayList<OperationLog>();
        for (int j = 0; j < detailList.size(); j++) {//以主表id找到所有对应字表中的学生，循环记录日志
            HashMap strD = (HashMap) detailList.get(j);
            OperationLog operationLog = new OperationLog();
            operationLog.setOptType("2");//修改
            operationLog.setModule("奖学金年度评审管理");
            operationLog.setModuleId("BG0006");
            ScholarshipX scholarshipX = findOne((String) strD.get("ID"));
//            operationLog.setStudentId(scholarshipX.getStudent().getId());
            operationLog.setStudentId((String) strD.get("STUDENTID"));
            operationLog.setCscId(scholarshipX.getCscId());
            operationLog.setPassportName(scholarshipX.getPassportName());
            if (type.equals("1")) {//提交
                operationLog.setBefore("未提交");
                operationLog.setAfter("已提交");
                operationLog.setColumnEN("schoolSta");
            } else {//批复
                operationLog.setBefore("未批复");
                operationLog.setAfter("已批复");
                operationLog.setColumnEN("schSta");
            }
            operationLog.setColumnCH("状态");
            operationLog.setTableEN("scms_scholarship_detail");
            operationLog.setTableCH("奖学金评审信息表");
            operationLog.setNodeId(user.getNode().getNodeId());
            operationLog.setNodeType(user.getNode().getNodeType());
            operationLog.setCreateBy(user.getUserId());
            operationLog.setCreateD(new java.util.Date());
            operationLogs.add(operationLog);
        }

        operationLogService.saveOperationLog(operationLogs);//保存日志
        scholarshipRepository.save(scholarship);
        return scholarship.getId();
    }

    public ScholarshipDetail getScholarshipDetailById(String id) {
        return scholarshipDetailRepository.findById(id);
    }

    public ScholarshipX findOne(String id) {
        return scholarshipXRepository.findOne(id);
    }

    public Scholarship findScholarshipOne(String id) {
        return scholarshipRepository.findById(id);
    }

    public Iterable<ScholarshipDetail> findScholarshipDetailAll() {//找到所有子表记录
        return scholarshipDetailRepository.findAll();
    }


    public Iterable<ScholarshipX> findScholarshipXAll() {//找到所有视图记录
        return scholarshipXRepository.findAll();
    }

    public List<ScholarshipX> findScholarshipXBySchoolAndYearAndSchReview(String school,int year,String schReview) {//找到所有视图记录
        long yearC = Long.valueOf(year);
        return scholarshipXRepository.findBySchoolAndYearAndSchReview(school, year, schReview);
    }
    public List<ScholarshipX> findScholarshipXBySchoolAndYear(String school,int year) {//找到所有视图记录
        long yearC = Long.valueOf(year);
        return scholarshipXRepository.findBySchoolAndYear(school, year);
    }

    //保存新增的奖学金详细记录,保存日志
//    @Transactional
    public String savenewScholarshipDetail(ScholarshipDetail scholarshipDetail, User user) {


        scholarshipDetail.setId(getBaseDao().getIdBySequence("seq_scholarship_detail"));
        scholarshipDetailRepository.save(scholarshipDetail);
        //记录日志
        List<OperationLog> operationLogs = new ArrayList<OperationLog>();
        Student student = null;
        try {
            student = studentService.getStudentById(scholarshipDetail.getStudent().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        OperationLog operationLog = new OperationLog();
        operationLog.setOptType("1");//新增
        operationLog.setModule("奖学金年度评审管理");
        operationLog.setModuleId("BG0006");
        operationLog.setStudentId(scholarshipDetail.getStudent().getId());
        operationLog.setCscId(student.getCscId());
        operationLog.setPassportName(student.getBasicInfo().getPassportName());
        String after = baseDAO.getNameCHByTranslateId(scholarshipDetail.getSchReview()) + "/" + baseDAO.getNameCHByTranslateId(scholarshipDetail.getSchResult()) + "/" + "-" + "/" + "-" + "/" + "-";
        operationLog.setBefore("");
        operationLog.setAfter(after);
        operationLog.setColumnEN("");
        operationLog.setColumnCH("");
        operationLog.setTableEN("scms_scholarship_detail");
        operationLog.setTableCH("奖学金评审信息表");
        operationLog.setNodeId(user.getNode().getNodeId());
        operationLog.setNodeType(user.getNode().getNodeType());
        operationLog.setCreateBy(user.getUserId());
        operationLog.setCreateD(new java.util.Date());
        operationLogs.add(operationLog);
        operationLogService.saveOperationLog(operationLogs);//保存日志
        return scholarshipDetail.getId();
    }

    // 根据id查询scholarshipXAndStu
    public ScholarshipXResultObject getScholarshipXAndStu(String id) throws Exception {
        //返回界面包含学生信息 根据保险id查出
        StringBuilder sb = new StringBuilder();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
//        int year = ts.getYear() + 1900;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        sb.append(ScholarshipXResultObject.getResultObject());
        String tempSql = " from ScholarshipX ScholarshipX,Student student,BasicInfo basicInfo, SchoolRoll schoolRoll" +
                " where student.id = basicInfo.student " +
                "and student.id = schoolRoll.student   and student.id = ScholarshipX.student.id and ScholarshipX.year='" + year + "'";//默认进来查询当年的
        sb.append(tempSql);
        sb.append(" and ScholarshipX.id = '").append(id).append("'");
        List<ScholarshipXResultObject> scholarshipXList = super.getBaseDao().getObjectListByHQL(sb.toString(), ScholarshipXResultObject.class, 0, 1);
        ScholarshipXResultObject scholarshipXResultObject = null;
        if (null == scholarshipXList || scholarshipXList.size() == 0) {
            return null;
        } else {
            scholarshipXResultObject = scholarshipXList.get(0);
        }
        return scholarshipXResultObject;
    }

    // 根据studentId查询上scholarshipXAndStu
    public ScholarshipXResultObject getScholarshipXAndStuBy(String studentId) throws Exception {
        //返回界面包含学生信息 根据保险id查出
        StringBuilder sb = new StringBuilder();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
//        int year = ts.getYear() + 1900;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        sb.append(ScholarshipXResultObject.getResultObject());
        String tempSql = " from ScholarshipX ScholarshipX,Student student,BasicInfo basicInfo, SchoolRoll schoolRoll" +
                " where student.id = basicInfo.student " +
                "and student.id = schoolRoll.student   and student.id = ScholarshipX.student.id and ScholarshipX.year='" + (year - 1) + "'";
        sb.append(tempSql);
        sb.append(" and ScholarshipX.student.id = '").append(studentId).append("'");
        List<ScholarshipXResultObject> scholarshipXList = super.getBaseDao().getObjectListByHQL(sb.toString(), ScholarshipXResultObject.class, 0, 1);
        ScholarshipXResultObject scholarshipXResultObject = null;
        if (null == scholarshipXList || scholarshipXList.size() == 0) {
            return null;
        } else {
            scholarshipXResultObject = scholarshipXList.get(0);
        }
        return scholarshipXResultObject;
    }

    //删除奖学金子表记录
    public ScholarshipDetail deleteScholarshipDetailById(User user, String id) {
        ScholarshipDetail scholarshipDetail = getScholarshipDetailById(id);
        if (scholarshipDetail == null)
            return null;
        //记录日志
        List<OperationLog> operationLogs = new ArrayList<OperationLog>();
        ScholarshipX scholarshipX = findOne(id);
        OperationLog operationLog = new OperationLog();
        operationLog.setOptType("3");
        operationLog.setModule("奖学金年度评审管理");
        operationLog.setModuleId("BG0006");
        operationLog.setStudentId(scholarshipDetail.getStudent().getId());
        operationLog.setCscId(scholarshipX.getCscId());
        operationLog.setPassportName(scholarshipX.getPassportName());
        String reason = (scholarshipX.getSchReason() != null) ? scholarshipX.getSchReason() : "-";//对null的情况进行转化
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//日期格式化
        String sTB = (scholarshipX.getSchStartTime() != null) ? formatter.format(scholarshipX.getSchStartTime()) : "-";
        String eTB = (scholarshipX.getSchEndTime() != null) ? formatter.format(scholarshipX.getSchEndTime()) : "-";
        String before = baseDAO.getNameCHByTranslateId(scholarshipX.getSchReview()) + "/" + baseDAO.getNameCHByTranslateId(scholarshipX.getSchResult()) + "/" + reason + "/" + sTB + "/" + eTB;
        operationLog.setBefore(before);
        operationLog.setAfter("");
        operationLog.setColumnEN("");
        operationLog.setColumnCH("");
        operationLog.setTableEN("scms_scholarship_detail");
        operationLog.setTableCH("奖学金评审信息表");
        operationLog.setNodeId(user.getNode().getNodeId());
        operationLog.setNodeType(user.getNode().getNodeType());
        operationLog.setCreateBy(user.getUserId());
        operationLog.setCreateD(new java.util.Date());
        operationLogs.add(operationLog);
        operationLogService.saveOperationLog(operationLogs);//保存日志

        scholarshipDetailRepository.delete(scholarshipDetail);//删除记录
        return scholarshipDetail;
    }

    //分页查询
    @Transactional
    public Page<ScholarshipX> getScholarshipXsPagingByFilter(Filter filter,Integer page,Integer size,String mode,String header) {
        try {
            User user = userService.getUserByJWT(header);
            String school = user.getNode().getNodeId();

            ///////////!!!!IMPORTANT!!!!//////////////
            if (filter.getYear() == 0)
                filter.setYear(Calendar.getInstance().get(Calendar.YEAR));

            List<ScholarshipX> list = scholarshipXRepository.findBySchoolAndYear(school, filter.getYear());
            if(list != null && list.size()>0){
                ScholarshipX scholarship = list.get(0);
                filter.setSchoolState(scholarship.getSchoolSta());
            }
            ///////////!!!!IMPORTANT!!!!//////////////

            Specification<ScholarshipX> specA = filterIsLike(filter, user, school);
            Specification<ScholarshipX> specB = userIs(user,baseDAO);
            return scholarshipXRepository.findAll(where(specA).and(specB), new PageRequest(page, size, Sort.Direction.ASC, "cscId"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //增加全部导出
    @Transactional
    public String[] getAllScholarshipXByFilter(Filter filter,String header) {
        List<ScholarshipX> scholarshipXes;
        try {
            User user = userService.getUserByJWT(header);
            String school = user.getNode().getNodeId();

            ///////////!!!!IMPORTANT!!!!//////////////
            if (filter.getYear() == 0)
                filter.setYear(Calendar.getInstance().get(Calendar.YEAR));

            List<ScholarshipX> list = scholarshipXRepository.findBySchoolAndYear(school, filter.getYear());
            if(list != null && list.size()>0){
                ScholarshipX scholarship = list.get(0);
                filter.setSchoolState(scholarship.getSchoolSta());
            }
            ///////////!!!!IMPORTANT!!!!//////////////

            Specification<ScholarshipX> specA = filterIsLike(filter, user, school);
            Specification<ScholarshipX> specB = userIs(user,baseDAO);
            scholarshipXes = scholarshipXRepository.findAll(where(specA).and(specB),new Sort(Sort.Direction.ASC,"cscId"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String result[]=new String[scholarshipXes.size()];
        for(int i=0;i<scholarshipXes.size();i++){
            String id = scholarshipXes.get(i).getId();
            result[i] = id;
        }
        return result;
    }

    //分页查询
    @Transactional
    public Page<ScholarshipX> getScholarshipXsPagingByFilterJ(Filter filter,Integer page,Integer size,String mode,String header,String school) {
            try {
                User user = userService.getUserByJWT(header);
                Specification<ScholarshipX> specA = filterIsLike(filter, user, school);
                Specification<ScholarshipX> specB = userIs(user,baseDAO);
                return scholarshipXRepository.findAll(where(specA).and(specB), new PageRequest(page, size, Sort.Direction.ASC, "cscId"));
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException(e);
            }
    }

    //新增学生时首先校验该学生是否已经存在于奖学金列表中
    public Map<String,String> verifyScholarshipXStudent(String studentId){
        Map<String,String> result = new HashMap<String, String>();
        List<ScholarshipX> scholarshipXs= scholarshipXRepository.findByStudentId(studentId);
        if(scholarshipXs.size()>0){
            result.put("result","failed");
        }else{
            result.put("result","success");
        }
        return result;
    }

    public Map<String,String> getSchoolSta(String school,int year){
        String schoolSta = baseDAO.getSchoolSta(school,year);
        Map<String,String> result = new HashMap<String, String>();
        result.put("schoolSta",schoolSta);
        return result;
    }


}
