package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.insurance.Insurance;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.StudentResultObject;
import gov.gwssi.csc.scms.domain.scholarship.Scholarship;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import gov.gwssi.csc.scms.domain.student.*;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.student.StudentRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.insurance.InsuranceService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import gov.gwssi.csc.scms.utils.TablesAndColumnsMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Murray on 4/5/2015.
 * 学生业务操作类
 */
@Service(value = "studentService")
public class StudentService extends BaseService {

    @Autowired
    @Qualifier("studentRepository")
    private StudentRepository studentRepository;
    @Autowired
    private BasicInfoService basicInfoService;
    @Autowired
    private DiscussService discussService;
    @Autowired
    private RegistrationInfoService registrationInfoService;
    @Autowired
    private RelatedAddressService relatedAddressService;
    @Autowired
    private SchoolRollService schoolRollService;
    @Autowired
    private AccidentService accidentService;
    @Autowired
    private ProfilesHistoryService profilesHistoryService;
    @Autowired
    private SchoolfellowService schoolfellowService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private GradeAttachmentService gradeAttachmentService;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private InsuranceService insuranceService;

    @Transactional
    public Student getStudentById(String id) throws Exception {
        Student student = studentRepository.findOne(id);
        if (null == student) {
            throw new Exception("can not find the student with id:" + id);
        }
        return student;
    }

    @Transactional
    public Student getCompleteInfoOfStudentById(String id) throws Exception {
        Student student = getStudentById(id);

        List<ScholarshipX> scholarships = student.getScholarshipXs();
        ScholarshipX lastScholarship = new ScholarshipX();
        if (scholarships.size() > 0) {
            Collections.sort(scholarships);
            Collections.reverse(scholarships);
        }
        for(int i=0;i<scholarships.size();i++){
            if("2".equals(scholarships.get(i).getSchoolSta())){
                lastScholarship = scholarships.get(i);
                break;
            }
        }

        if (student.getSchoolRoll() != null) {
            student.getSchoolRoll().setScholarshipAnnual(lastScholarship.getYear());
            student.getSchoolRoll().setScholarshipReview(lastScholarship.getCscReview());
            student.getSchoolRoll().setScholarshipResult(lastScholarship.getCscResult());
        }

        List<Insurance> insurances = student.getInsurances();
        Collections.sort(insurances);
        Collections.reverse(insurances);



        /*!IMPORTANT!*/
        System.out.println(student.getDiscuss());
        System.out.println(student.getProfilesHistory());
        System.out.println(student.getRegistrationInfo());
        System.out.println(student.getRelatedAddress());
        System.out.println(student.getAccidents());
        System.out.println(student.getSchoolfellow());
        System.out.println(student.getGrades());
        System.out.println(student.getGradeAttachment());
        System.out.println(student.getAbnormals());
        System.out.println(student.getTickets());
        System.out.println(student.getScholarshipXs());
        System.out.println(student.getWarning());
        /*!IMPORTANT!*/

        return student.getStudent();
    }

    public Student getStudentByCscId(String scsId) {
        return studentRepository.findByCscId(scsId);
    }

    public List<StudentResultObject> getStudentsByFilter(FilterObject filterObject, User user) {
        List<StudentResultObject> studentList;
        int startPosition, pageSize;

        String sql = getSqlByBody(filterObject, user);
        System.out.println("getStudentSql===" + sql);
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

        studentList = super.getBaseDao().getObjectListByHQL(sql, StudentResultObject.class, startPosition, pageSize);
        return studentList;
    }

    public List<StudentResultObject> getSchoolStudentsByFilter(FilterObject filterObject, User user) {
        List<StudentResultObject> studentList;
        int startPosition, pageSize;
        String str = "and schoolRoll.registed = 'AX0002' and schoolRoll.leaveChina ='BA0001'";
        String sql = getSqlByBody(filterObject, user, str);
        System.out.println("getStudentSql===" + sql);
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

        studentList = super.getBaseDao().getObjectListByHQL(sql, StudentResultObject.class, startPosition, pageSize);
        return studentList;
    }

    public List<StudentResultObject> getLeaveStudentsByFilter(FilterObject filterObject, User user) {
        List<StudentResultObject> studentList;
        int startPosition, pageSize;
        String str = "and schoolRoll.registed = 'AX0002' and schoolRoll.leaveChina ='BA0002'";
        String sql = getSqlByBody(filterObject, user, str);
        System.out.println("getStudentSql===" + sql);
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

        studentList = super.getBaseDao().getObjectListByHQL(sql, StudentResultObject.class, startPosition, pageSize);
        return studentList;
    }

    public int getCountByQueryFilter(FilterObject filterObject, User user) {
        String sql = getCountSqlByBody(filterObject, user);
        if (sql == null) {
            return 0;
        }

        return super.getBaseDao().getCountObjectByHQL(sql);
    }

    private String getCountSqlByBody(FilterObject filterObject, User user) {
        if (filterObject == null) {
            return null;
        }

        String tempSql = "select count(*) " +
                "from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll " +
                "where student.basicInfo = basicInfo.student " +
                "and student.schoolRoll = schoolRoll.student ";

        // tempSql += new StudentFilter((StudentFilterObject) filterObject).getFilter(user);
        return tempSql;
    }

    private String getSqlByBody(FilterObject filterObject, User user) {
        if (filterObject == null)
            return null;

        //获取查询结果集
        String sqlStr = StudentResultObject.getResultObject();

        //添加查询实体
        sqlStr += " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll " +
                "where student.id = basicInfo.student " +
                "and student.id = schoolRoll.student ";

        //添加查询条件，并返回完整SQL语句
        return sqlStr + new StudentFilter((StudentFilterObject) filterObject).getFilter(user, "", "");
    }

    private String getSqlByBody(FilterObject filterObject, User user, String str) {
        if (filterObject == null)
            return null;

        //获取查询结果集
        String sqlStr = StudentResultObject.getResultObject();

        //添加查询实体
        sqlStr += " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll " +
                "where student.id = basicInfo.student " +
                "and student.id = schoolRoll.student " + str;

        //添加查询条件，并返回完整SQL语句
        return sqlStr + new StudentFilter((StudentFilterObject) filterObject).getFilter(user, "", "");
    }

    @Transactional
    public Student saveStudent(Student student, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);

        student.setId(getBaseDao().getIdBySequence("SEQ_STUDENT"));
        if (student.getBasicInfo() != null)
            basicInfoService.saveBasicInfo(student.getBasicInfo());
        if (student.getDiscuss() != null)
            discussService.saveDiscuss(student.getDiscuss());
        if (student.getRegistrationInfo() != null)
            registrationInfoService.saveRegistrationInfo(student.getRegistrationInfo());
        if (student.getSchoolRoll() != null)
            schoolRollService.saveSchoolRoll(student.getSchoolRoll());
        if (student.getProfilesHistory() != null)
            profilesHistoryService.saveProfilesHistory(student.getProfilesHistory());
        if (student.getRelatedAddress() != null && !student.getRelatedAddress().isEmpty())
            relatedAddressService.saveRelatedAddress(student.getRelatedAddress());
        if (!(student.getAccidents() == null || student.getAccidents().isEmpty()))
            accidentService.saveAccidents(student.getAccidents());
        if (!(student.getGrades() == null || student.getGrades().isEmpty()))
            gradeService.saveGrade(student.getGrades());
        if (!(student.getGradeAttachment() == null || student.getGradeAttachment().isEmpty()))
            gradeAttachmentService.saveGradeAttachment(student.getGradeAttachment());
        if (student.getSchoolfellow() != null)
            schoolfellowService.saveSchoolfellow(student.getSchoolfellow());
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public String saveStudent(String dbType, OperationLog operationLog) throws Exception {
        //记录日志
        List<OperationLog> operationLogs = new ArrayList<OperationLog>();
        operationLogs.add(operationLog);
        operationLogService.saveOperationLog(operationLogs);
        Map tableMap = TablesAndColumnsMap.tableMap;
        String tableName = tableMap.get(operationLog.getTableEN()).toString();
        String studentId = operationLog.getStudentId();
        if ("insurance".equals(operationLog.getTableEN())) {
            String insuranceId = "";
            List<Insurance> insurances = insuranceService.findInsuranceByStduentId(operationLog.getStudentId());
            if (insurances != null) {
                for (int i = 0; i < insurances.size(); i++) {
                    if (insurances.get(i).getInsurSta().equals("1")) {
                        insuranceId = insurances.get(i).getId();
                        break;
                    }
                }
            }
            if (!insuranceId.equals("")) {
                Insurance insurance = insuranceService.getInsuranceById(insuranceId);
                insurance.setInsurNo(operationLog.getAfter());
            }

        } else if ("SCMS_SCHOOL_FELLOW".equals(tableName) && studentRepository.findOne(studentId).getSchoolfellow() == null
                || "SCMS_DISCUSS".equals(tableName) && studentRepository.findOne(studentId).getDiscuss() == null
                || "SCMS_REGISTRATION_INFO".equals(tableName) && studentRepository.findOne(studentId).getRegistrationInfo() == null
                || "SCMS_PROFILES_HISTORY".equals(tableName) && studentRepository.findOne(studentId).getProfilesHistory() == null) {
            String id = getBaseDao().getIdBySequence("SEQ_" + tableName.substring(5));
            String sql = " insert into " + tableName + "(id,studentid," + operationLog.getColumnEN() + ") values('" + id + "','" + studentId + "',";
            //判断数据类型
            if (operationLog.getAfter() != null) {
                if (dbType.equals("number")) {
                    sql += operationLog.getAfter();
                } else if (dbType.equals("string")) {
                    sql += "'" + operationLog.getAfter() + "'";
                } else if (dbType.equals("date")) {
                    String after = operationLog.getAfter().substring(0, 10);
                    sql += "to_date('" + after + "','yyyy-mm-dd')";
                }
            } else {
                sql += "NULL";
            }
            sql += ")";
            System.out.println(sql);
            getBaseDao().updateBySql(sql);

            //拼出子表对应在主表Student中的字段
            StringBuffer stuColumn = new StringBuffer(tableName);
            stuColumn.delete(0, 5);
            int pos = stuColumn.indexOf("_");
            if (pos != -1) {
                stuColumn.deleteCharAt(pos);
            }
            String mainSql = "update SCMS_STUDENT set " + stuColumn.toString() + " = '" + id + "' where id = '" + studentId + "'";
            System.out.println(mainSql);
            getBaseDao().updateBySql(mainSql);
        } else {
            String sql = " update " + tableMap.get(operationLog.getTableEN()) + " set " + operationLog.getColumnEN() + " = ";
            //判断数据类型
            if (operationLog.getAfter() != null) {
                if (dbType.equals("number")) {
                    sql += operationLog.getAfter();
                } else if (dbType.equals("string")) {
                    sql += "'" + operationLog.getAfter() + "'";
                } else if (dbType.equals("date")) {
                    String after = operationLog.getAfter().substring(0, 10);
                    sql += "to_date('" + after + "','yyyy-mm-dd')";
                }
            } else {
                sql += "NULL";
            }

            sql += " where studentid ='" + operationLog.getStudentId() + "'";
            System.out.println(sql);
            getBaseDao().updateBySql(sql);
        }

        return operationLog.getAfter();
    }

    @Transactional
    public void updateState(OperationLog operationLog) throws Exception {
        if ("AX0002".equals(operationLog.getAfter())) {
            //若将"是否报到"从否(AX0001)改为是(AX0002)，则还要将报到状态从未处理(AW0002)改为新生报到(AW0001)
            //系统同时修改当前院校，根据系统时间所在为汉补还是专业的时间范围去修改当前院校。
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(operationLog.getStudentId());

            String oldRegisterState = schoolRoll.getRegisterState();
            Integer oldRegisterYear = schoolRoll.getRegisterYear();
            String oldState = schoolRoll.getState();
            String oldCurrentProvince = schoolRoll.getCurrentProvince();
            String oldCurrentUniversity = schoolRoll.getCurrentUniversity();

            schoolRoll.setRegisted("AX0002");
            schoolRoll.setRegisterState("AW0001");
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            schoolRoll.setRegisterYear(currentYear);

            //记录日志
            List<OperationLog> operationLogs = new ArrayList<OperationLog>();
            //记录日志-是否报到
            operationLogs.add(operationLog);
            //记录日志-报到状态
            OperationLog log_registerState = new OperationLog(operationLog);
            log_registerState.setColumnEN("registerState");
            log_registerState.setColumnCH("报到状态");
            log_registerState.setBefore(oldRegisterState);
            log_registerState.setAfter("AW0001");
            operationLogs.add(log_registerState);
            //记录日志-报到年度
            OperationLog log_registerYear = new OperationLog(operationLog);
            log_registerYear.setColumnEN("registerYear");
            log_registerYear.setColumnCH("报到年度");
            log_registerYear.setBefore(oldRegisterYear+"");
            log_registerYear.setAfter(currentYear+"");
            operationLogs.add(log_registerYear);

            Date majorStart = schoolRoll.getMajorStartDate();
            Date majorEnd = schoolRoll.getPlanLeaveDate();
            Date cramStart = schoolRoll.getCramDateBegin();
            Date cramEnd = schoolRoll.getCramDateEnd();
            Date now = new Date();
            if (majorStart != null && majorEnd != null && now.after(majorStart) && now.before(majorEnd)) {
                schoolRoll.setCurrentProvince(schoolRoll.getMajorProvince());
                schoolRoll.setCurrentUniversity(schoolRoll.getMajorUniversity());
                schoolRoll.setState("BB0003"); // 专业

                //记录日志-学籍状态
                OperationLog log_state = new OperationLog(operationLog);
                log_state.setColumnEN("STATE");
                log_state.setColumnCH("学籍状态");
                log_state.setBefore(oldState);
                log_state.setAfter("BB0003");
                operationLogs.add(log_state);
                //记录日志-当前省市
                OperationLog log_currentProvince = new OperationLog(operationLog);
                log_currentProvince.setColumnEN("currentProvince");
                log_currentProvince.setColumnCH("当前省市");
                log_currentProvince.setBefore(oldCurrentProvince);
                log_currentProvince.setAfter(schoolRoll.getMajorProvince());
                operationLogs.add(log_currentProvince);
                //记录日志-当前院校
                OperationLog log_currentUniversity = new OperationLog(operationLog);
                log_currentUniversity.setColumnEN("currentUniversity");
                log_currentUniversity.setColumnCH("当前院校");
                log_currentUniversity.setBefore(oldCurrentUniversity);
                log_currentUniversity.setAfter(schoolRoll.getMajorUniversity());
                operationLogs.add(log_currentUniversity);

            } else if (cramStart != null && now.after(cramStart) && cramEnd != null && now.before(cramEnd)) {
                schoolRoll.setCurrentProvince(schoolRoll.getCramProvince());
                schoolRoll.setCurrentUniversity(schoolRoll.getCramUniversity());
                schoolRoll.setState("BB0002"); // 汉补
                //记录日志-学籍状态
                OperationLog log_state = new OperationLog(operationLog);
                log_state.setColumnEN("STATE");
                log_state.setColumnCH("学籍状态");
                log_state.setBefore(oldState);
                log_state.setAfter("BB0002");
                operationLogs.add(log_state);
                //记录日志-当前省市
                OperationLog log_currentProvince = new OperationLog(operationLog);
                log_currentProvince.setColumnEN("currentProvince");
                log_currentProvince.setColumnCH("当前省市");
                log_currentProvince.setBefore(oldCurrentProvince);
                log_currentProvince.setAfter(schoolRoll.getCramProvince());
                operationLogs.add(log_currentProvince);
                //记录日志-当前院校
                OperationLog log_currentUniversity = new OperationLog(operationLog);
                log_currentUniversity.setColumnEN("currentUniversity");
                log_currentUniversity.setColumnCH("当前院校");
                log_currentUniversity.setBefore(oldCurrentUniversity);
                log_currentUniversity.setAfter(schoolRoll.getCramUniversity());
                operationLogs.add(log_currentUniversity);
            }
            schoolRollService.updateSchoolRoll(schoolRoll);
            operationLogService.saveOperationLog(operationLogs);
        } else if ("AX0001".equals(operationLog.getAfter())) {
            //若将"是否报到"从是(AX0002)改为否(AX0001)，则还要将报到状态从新生报到(AW0001)改为未处理(AW0002),学籍状态改为录取(BB0001),并清空当前省市当前院校
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(operationLog.getStudentId());

            String oldRegisterState = schoolRoll.getRegisterState();
            String oldState = schoolRoll.getState();
            String oldCurrentProvince = schoolRoll.getCurrentProvince();
            String oldCurrentUniversity = schoolRoll.getCurrentUniversity();

            schoolRoll.setRegisted("AX0001");
            schoolRoll.setRegisterState("AW0002");
            schoolRoll.setState("BB0001");
            schoolRoll.setCurrentProvince(null);
            schoolRoll.setCurrentUniversity(null);

            //记录日志
            List<OperationLog> operationLogs = new ArrayList<OperationLog>();
            //记录日志-是否报到
            operationLogs.add(operationLog);
            //记录日志-报到状态
            OperationLog log_registerState = new OperationLog(operationLog);
            log_registerState.setColumnEN("registerState");
            log_registerState.setColumnCH("报到状态");
            log_registerState.setBefore(oldRegisterState);
            log_registerState.setAfter("AW0002");
            operationLogs.add(log_registerState);
            //记录日志-学籍状态
            OperationLog log_state = new OperationLog(operationLog);
            log_state.setColumnEN("STATE");
            log_state.setColumnCH("学籍状态");
            log_state.setBefore(oldState);
            log_state.setAfter("BB0001");
            operationLogs.add(log_state);
            //记录日志-当前省市
            OperationLog log_currentProvince = new OperationLog(operationLog);
            log_currentProvince.setColumnEN("currentProvince");
            log_currentProvince.setColumnCH("当前省市");
            log_currentProvince.setBefore(oldCurrentProvince);
            log_currentProvince.setAfter(null);
            operationLogs.add(log_currentProvince);
            //记录日志-当前院校
            OperationLog log_currentUniversity = new OperationLog(operationLog);
            log_currentUniversity.setColumnEN("currentUniversity");
            log_currentUniversity.setColumnCH("当前院校");
            log_currentUniversity.setBefore(oldCurrentUniversity);
            log_currentUniversity.setAfter(null);
            operationLogs.add(log_currentUniversity);

            schoolRollService.updateSchoolRoll(schoolRoll);
            operationLogService.saveOperationLog(operationLogs);
        } else if ("BA0002".equals(operationLog.getAfter())) {
            //当操作员修改“是否离华”由“否(BA0001)”改为“是(BA0002)”时，系统需将“学籍状态”同时修改为“离华(BB0004)”
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(operationLog.getStudentId());
            schoolRoll.setLeaveChina("BA0002");
            schoolRoll.setState("BB0004");
            schoolRoll.setLeaveDate(new Date());
            schoolRollService.updateSchoolRoll(schoolRoll);
        } else if ("BA0001".equals(operationLog.getAfter())) {
            //当操作员修改“是否离华”由“是(BA0002)”改为“否(BA0001)”时,系统需检查当前时间是否在汉补或专业时间范围内。
            //如是则可修改,系统同时根据系统时间所在为汉补还是专业的时间范围去修改学籍状态。如果在汉补时间段内，学籍状态修改为“汉补”，如果在专业时间段内，学籍状态修改为”专业“。
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(operationLog.getStudentId());

            String oldRegisted = schoolRoll.getRegisted();
            String oldRegisterState = schoolRoll.getRegisterState();
            Integer oldRegisterYear = schoolRoll.getRegisterYear();
            String oldState = schoolRoll.getState();
            String oldCurrentProvince = schoolRoll.getCurrentProvince();
            String oldCurrentUniversity = schoolRoll.getCurrentUniversity();
            String oldLeaveReason = schoolRoll.getLeaveReason();
            Date oldLeaveDate = schoolRoll.getLeaveDate();

            schoolRoll.setLeaveChina("BA0001");
            schoolRoll.setRegisted("AX0002");     // 是否报到为是
            schoolRoll.setRegisterState("AW0004"); // 报到状态老生报到
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            schoolRoll.setRegisterYear(currentYear);  // 报到年度为当年
            schoolRoll.setLeaveDate(null);
            schoolRoll.setLeaveReason(null);

            //记录日志
            List<OperationLog> operationLogs = new ArrayList<OperationLog>();
            //记录日志-是否离华
            operationLogs.add(operationLog);
            //记录日志-是否报到
            OperationLog log_registed = new OperationLog(operationLog);
            log_registed.setColumnEN("registed");
            log_registed.setColumnCH("是否报到");
            log_registed.setBefore(oldRegisted);
            log_registed.setAfter("AX0002");
            operationLogs.add(log_registed);
            //记录日志-报到状态
            OperationLog log_registerState = new OperationLog(operationLog);
            log_registerState.setColumnEN("registerState");
            log_registerState.setColumnCH("报到状态");
            log_registerState.setBefore(oldRegisterState);
            log_registerState.setAfter("AW0004");
            operationLogs.add(log_registerState);
            //记录日志-报到年度
            OperationLog log_registerYear = new OperationLog(operationLog);
            log_registerYear.setColumnEN("registerYear");
            log_registerYear.setColumnCH("报到年度");
            log_registerYear.setBefore(oldRegisterYear+"");
            log_registerYear.setAfter(currentYear+"");
            operationLogs.add(log_registerYear);
            //记录日志 离华日期
            if(oldLeaveDate != null){
                String oldLeaveDateStr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(oldLeaveDate);
                OperationLog log_leaveDate = new OperationLog(operationLog);
                log_leaveDate.setColumnEN("LEAVEDATE");
                log_leaveDate.setColumnCH("离华日期");
                log_leaveDate.setBefore(oldLeaveDateStr);
                log_leaveDate.setAfter(null);
                operationLogs.add(log_leaveDate);
            }
            //记录日志 离华原因
            OperationLog log_leaveReason = new OperationLog(operationLog);
            log_leaveReason.setColumnEN("LEAVEREASON");
            log_leaveReason.setColumnCH("离华原因");
            log_leaveReason.setBefore(oldLeaveReason);
            log_leaveReason.setAfter(null);
            operationLogs.add(log_leaveReason);


            Date majorStart = schoolRoll.getMajorStartDate();
            Date majorEnd = schoolRoll.getPlanLeaveDate();
            Date cramStart = schoolRoll.getCramDateBegin();
            Date cramEnd = schoolRoll.getCramDateEnd();
            Date now = new Date();

            if (majorStart != null && now.after(majorStart) && majorEnd != null && now.before(majorEnd)) {
                schoolRoll.setState("BB0003");
                schoolRoll.setCurrentProvince(schoolRoll.getMajorProvince());
                schoolRoll.setCurrentUniversity(schoolRoll.getMajorUniversity());
                //记录日志-学籍状态
                OperationLog log_state = new OperationLog(operationLog);
                log_state.setColumnEN("STATE");
                log_state.setColumnCH("学籍状态");
                log_state.setBefore(oldState);
                log_state.setAfter("BB0003");
                operationLogs.add(log_state);
                //记录日志-当前省市
                OperationLog log_currentProvince = new OperationLog(operationLog);
                log_currentProvince.setColumnEN("currentProvince");
                log_currentProvince.setColumnCH("当前省市");
                log_currentProvince.setBefore(oldCurrentProvince);
                log_currentProvince.setAfter(schoolRoll.getMajorProvince());
                operationLogs.add(log_currentProvince);
                //记录日志-当前院校
                OperationLog log_currentUniversity = new OperationLog(operationLog);
                log_currentUniversity.setColumnEN("currentUniversity");
                log_currentUniversity.setColumnCH("当前院校");
                log_currentUniversity.setBefore(oldCurrentUniversity);
                log_currentUniversity.setAfter(schoolRoll.getMajorUniversity());
                operationLogs.add(log_currentUniversity);

            } else if (cramStart != null && now.after(cramStart) && cramEnd != null && now.before(cramEnd)) {
                schoolRoll.setState("BB0002");
                schoolRoll.setCurrentProvince(schoolRoll.getCramProvince());
                schoolRoll.setCurrentUniversity(schoolRoll.getCramUniversity());
                //记录日志-学籍状态
                OperationLog log_state = new OperationLog(operationLog);
                log_state.setColumnEN("STATE");
                log_state.setColumnCH("学籍状态");
                log_state.setBefore(oldState);
                log_state.setAfter("BB0002");
                operationLogs.add(log_state);
                //记录日志-当前省市
                OperationLog log_currentProvince = new OperationLog(operationLog);
                log_currentProvince.setColumnEN("currentProvince");
                log_currentProvince.setColumnCH("当前省市");
                log_currentProvince.setBefore(oldCurrentProvince);
                log_currentProvince.setAfter(schoolRoll.getCramProvince());
                operationLogs.add(log_currentProvince);
                //记录日志-当前院校
                OperationLog log_currentUniversity = new OperationLog(operationLog);
                log_currentUniversity.setColumnEN("currentUniversity");
                log_currentUniversity.setColumnCH("当前院校");
                log_currentUniversity.setBefore(oldCurrentUniversity);
                log_currentUniversity.setAfter(schoolRoll.getCramUniversity());
                operationLogs.add(log_currentUniversity);
            }
            schoolRollService.updateSchoolRoll(schoolRoll);
            operationLogService.saveOperationLog(operationLogs);
        }
    }
//    @SuppressWarnings("unchecked")
//    public Object getGroupByStudentId(String studentId, String groupName) {
//        if ("basicInfo".equalsIgnoreCase(groupName)) {
//            BasicInfo basicInfo = basicInfoService.getBasicInfoByStudentId(studentId);
//            return setNullByField(basicInfo, "student", BasicInfo.class);
//        }
//        if ("schoolRoll".equalsIgnoreCase(groupName)) {
//            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(studentId);
//            return setNullByField(schoolRoll, "student", SchoolRoll.class);
//        }
//        if ("registrationInfo".equalsIgnoreCase(groupName)) {
//            RegistrationInfo registrationInfo = registrationInfoService.getRegistrationInfoByStudentId(studentId);
//            return setNullByField(registrationInfo, "student", RegistrationInfo.class);
//        }
//        if ("profilesHistory".equalsIgnoreCase(groupName)) {
//            ProfilesHistory profilesHistory = profilesHistoryService.getProfilesHistoryByStudentId(studentId);
//            return setNullByField(profilesHistory, "student", ProfilesHistory.class);
//        }
//        if ("discuss".equalsIgnoreCase(groupName)) {
//            Discuss discuss = discussService.getDiscussByStudentId(studentId);
//            return setNullByField(discuss, "student", Discuss.class);
//        }
//        if ("schoolfellow".equalsIgnoreCase(groupName)) {
//            Schoolfellow schoolfellow = schoolfellowService.getSchoolfellowByStudentId(studentId);
//            return setNullByField(schoolfellow, "student", Schoolfellow.class);
//        }
//        if ("accident".equalsIgnoreCase(groupName)) {
//            List<Accident> list = accidentService.getAccidentByStudentId(studentId);
//            return setNullByField(list, "student", Accident.class);
//        }
//        if ("relatedAddress".equalsIgnoreCase(groupName)) {
//            List<RelatedAddress> list = relatedAddressService.getRelatedAddressByStudentId(studentId);
//            return setNullByField(list, "student", RelatedAddress.class);
//        }
//        if ("grade".equalsIgnoreCase(groupName)) {
//            List<Grade> list = gradeService.getGradeByStudentId(studentId);
//            return setNullByField(list, "student", Grade.class);
//        }
//        if ("gradeAttachment".equalsIgnoreCase(groupName)) {
//            List<GradeAttachment> list = gradeAttachmentService.getGradeAttachmentByStudentId(studentId);
//            return setNullByField(list, "student", GradeAttachment.class);
//        }
//        return null;
//    }

    public Student deleteStudentById(String studentId, List<OperationLog> operationLogs) throws Exception {
        Student student = getStudentById(studentId);
        if (student == null)
            return null;
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        studentRepository.delete(student);
        return student;
    }

//    @SuppressWarnings("unchecked")
//    public Object updateGroupByName(String groupName, Object groupObj, List<OperationLog> operationLogs) {
//        //记录日志
//        operationLogService.saveOperationLog(operationLogs);
//        if ("basicInfo".equalsIgnoreCase(groupName)) {
//            BasicInfo basicInfo = basicInfoService.updateBasicInfo((BasicInfo) groupObj);
//            return setNullByField(basicInfo, "student", BasicInfo.class);
//        }
//        if ("schoolRoll".equalsIgnoreCase(groupName)) {
//            SchoolRoll schoolRoll = schoolRollService.updateSchoolRoll((SchoolRoll) groupObj);
//            return setNullByField(schoolRoll, "student", SchoolRoll.class);
//        }
//        if ("registrationInfo".equalsIgnoreCase(groupName)) {
//            RegistrationInfo registrationInfo = registrationInfoService.updateRegistrationInfo((RegistrationInfo) groupObj);
//            return setNullByField(registrationInfo, "student", RegistrationInfo.class);
//        }
//        if ("profilesHistory".equalsIgnoreCase(groupName)) {
//            ProfilesHistory profilesHistory = profilesHistoryService.updateProfilesHistory((ProfilesHistory) groupObj);
//            return setNullByField(profilesHistory, "student", ProfilesHistory.class);
//        }
//        if ("discuss".equalsIgnoreCase(groupName)) {
//            Discuss discuss = discussService.updateDiscuss((Discuss) groupObj);
//            return setNullByField(discuss, "student", Discuss.class);
//        }
//        if ("schoolfellow".equalsIgnoreCase(groupName)) {
//            Schoolfellow schoolfellow = schoolfellowService.updateSchoolfellow((Schoolfellow) groupObj);
//            return setNullByField(schoolfellow, "student", Schoolfellow.class);
//        }
//        if ("accident".equalsIgnoreCase(groupName)) {
//            List<Accident> list = accidentService.updateAccident((List<Accident>) groupObj);
//            return setNullByField(list, "student", Accident.class);
//        }
//        if ("relatedAddress".equalsIgnoreCase(groupName)) {
//            List<RelatedAddress> list = relatedAddressService.updateRelatedAddress((List<RelatedAddress>) groupObj);
//            return setNullByField(list, "student", RelatedAddress.class);
//        }
//        if ("grade".equalsIgnoreCase(groupName)) {
//            List<Grade> list = gradeService.updateGrade((List<Grade>) groupObj);
//            return setNullByField(list, "student", Grade.class);
//        }
//        if ("gradeAttachment".equalsIgnoreCase(groupName)) {
//            List<GradeAttachment> list = gradeAttachmentService.updateGradeAttachment((List<GradeAttachment>) groupObj);
//            return setNullByField(list, "student", GradeAttachment.class);
//        }
//        return null;
//    }

//    public Object transObj(Object des, Object src) throws Exception {
//        try {
//            Field[] srcfields = src.getClass().getDeclaredFields();//需要其中属性值的obj
//            for (Field field : srcfields) { //遍历需要修改的所有属性
//                Field f = des.getClass().getDeclaredField(field.getName());
//                field.setAccessible(true);
//                Object value = field.get(src);
//                System.out.println("===value=====" + value);
//                if (value != null) {
//                    if (f.getType().equals(String.class)) {
//                        f.setAccessible(true);
//                        f.set(des, value);
//                    } else if (!f.getType().equals(String.class) && !f.getType().equals(Date.class)) {
//                        Integer integer = (Integer) value;
//                        f.setAccessible(true);
//                        f.set(des, integer);
//                    }
//                }
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return des;
//    }

////    @Transactional
//    public Object updateGroupByName(String studentId, String groupName, Object groupObj, List<OperationLog> operationLogs) throws Exception {
//        //记录日志
////        operationLogService.saveOperationLog(operationLogs);
//        Object des = getGroupByStudentId(studentId, groupName);
//        System.out.println("obj===============" + des);
//        if ("basicInfo".equalsIgnoreCase(groupName)) {
//            BasicInfo basicInfo = (BasicInfo) transObj(des, groupObj);
//            basicInfo = basicInfoService.updateBasicInfo(basicInfo,operationLogs);
//            return setNullByField(basicInfo, "student", BasicInfo.class);
//        }
//        if ("schoolRoll".equalsIgnoreCase(groupName)) {
//            SchoolRoll schoolRoll = (SchoolRoll) transObj(des, groupObj);
//            schoolRoll = schoolRollService.updateSchoolRoll(schoolRoll,operationLogs);
//            return setNullByField(schoolRoll, "student", SchoolRoll.class);
//        }
//        if ("registrationInfo".equalsIgnoreCase(groupName)) {
//            RegistrationInfo registrationInfo = (RegistrationInfo) transObj(des, groupObj);
//            registrationInfo = registrationInfoService.updateRegistrationInfo(registrationInfo,operationLogs);
//            return setNullByField(registrationInfo, "student", RegistrationInfo.class);
//        }
//        if ("profilesHistory".equalsIgnoreCase(groupName)) {
//            ProfilesHistory profilesHistory = (ProfilesHistory) transObj(des, groupObj);
//            profilesHistory = profilesHistoryService.updateProfilesHistory(profilesHistory,operationLogs);
//            return setNullByField(profilesHistory, "student", ProfilesHistory.class);
//        }
//        if ("discuss".equalsIgnoreCase(groupName)) {
//            Discuss discuss = (Discuss) transObj(des, groupObj);
//            discuss = discussService.updateDiscuss(discuss,operationLogs);
//            return setNullByField(discuss, "student", Discuss.class);
//        }
//        if ("schoolfellow".equalsIgnoreCase(groupName)) {
//            Schoolfellow schoolfellow = (Schoolfellow) transObj(des, groupObj);
//            schoolfellow = schoolfellowService.updateSchoolfellow(schoolfellow,operationLogs);
//            return setNullByField(schoolfellow, "student", Schoolfellow.class);
//        }
//
//        return null;
//    }

    @Transactional
    public void registerorAbandon(String mode, String studentId, List<OperationLog> operationLogs) throws Exception {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        String sql = null;
        if ("register".equals(mode)) {
            // 老生注册，汉补截止时间>12.31（当年）,当前院校取汉补院校,专业开始时间<12.31（当年），当前院校取专业院校
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(studentId);
            Date cramDateEnd = schoolRoll.getCramDateEnd();
            Date majorStartDate = schoolRoll.getMajorStartDate();
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(currentYear + "-12-31");
            String currentProvince = "";
            String currentUniversity = "";
            String state = "";
            if (cramDateEnd != null && date.before(cramDateEnd)) {
                currentProvince = schoolRoll.getCramProvince();
                currentUniversity = schoolRoll.getCramUniversity();
                state = "BB0002";
            } else if (majorStartDate != null && date.after(majorStartDate)) {
                currentProvince = schoolRoll.getMajorProvince();
                currentUniversity = schoolRoll.getMajorUniversity();
                state = "BB0003";
            }
            sql = " update SCMS_SCHOOLROLL set registed = 'AX0002'," +
                    " registerState = 'AW0004', registerYear = extract(year from sysdate)," +
                    " STATE = '" + state + "'," +
                    "CURRENTPROVINCE = '" + currentProvince + "',CURRENTUNIVERSITY = '" + currentUniversity + "'" +
                    " where studentid = '" + studentId + "'";
        }
        if ("abandon".equals(mode)) {
            sql = " update SCMS_SCHOOLROLL set registerState = 'AW0003'" +
                    " where studentid = '" + studentId + "'";
        }
        System.out.println(sql);
        getBaseDao().updateBySql(sql);
    }

    @Transactional
    public void leaveChina(String studentIds, SchoolRoll schoolRoll, List<OperationLog> operationLogs) throws Exception {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        //studentIds转为('1','2'...)
        StringBuilder sbIds = new StringBuilder("(");

        String ids[] = studentIds.split(",");
        for (String id : ids) {
            sbIds.append("'").append(id).append("',");
        }
        sbIds.setCharAt(sbIds.length() - 1, ')');

        String leavaReason = null == schoolRoll.getLeaveReason() ? "" : schoolRoll.getLeaveReason();
//        Date date=new Date(schoolRoll.getLeaveDate()+"");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String leaveDate = sdf.format(schoolRoll.getLeaveDate());
        String sql = " update SCMS_SCHOOLROLL set LEAVECHINA = 'BA0002'," +
                " LEAVEREASON = '" + leavaReason + "', LEAVEDATE = to_date('" + leaveDate + "','yyyy-mm-dd')" +
                ", STATE = 'BB0004'" +
                " where studentid in " + sbIds;
        System.out.println(sql);
        getBaseDao().updateBySql(sql);
    }

    @Transactional
    // 修改 汉补省市 汉补院校 汉补起止日期 需联动修改学籍状态 当前省市 当前院校（学籍状态若为离华或者是否报到为否，则不联动修改学籍状态和当前院校）
    public Map<String, String> modifyStudentCramInfo(String id, String cramProvince, String cramUniversity, Date cramDateBegin, Date cramDateEnd, List<OperationLog> operationLogs) {
        Map<String, String> retValue = new HashMap<String, String>();
        retValue.put("flag", "0");
        try {
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(id);
            if (schoolRoll == null) {
                return retValue;
            }
            // 修改汉补省市院校，汉补起止日期
            schoolRoll.setCramProvince(cramProvince);
            schoolRoll.setCramUniversity(cramUniversity);
            schoolRoll.setCramDateBegin(cramDateBegin);
            schoolRoll.setCramDateEnd(cramDateEnd);

            Date now = new Date();
            //学籍状态若不为离华并且是否报到为否，并且当前时间在汉补时间内，则联动修改学籍状态和当前省市院校
            if (!"BB0004".equals(schoolRoll.getState())
                    && "AX0002".equals(schoolRoll.getRegisted())
                    && now.after(cramDateBegin)
                    && now.before(cramDateEnd)) {
                //保存原有学籍状态和当前省市院校
                String oldState = schoolRoll.getState();
                String oldCurrentProvince = schoolRoll.getCurrentProvince();
                String oldCurrentUniversity = schoolRoll.getCurrentUniversity();
                //修改学籍状态和当前省市院校
                schoolRoll.setState("BB0002");
                schoolRoll.setCurrentProvince(cramProvince);
                schoolRoll.setCurrentUniversity(cramUniversity);
                //记录日志 学籍状态
                if(!"BB0002".equals(oldState)){
                    OperationLog log_state = new OperationLog(operationLogs.get(0));
                    log_state.setColumnEN("STATE");
                    log_state.setColumnCH("学籍状态");
                    log_state.setBefore(oldState);
                    log_state.setAfter("BB0002");
                    operationLogs.add(log_state);
                }
                //记录日志 当前省市
                if(!cramProvince.equals(oldCurrentProvince)){
                    OperationLog log_currentProvince = new OperationLog(operationLogs.get(0));
                    log_currentProvince.setColumnEN("CurrentProvince");
                    log_currentProvince.setColumnCH("当前省市");
                    log_currentProvince.setBefore(oldCurrentProvince);
                    log_currentProvince.setAfter(cramProvince);
                    operationLogs.add(log_currentProvince);
                }
                //记录日志 当前院校
                if(!cramUniversity.equals(oldCurrentUniversity)){
                    OperationLog log_currentUniversity = new OperationLog(operationLogs.get(0));
                    log_currentUniversity.setColumnEN("CurrentUniversity");
                    log_currentUniversity.setColumnCH("当前院校");
                    log_currentUniversity.setBefore(oldCurrentUniversity);
                    log_currentUniversity.setAfter(cramUniversity);
                    operationLogs.add(log_currentUniversity);
                }
                retValue.put("isModify", "1"); // 标志位，是否修改了学籍状态和当前省市院校
            } else {
                retValue.put("isModify", "0"); // 标志位，是否修改了学籍状态和当前省市院校
            }
            schoolRollService.updateSchoolRoll(schoolRoll);
            // 保存日志
            operationLogService.saveOperationLog(operationLogs);
            retValue.put("flag", "1");
            return retValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retValue;

    }

    @Transactional
    // 一键修改专业省市 专业院校 专业开始日期 预计毕业日期 需联动修改学籍状态 当前省市 当前院校（学籍状态若为离华或者是否报到为否，则不联动修改学籍状态和当前院校）
    public Map<String, String> modifyStudentMajorInfo(String id, String majorProvince, String majorUniversity, Date majorDateBegin, Date planLeaveDate, List<OperationLog> operationLogs) {
        Map<String, String> retValue = new HashMap<String, String>();
        retValue.put("flag", "0");
        try {
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(id);
            if (schoolRoll == null) {
                return retValue;
            }
            // 修改专业省市 专业院校 专业开始日期 预计毕业日期
            schoolRoll.setMajorProvince(majorProvince);
            schoolRoll.setMajorUniversity(majorUniversity);
            schoolRoll.setMajorStartDate(majorDateBegin);
            schoolRoll.setPlanLeaveDate(planLeaveDate);

            Date now = new Date();
            //学籍状态若不为离华并且是否报到为否，并且当前时间在专业时间内，则联动修改学籍状态和当前省市院校
            if (!"BB0004".equals(schoolRoll.getState())
                    && "AX0002".equals(schoolRoll.getRegisted())
                    && now.after(majorDateBegin)
                    && now.before(planLeaveDate)) {
                //保存原有学籍状态和当前省市院校
                String oldState = schoolRoll.getState();
                String oldCurrentProvince = schoolRoll.getCurrentProvince();
                String oldCurrentUniversity = schoolRoll.getCurrentUniversity();
                //修改学籍状态和当前省市院校
                schoolRoll.setState("BB0003");
                schoolRoll.setCurrentProvince(majorProvince);
                schoolRoll.setCurrentUniversity(majorUniversity);
                //记录日志 学籍状态
                if(!"BB0003".equals(oldState)){
                    OperationLog log_state = new OperationLog(operationLogs.get(0));
                    log_state.setColumnEN("STATE");
                    log_state.setColumnCH("学籍状态");
                    log_state.setBefore(oldState);
                    log_state.setAfter("BB0003");
                    operationLogs.add(log_state);
                }
                //记录日志 当前省市
                if(!majorProvince.equals(oldCurrentProvince)){
                    OperationLog log_currentProvince = new OperationLog(operationLogs.get(0));
                    log_currentProvince.setColumnEN("CurrentProvince");
                    log_currentProvince.setColumnCH("当前省市");
                    log_currentProvince.setBefore(oldCurrentProvince);
                    log_currentProvince.setAfter(majorProvince);
                    operationLogs.add(log_currentProvince);
                }
                //记录日志 当前院校
                if(!majorUniversity.equals(oldCurrentUniversity)){
                    OperationLog log_currentUniversity = new OperationLog(operationLogs.get(0));
                    log_currentUniversity.setColumnEN("CurrentUniversity");
                    log_currentUniversity.setColumnCH("当前院校");
                    log_currentUniversity.setBefore(oldCurrentUniversity);
                    log_currentUniversity.setAfter(majorUniversity);
                    operationLogs.add(log_currentUniversity);
                }
                retValue.put("isModify", "1"); // 标志位，是否修改了学籍状态和当前省市院校
            } else {
                retValue.put("isModify", "0"); // 标志位，是否修改了学籍状态和当前省市院校
            }
            schoolRollService.updateSchoolRoll(schoolRoll);
            // 保存日志
            operationLogService.saveOperationLog(operationLogs);
            retValue.put("flag", "1");
            return retValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retValue;

    }

    @Transactional
    // 一键清空汉补省市 汉补院校 汉补开始日期 汉补结束日期
    public Map<String, String> removeStudentCramInfo(String id, List<OperationLog> operationLogs) {
        Map<String, String> retValue = new HashMap<String, String>();
        retValue.put("flag", "0");
        try {
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(id);
            if (schoolRoll == null) {
                return retValue;
            }
            // 清空汉补省市院校，汉补起止日期
            schoolRoll.setCramProvince(null);
            schoolRoll.setCramUniversity(null);
            schoolRoll.setCramDateBegin(null);
            schoolRoll.setCramDateEnd(null);
            schoolRollService.updateSchoolRoll(schoolRoll);
            // 保存日志
            operationLogService.saveOperationLog(operationLogs);
            retValue.put("flag", "1");
            return retValue;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retValue;

    }

}
