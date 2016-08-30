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

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Murray on 4/5/2015.
 * 学生业务操作类
 */
@Service(value = "studentService")
public class StudentService extends BaseService
{

    @Autowired
    @Qualifier("studentRepository")
    private StudentRepository       studentRepository;
    @Autowired
    private BasicInfoService        basicInfoService;
    @Autowired
    private DiscussService          discussService;
    @Autowired
    private RegistrationInfoService registrationInfoService;
    @Autowired
    private RelatedAddressService   relatedAddressService;
    @Autowired
    private SchoolRollService       schoolRollService;
    @Autowired
    private AccidentService         accidentService;
    @Autowired
    private ProfilesHistoryService  profilesHistoryService;
    @Autowired
    private SchoolfellowService     schoolfellowService;
    @Autowired
    private GradeService            gradeService;
    @Autowired
    private GradeAttachmentService  gradeAttachmentService;
    @Autowired
    private OperationLogService     operationLogService;
    @Autowired
    private InsuranceService        insuranceService;

    @Transactional
    public Student getStudentById(String id) throws Exception
    {
        Student student = studentRepository.findOne(id);
        if (null == student)
        {
            throw new Exception("can not find the student with id:" + id);
        }
        return student;
    }

    @Transactional
    public Student getCompleteInfoOfStudentById(String id) throws Exception
    {
        Student student = getStudentById(id);

        List<ScholarshipX> scholarships = student.getScholarshipXs();
        ScholarshipX lastScholarship = new ScholarshipX();
        if (scholarships.size() > 0)
        {
            Collections.sort(scholarships);
            Collections.reverse(scholarships);
            lastScholarship = scholarships.get(0);
        }

        if(student.getSchoolRoll() != null){
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

    public Student getStudentByCscId(String scsId)
    {
        return studentRepository.findByCscId(scsId);
    }

    public List<StudentResultObject> getStudentsByFilter(FilterObject filterObject, User user)
    {
        List<StudentResultObject> studentList;
        int                       startPosition, pageSize;

        String sql = getSqlByBody(filterObject, user);
        System.out.println("getStudentSql===" + sql);
        if (sql == null)
        {
            return null;
        }

        try
        {
            startPosition = Integer.parseInt(filterObject.getOffSet());
            pageSize = Integer.parseInt(filterObject.getPageSize());
        } catch (NumberFormatException ne)
        {
            ne.printStackTrace();
            startPosition = FilterObject.OFFSETDEFULT;
            pageSize = FilterObject.PAGESIZEDEFULT;
        }

        studentList = super.getBaseDao().getObjectListByHQL(sql, StudentResultObject.class, startPosition, pageSize);
        return studentList;
    }

    public List<StudentResultObject> getSchoolStudentsByFilter(FilterObject filterObject, User user)
    {
        List<StudentResultObject> studentList;
        int                       startPosition, pageSize;
        String                    str = "and schoolRoll.registed = 'AX0002' and schoolRoll.leaveChina ='BA0001'";
        String                    sql = getSqlByBody(filterObject, user, str);
        System.out.println("getStudentSql===" + sql);
        if (sql == null)
        {
            return null;
        }

        try
        {
            startPosition = Integer.parseInt(filterObject.getOffSet());
            pageSize = Integer.parseInt(filterObject.getPageSize());
        } catch (NumberFormatException ne)
        {
            ne.printStackTrace();
            startPosition = FilterObject.OFFSETDEFULT;
            pageSize = FilterObject.PAGESIZEDEFULT;
        }

        studentList = super.getBaseDao().getObjectListByHQL(sql, StudentResultObject.class, startPosition, pageSize);
        return studentList;
    }

    public List<StudentResultObject> getLeaveStudentsByFilter(FilterObject filterObject, User user)
    {
        List<StudentResultObject> studentList;
        int                       startPosition, pageSize;
        String                    str = "and schoolRoll.registed = 'AX0002' and schoolRoll.leaveChina ='BA0002'";
        String                    sql = getSqlByBody(filterObject, user, str);
        System.out.println("getStudentSql===" + sql);
        if (sql == null)
        {
            return null;
        }

        try
        {
            startPosition = Integer.parseInt(filterObject.getOffSet());
            pageSize = Integer.parseInt(filterObject.getPageSize());
        } catch (NumberFormatException ne)
        {
            ne.printStackTrace();
            startPosition = FilterObject.OFFSETDEFULT;
            pageSize = FilterObject.PAGESIZEDEFULT;
        }

        studentList = super.getBaseDao().getObjectListByHQL(sql, StudentResultObject.class, startPosition, pageSize);
        return studentList;
    }

    public int getCountByQueryFilter(FilterObject filterObject, User user)
    {
        String sql = getCountSqlByBody(filterObject, user);
        if (sql == null)
        {
            return 0;
        }

        return super.getBaseDao().getCountObjectByHQL(sql);
    }

    private String getCountSqlByBody(FilterObject filterObject, User user)
    {
        if (filterObject == null)
        {
            return null;
        }

        String tempSql = "select count(*) " +
                "from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll " +
                "where student.basicInfo = basicInfo.student " +
                "and student.schoolRoll = schoolRoll.student ";

        // tempSql += new StudentFilter((StudentFilterObject) filterObject).getFilter(user);
        return tempSql;
    }

    private String getSqlByBody(FilterObject filterObject, User user)
    {
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

    private String getSqlByBody(FilterObject filterObject, User user, String str)
    {
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
    public Student saveStudent(Student student, List<OperationLog> operationLogs)
    {
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
    public Student updateStudent(Student student)
    {
        return studentRepository.save(student);
    }

    @Transactional
    public String saveStudent(String dbType, OperationLog operationLog) throws Exception
    {
        //记录日志
        List<OperationLog> operationLogs = new ArrayList<OperationLog>();
        operationLogs.add(operationLog);
        operationLogService.saveOperationLog(operationLogs);
        Map tableMap = TablesAndColumnsMap.tableMap;
        String tableName = tableMap.get(operationLog.getTableEN()).toString();
        String studentId = operationLog.getStudentId();
        if ("insurance".equals(operationLog.getTableEN())) {
            String          insuranceId = "";
            List<Insurance> insurances  = insuranceService.findInsuranceByStduentId(operationLog.getStudentId());
            if (insurances != null)
            {
                for (int i = 0; i < insurances.size(); i++)
                {
                    if (insurances.get(i).getInsurSta().equals("1"))
                    {
                        insuranceId = insurances.get(i).getId();
                        break;
                    }
                }
            }
            if (!insuranceId.equals(""))
            {
                Insurance insurance = insuranceService.getInsuranceById(insuranceId);
                insurance.setInsurNo(operationLog.getAfter());
            }

        } else if("SCMS_SCHOOL_FELLOW".equals(tableName) && studentRepository.findOne(studentId).getSchoolfellow() == null
                 || "SCMS_DISCUSS".equals(tableName) && studentRepository.findOne(studentId).getDiscuss() == null
                 || "SCMS_REGISTRATION_INFO".equals(tableName) && studentRepository.findOne(studentId).getRegistrationInfo() == null
                 || "SCMS_PROFILES_HISTORY".equals(tableName) && studentRepository.findOne(studentId).getProfilesHistory() == null){
            String id = getBaseDao().getIdBySequence("SEQ_" + tableName.substring(5));
            String sql = " insert into " + tableName + "(id,studentid," + operationLog.getColumnEN() + ") values('"+id +"','"+ studentId +"',";
            //判断数据类型
            if (dbType.equals("number"))
            {
                sql += operationLog.getAfter();
            } else if (dbType.equals("string"))
            {
                sql += "'" + operationLog.getAfter() + "'";
            } else if (dbType.equals("date"))
            {
                String after = operationLog.getAfter().substring(0, 10);
                sql += "to_date('" + after + "','yyyy-mm-dd')";
            }
            sql += ")";
            System.out.println(sql);
            getBaseDao().updateBySql(sql);

            //拼出子表对应在主表Student中的字段
            StringBuffer stuColumn = new StringBuffer(tableName);
            stuColumn.delete(0,5);
            int pos = stuColumn.indexOf("_");
            if(pos!=-1){
                stuColumn.deleteCharAt(pos);
            }
            String mainSql = "update SCMS_STUDENT set "+ stuColumn.toString() +" = '"+ id +"' where id = '"+ studentId +"'";
            System.out.println(mainSql);
            getBaseDao().updateBySql(mainSql);
        } else {
            String sql = " update " + tableMap.get(operationLog.getTableEN()) + " set " + operationLog.getColumnEN() + " = ";
            //判断数据类型
            if (dbType.equals("number"))
            {
                sql += operationLog.getAfter();
            } else if (dbType.equals("string"))
            {
                sql += "'" + operationLog.getAfter() + "'";
            } else if (dbType.equals("date"))
            {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String after = operationLog.getAfter().substring(0, 10);
                sql += "to_date('" + after + "','yyyy-mm-dd')";
            }
            sql += " where studentid ='" + operationLog.getStudentId() + "'";
            System.out.println(sql);
            getBaseDao().updateBySql(sql);
        }
        return operationLog.getAfter().toString();
    }

    @Transactional
    public void updateState(OperationLog operationLog) throws Exception
    {
        if (operationLog.getAfter().equals("AX0002")) {
            //若将"是否报到"从否(AX0001)改为是(AX0002)，则还要将报到状态从未处理(AW0002)改为新生报到(AW0001)
            //系统同时修改当前院校，根据系统时间所在为汉补还是专业的时间范围去修改当前院校。
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(operationLog.getStudentId());
            schoolRoll.setRegisterState("AW0001");
            Calendar calendar    = Calendar.getInstance();
            int      currentYear = calendar.get(Calendar.YEAR);
            schoolRoll.setRegisterYear(currentYear);
            Date majorStart = schoolRoll.getMajorStartDate();
            Date majorEnd = schoolRoll.getPlanLeaveDate();
            Date cramStart = schoolRoll.getCramDateBegin();
            Date cramEnd = schoolRoll.getCramDateEnd();
            Date now = new Date();
            if(now.after(majorStart) && now.before(majorEnd)){
                schoolRoll.setCurrentProvince(schoolRoll.getMajorProvince());
                schoolRoll.setCurrentUniversity(schoolRoll.getMajorUniversity());
            }else if(now.after(cramStart) && now.before(cramEnd)){
                schoolRoll.setCurrentProvince(schoolRoll.getCramProvince());
                schoolRoll.setCurrentUniversity(schoolRoll.getCramUniversity());
            }
            schoolRollService.updateSchoolRoll(schoolRoll);
        }else if (operationLog.getAfter().equals("AX0001")) {
            //若将"是否报到"从是(AX0002)改为否(AX0001)，则还要将报到状态从新生报到(AW0001)改为未处理(AW0002)
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(operationLog.getStudentId());
            schoolRoll.setRegisterState("AW0002");
            schoolRollService.updateSchoolRoll(schoolRoll);
        }else if("BA0002".equals(operationLog.getAfter())){
            //当操作员修改“是否离华”由“否(BA0001)”改为“是(BA0002)”时，系统需将“学籍状态”同时修改为“离华(BB0004)”
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(operationLog.getStudentId());
            String oldState = schoolRoll.getState();
            schoolRoll.setState("BB0004");
            schoolRoll.setLeaveDate(new Date());
            schoolRollService.updateSchoolRoll(schoolRoll);
            //记录日志 学籍状态
            List<OperationLog> operationLogState = new ArrayList<OperationLog>();
            operationLog.setColumnEN("STATE");
            operationLog.setColumnCH("学籍状态");
            operationLog.setBefore(oldState);
            operationLog.setAfter("BB0004");
            operationLogState.add(operationLog);
            operationLogService.saveOperationLog(operationLogState);
            //记录日志 离华日期
            List<OperationLog> operationLogLeaveDate = new ArrayList<OperationLog>();
            operationLog.setColumnEN("LEAVEDATE");
            operationLog.setColumnCH("离华日期");
            operationLog.setBefore(null);
            operationLog.setAfter((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
            operationLogLeaveDate.add(operationLog);
            operationLogService.saveOperationLog(operationLogLeaveDate);
        }else if("BA0001".equals(operationLog.getAfter())){
            //当操作员修改“是否离华”由“是(BA0002)”改为“否(BA0001)”时,系统需检查当前时间是否在汉补或专业时间范围内。
            //如是则可修改,系统同时根据系统时间所在为汉补还是专业的时间范围去修改学籍状态。如果在汉补时间段内，学籍状态修改为“汉补”，如果在专业时间段内，学籍状态修改为”专业“。
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(operationLog.getStudentId());
            String oldState = schoolRoll.getState();
            String oldLeaveReason = schoolRoll.getLeaveReason();
            Date oldLeaveDate = schoolRoll.getLeaveDate();
            Date majorStart = schoolRoll.getMajorStartDate();
            Date majorEnd = schoolRoll.getPlanLeaveDate();
            Date cramStart = schoolRoll.getCramDateBegin();
            Date cramEnd = schoolRoll.getCramDateEnd();
            Date now = new Date();
            if(now.after(majorStart) && now.before(majorEnd)){
                schoolRoll.setState("BB0003");
                schoolRoll.setCurrentProvince(schoolRoll.getMajorProvince());
                schoolRoll.setCurrentUniversity(schoolRoll.getMajorUniversity());
                schoolRoll.setLeaveDate(null);
                schoolRoll.setLeaveReason(null);
                schoolRollService.updateSchoolRoll(schoolRoll);
                //记录日志 学籍状态
                List<OperationLog> operationLogState = new ArrayList<OperationLog>();
                operationLog.setColumnEN("STATE");
                operationLog.setColumnCH("学籍状态");
                operationLog.setBefore(oldState);
                operationLog.setAfter("BB0003");
                operationLogState.add(operationLog);
                operationLogService.saveOperationLog(operationLogState);
            }else if(now.after(cramStart) && now.before(cramEnd)){
                schoolRoll.setState("BB0002");
                schoolRoll.setCurrentProvince(schoolRoll.getCramProvince());
                schoolRoll.setCurrentUniversity(schoolRoll.getCramUniversity());
                schoolRoll.setLeaveDate(null);
                schoolRoll.setLeaveReason(null);
                schoolRollService.updateSchoolRoll(schoolRoll);
                //记录日志 学籍状态
                List<OperationLog> operationLogState = new ArrayList<OperationLog>();
                operationLog.setColumnEN("STATE");
                operationLog.setColumnCH("学籍状态");
                operationLog.setBefore(oldState);
                operationLog.setAfter("BB0002");
                operationLogState.add(operationLog);
                operationLogService.saveOperationLog(operationLogState);
            }
            //记录日志 离华日期
            if(oldLeaveDate != null){
                String oldLeaveDateStr = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(oldLeaveDate);
                List<OperationLog> operationLogLeaveDate = new ArrayList<OperationLog>();
                operationLog.setColumnEN("LEAVEDATE");
                operationLog.setColumnCH("离华日期");
                operationLog.setBefore(oldLeaveDateStr);
                operationLog.setAfter(null);
                operationLogLeaveDate.add(operationLog);
                operationLogService.saveOperationLog(operationLogLeaveDate);
            }
            //记录日志 离华原因
            if(oldLeaveReason!=null && oldLeaveReason!=""){
                List<OperationLog> operationLogLeaveReason = new ArrayList<OperationLog>();
                operationLog.setColumnEN("LEAVEREASON");
                operationLog.setColumnCH("离华原因");
                operationLog.setBefore(oldLeaveReason);
                operationLog.setAfter(null);
                operationLogLeaveReason.add(operationLog);
                operationLogService.saveOperationLog(operationLogLeaveReason);
            }
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

    public Student deleteStudentById(String studentId, List<OperationLog> operationLogs) throws Exception
    {
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
    public void registerorAbandon(String mode, String studentId, List<OperationLog> operationLogs) throws Exception
    {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        String sql = null;
        if ("register".equals(mode))
        {
            // 老生注册，汉补截止时间>12.31（当年）,当前院校取汉补院校,专业开始时间<12.31（当年），当前院校取专业院校
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(studentId);
            Date cramDateEnd = schoolRoll.getCramDateEnd();
            Date majorStartDate = schoolRoll.getMajorStartDate();
            Calendar calendar = Calendar.getInstance();
            int      currentYear = calendar.get(Calendar.YEAR);
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(currentYear+"-12-31");
            String currentProvince = "";
            String currentUniversity = "";
            if(cramDateEnd.after(date)){
                currentProvince = schoolRoll.getCramProvince();
                currentUniversity = schoolRoll.getCramUniversity();
            }else if(majorStartDate.before(date)){
                currentProvince = schoolRoll.getMajorProvince();
                currentUniversity = schoolRoll.getMajorUniversity();
            }
            sql = " update SCMS_SCHOOLROLL set registed = 'AX0002'," +
                    " registerState = 'AW0004', registerYear = extract(year from sysdate)," +
                    "CURRENTPROVINCE = '" + currentProvince + "',CURRENTUNIVERSITY = '" + currentUniversity + "'" +
                    " where studentid = '" + studentId + "'";
        }
        if ("abandon".equals(mode))
        {
            sql = " update SCMS_SCHOOLROLL set registerState = 'AW0003'" +
                    " where studentid = '" + studentId + "'";
        }
        System.out.println(sql);
        getBaseDao().updateBySql(sql);
    }

    @Transactional
    public void leaveChina(String studentIds, SchoolRoll schoolRoll, List<OperationLog> operationLogs) throws Exception
    {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        //studentIds转为('1','2'...)
        StringBuilder sbIds = new StringBuilder("(");

        String ids[] = studentIds.split(",");
        for (String id : ids)
        {
            sbIds.append("'").append(id).append("',");
        }
        sbIds.setCharAt(sbIds.length() - 1, ')');

        String leavaReason = null == schoolRoll.getLeaveReason() ? "" : schoolRoll.getLeaveReason();
//        Date date=new Date(schoolRoll.getLeaveDate()+"");
        SimpleDateFormat sdf       = new SimpleDateFormat("yyyy-MM-dd");
        String           leaveDate = sdf.format(schoolRoll.getLeaveDate());
        String sql = " update SCMS_SCHOOLROLL set LEAVECHINA = 'BA0002'," +
                " LEAVEREASON = '" + leavaReason + "', LEAVEDATE = to_date('" + leaveDate + "','yyyy-mm-dd')" +
                ", STATE = 'BB0004'" +
                " where studentid in " + sbIds;
        System.out.println(sql);
        getBaseDao().updateBySql(sql);
    }

}
