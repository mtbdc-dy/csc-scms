package gov.gwssi.csc.scms.service.scholarship;

import gov.gwssi.csc.scms.dao.scholarshipX.ScholarshipXDAO;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.ScholarshipXResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipXRepository;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipXRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.abnormal.NoSuchAbnormalException;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gc on 2015/7/24.
 * 奖学金评审服务类
 */
@Service("scholarshipXService")
public class ScholarshipXService extends BaseService {
    @Autowired
    @Qualifier("scholarshipXRepository")
    private ScholarshipXRepository scholarshipXRepository;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private ScholarshipXDAO scholarshipXDAO;
    //生成奖学金评审清单
    public List<ScholarshipXResultObject> getScholarshipXList(User user) {
        List listParameter = new ArrayList();
        List<ScholarshipXResultObject> ScholarshipXResultObjectList;
        listParameter.add(user.getUserId());//传入userid
        scholarshipXDAO.doSt("p_scms_scholarship", listParameter);//调用存储生成当年需评审的奖学金记录
        int startPosition, pageSize;

        String sql = getSql(user);
        if (sql == null) {
            return null;
        }
            startPosition =FilterObject.OFFSETDEFULT;
            pageSize =FilterObject.PAGESIZEDEFULT;

        ScholarshipXResultObjectList = super.getBaseDao().getObjectListByHQL(sql, ScholarshipXResultObject.class, startPosition, pageSize);
        return ScholarshipXResultObjectList;

    }
    //查询获取机票管理列表
    public List<ScholarshipXResultObject> getScholarshipXListByFilter(FilterObject filterObject,User user) {

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
            startPosition =FilterObject.OFFSETDEFULT;
            pageSize =FilterObject.PAGESIZEDEFULT;
        }

        ScholarshipXResultObjectList = super.getBaseDao().getObjectListByHQL(sql, ScholarshipXResultObject.class, startPosition, pageSize);
        return ScholarshipXResultObjectList;


    }
    //获取当前用户下的奖学金评审对应的字段数据 不加查询条件的sql
    private String getSql(User user) {
        StringBuilder sb = new StringBuilder();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        int year=ts.getYear()+1900;
        sb.append(ScholarshipXResultObject.getResultObject());
        String tempSql = " from ScholarshipX ScholarshipX,Student student,BasicInfo basicInfo, SchoolRoll schoolRoll" +
                " where student.id = basicInfo.student " +
                "and student.id = schoolRoll.student   and student.id = ScholarshipX.studentId and ScholarshipX.year='"+year+"'";//默认进来查询当年的
        sb.append(tempSql);
        return sb.toString();
    }
    //获取奖学金评审列表对应的字段数据
    private String getSqlByBody(FilterObject filterObject, User user) {
        if (filterObject == null)
            return null;
        StringBuilder sb = new StringBuilder();
        sb.append(ScholarshipXResultObject.getResultObject());
        String tempSql = " from ScholarshipX ScholarshipX, Student student,BasicInfo basicInfo, SchoolRoll schoolRoll " +
                " where student.id = basicInfo.student " +
                "and student.id = schoolRoll.student   and student.id = ScholarshipX.studentId ";
        sb.append(tempSql);
        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter(user, "scholarshipX", ""));
        return sb.toString();
    }

    public ScholarshipX findOne(String id) {
        return scholarshipXRepository.findOne(id);
    }

    public Iterable<ScholarshipX> findAll() {
        return scholarshipXRepository.findAll();
    }
//    //保存新增的保险记录
//    @Transactional
//    public String saveScholarshipX(ScholarshipX scholarshipX, List<OperationLog> operationLogs) {
//        //记录日志
//        operationLogService.saveOperationLog(operationLogs);
//        scholarshipX.setId(getBaseDao().getIdBySequence("SEQ_INSURANCE"));
//        scholarshipXRepository.save(scholarshipX);
//        return scholarshipX.getId();
//    }
//    // 根据id查询scholarshipXAndStu
//    public ScholarshipXResultObject getScholarshipXAndStu(String id) throws Exception{
//        //返回界面包含学生信息 根据保险id查出
//        StringBuilder sb = new StringBuilder();
//        sb.append(ScholarshipXResultObject.getResultObject());
//        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll, ScholarshipX ScholarshipX " +
//                "where student.id = basicInfo.student  " +
//                "and student.id = schoolRoll.student and student.id = ScholarshipX.studentId  and  ScholarshipX.insurSta ='1'";//正式的
//        sb.append(tempSql);
//        sb.append(" and ScholarshipX.id = '").append(id).append("'");
//        List<ScholarshipXResultObject> scholarshipXList = super.getBaseDao().getObjectListByHQL(sb.toString(), ScholarshipXResultObject.class, 0, 1);
//        ScholarshipXResultObject scholarshipXResultObject = null;
//        if(null == scholarshipXList || scholarshipXList.size()==0){
//            throw new NoSuchAbnormalException("cannot find the scholarshipX, please refresh the page!" );
//        }else{
//            scholarshipXResultObject = scholarshipXList.get(0);
//        }
//        return scholarshipXResultObject;
//    }
//    public ScholarshipX getScholarshipXById(String id) {
//        return scholarshipXRepository.findById(id);
//    }
//    //删除保险记录
//    public ScholarshipX deleteScholarshipXById(String id, List<OperationLog> operationLogs) {
//        ScholarshipX scholarshipX = getScholarshipXById(id);
//        if (scholarshipX == null)
//            return null;
//        //记录日志
//        operationLogService.saveOperationLog(operationLogs);
//        scholarshipXRepository.delete(scholarshipX);
//        return scholarshipX;
//    }

    }
