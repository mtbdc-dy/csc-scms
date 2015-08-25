package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.StudentResultObject;
import gov.gwssi.csc.scms.domain.student.*;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.student.StudentRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import gov.gwssi.csc.scms.utils.TablesAndColumnsMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


    public Student getStudentById(String id) throws Exception{
        Student student = studentRepository.findOne(id);
        if(null == student){
            throw new Exception("can not find the student with id:" + id);
        }

        setNullByField(student.getBasicInfo(), "student", BasicInfo.class);
        setNullByField(student.getSchoolfellow(), "student", Schoolfellow.class);
        setNullByField(student.getDiscuss(), "student", Discuss.class);
        setNullByField(student.getProfilesHistory(), "student", ProfilesHistory.class);
        setNullByField(student.getRegistrationInfo(), "student", RegistrationInfo.class);
        setNullByField(student.getSchoolRoll(), "student", SchoolRoll.class);


        setNullByField(student.getAccidents(), "student", Accident.class);
        setNullByField(student.getRelatedAddress(), "student", RelatedAddress.class);
        setNullByField(student.getGrades(), "student", Grade.class);
        setNullByField(student.getGradeAttachment(), "student", GradeAttachment.class);
        return student;
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
        return sqlStr + new StudentFilter((StudentFilterObject) filterObject).getFilter(user,"","");
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
        return sqlStr + new StudentFilter((StudentFilterObject) filterObject).getFilter(user,"","");
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
        String sql = " update "+tableMap.get(operationLog.getTableEN())+" set "+operationLog.getColumnEN()+" = ";
        //判断数据类型
        if(dbType.equals("number")){
            sql+=operationLog.getAfter();
        }else if(dbType.equals("string")){
            sql+="'"+operationLog.getAfter()+"'";
        }else if(dbType.equals("date")){
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String after = operationLog.getAfter().substring(0,10);
            sql+="to_date('" + after + "','yyyy-mm-dd')" ;
        }
        sql+= " where studentid ='" + operationLog.getStudentId()+"'";
        System.out.println(sql);
        getBaseDao().updateBySql(sql);


        return operationLog.getAfter().toString();
    }
    @Transactional
    public void updateRegistState( OperationLog operationLog) throws Exception {
        if(operationLog.getAfter().equals("AX0002")){    //若将"是否报到"从否(AX0001)改为是(AX0002)，则还要将报到状态从未处理(AW0002)改为报到(AW0001)
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(operationLog.getStudentId());
            schoolRoll.setRegisterState("AW0001");
            schoolRollService.updateSchoolRoll(schoolRoll);
        }
        if(operationLog.getAfter().equals("AX0001")){  //若将"是否报到"从是(AX0002)改为否(AX0001)，则还要将报到状态从报到(AW0001)改为未处理(AW0002)
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(operationLog.getStudentId());
            schoolRoll.setRegisterState("AW0002");
            schoolRollService.updateSchoolRoll(schoolRoll);
        }
    }
    @SuppressWarnings("unchecked")
    public Object getGroupByStudentId(String studentId, String groupName) {
        if ("basicInfo".equalsIgnoreCase(groupName)) {
            BasicInfo basicInfo = basicInfoService.getBasicInfoByStudentId(studentId);
            return setNullByField(basicInfo, "student", BasicInfo.class);
        }
        if ("schoolRoll".equalsIgnoreCase(groupName)) {
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(studentId);
            return setNullByField(schoolRoll, "student", SchoolRoll.class);
        }
        if ("registrationInfo".equalsIgnoreCase(groupName)) {
            RegistrationInfo registrationInfo = registrationInfoService.getRegistrationInfoByStudentId(studentId);
            return setNullByField(registrationInfo, "student", RegistrationInfo.class);
        }
        if ("profilesHistory".equalsIgnoreCase(groupName)) {
            ProfilesHistory profilesHistory = profilesHistoryService.getProfilesHistoryByStudentId(studentId);
            return setNullByField(profilesHistory, "student", ProfilesHistory.class);
        }
        if ("discuss".equalsIgnoreCase(groupName)) {
            Discuss discuss = discussService.getDiscussByStudentId(studentId);
            return setNullByField(discuss, "student", Discuss.class);
        }
        if ("schoolfellow".equalsIgnoreCase(groupName)) {
            Schoolfellow schoolfellow = schoolfellowService.getSchoolfellowByStudentId(studentId);
            return setNullByField(schoolfellow, "student", Schoolfellow.class);
        }
        if ("accident".equalsIgnoreCase(groupName)) {
            List<Accident> list = accidentService.getAccidentByStudentId(studentId);
            return setNullByField(list, "student", Accident.class);
        }
        if ("relatedAddress".equalsIgnoreCase(groupName)) {
            List<RelatedAddress> list = relatedAddressService.getRelatedAddressByStudentId(studentId);
            return setNullByField(list, "student", RelatedAddress.class);
        }
        if ("grade".equalsIgnoreCase(groupName)) {
            List<Grade> list = gradeService.getGradeByStudentId(studentId);
            return setNullByField(list, "student", Grade.class);
        }
        if ("gradeAttachment".equalsIgnoreCase(groupName)) {
            List<GradeAttachment> list = gradeAttachmentService.getGradeAttachmentByStudentId(studentId);
            return setNullByField(list, "student", GradeAttachment.class);
        }
        return null;
    }

    public Student deleteStudentById(String studentId, List<OperationLog> operationLogs) throws Exception{
        Student student = getStudentById(studentId);
        if (student == null)
            return null;
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        studentRepository.delete(student);
        return student;
    }

    @SuppressWarnings("unchecked")
    public Object updateGroupByName(String groupName, Object groupObj, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        if ("basicInfo".equalsIgnoreCase(groupName)) {
            BasicInfo basicInfo = basicInfoService.updateBasicInfo((BasicInfo) groupObj);
            return setNullByField(basicInfo, "student", BasicInfo.class);
        }
        if ("schoolRoll".equalsIgnoreCase(groupName)) {
            SchoolRoll schoolRoll = schoolRollService.updateSchoolRoll((SchoolRoll) groupObj);
            return setNullByField(schoolRoll, "student", SchoolRoll.class);
        }
        if ("registrationInfo".equalsIgnoreCase(groupName)) {
            RegistrationInfo registrationInfo = registrationInfoService.updateRegistrationInfo((RegistrationInfo) groupObj);
            return setNullByField(registrationInfo, "student", RegistrationInfo.class);
        }
        if ("profilesHistory".equalsIgnoreCase(groupName)) {
            ProfilesHistory profilesHistory = profilesHistoryService.updateProfilesHistory((ProfilesHistory) groupObj);
            return setNullByField(profilesHistory, "student", ProfilesHistory.class);
        }
        if ("discuss".equalsIgnoreCase(groupName)) {
            Discuss discuss = discussService.updateDiscuss((Discuss) groupObj);
            return setNullByField(discuss, "student", Discuss.class);
        }
        if ("schoolfellow".equalsIgnoreCase(groupName)) {
            Schoolfellow schoolfellow = schoolfellowService.updateSchoolfellow((Schoolfellow) groupObj);
            return setNullByField(schoolfellow, "student", Schoolfellow.class);
        }
        if ("accident".equalsIgnoreCase(groupName)) {
            List<Accident> list = accidentService.updateAccident((List<Accident>) groupObj);
            return setNullByField(list, "student", Accident.class);
        }
        if ("relatedAddress".equalsIgnoreCase(groupName)) {
            List<RelatedAddress> list = relatedAddressService.updateRelatedAddress((List<RelatedAddress>) groupObj);
            return setNullByField(list, "student", RelatedAddress.class);
        }
        if ("grade".equalsIgnoreCase(groupName)) {
            List<Grade> list = gradeService.updateGrade((List<Grade>) groupObj);
            return setNullByField(list, "student", Grade.class);
        }
        if ("gradeAttachment".equalsIgnoreCase(groupName)) {
            List<GradeAttachment> list = gradeAttachmentService.updateGradeAttachment((List<GradeAttachment>) groupObj);
            return setNullByField(list, "student", GradeAttachment.class);
        }
        return null;
    }

    public Object transObj(Object des, Object src) throws Exception {
        try {
            Field[] srcfields = src.getClass().getDeclaredFields();//需要其中属性值的obj
            for (Field field : srcfields) { //遍历需要修改的所有属性
                Field f = des.getClass().getDeclaredField(field.getName());
                field.setAccessible(true);
                Object value = field.get(src);
                System.out.println("===value=====" + value);
                if (value != null) {
                    if (f.getType().equals(String.class)) {
                        f.setAccessible(true);
                        f.set(des, value);
                    } else if (!f.getType().equals(String.class) && !f.getType().equals(Date.class)) {
                        Integer integer = (Integer) value;
                        f.setAccessible(true);
                        f.set(des, integer);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return des;
    }

    @Transactional
    public Object updateGroupByName(String studentId, String groupName, Object groupObj, List<OperationLog> operationLogs) throws Exception {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        Object des = getGroupByStudentId(studentId, groupName);
        System.out.println("obj===============" + des);
        if ("basicInfo".equalsIgnoreCase(groupName)) {
            BasicInfo basicInfo = (BasicInfo) transObj(des, groupObj);
            basicInfo = basicInfoService.updateBasicInfo(basicInfo);
            return setNullByField(basicInfo, "student", BasicInfo.class);
        }
        if ("schoolRoll".equalsIgnoreCase(groupName)) {
            SchoolRoll schoolRoll = (SchoolRoll) transObj(des, groupObj);
            schoolRoll = schoolRollService.updateSchoolRoll(schoolRoll);
            return setNullByField(schoolRoll, "student", SchoolRoll.class);
        }
        if ("registrationInfo".equalsIgnoreCase(groupName)) {
            RegistrationInfo registrationInfo = (RegistrationInfo) transObj(des, groupObj);
            registrationInfo = registrationInfoService.updateRegistrationInfo(registrationInfo);
            return setNullByField(registrationInfo, "student", RegistrationInfo.class);
        }
        if ("profilesHistory".equalsIgnoreCase(groupName)) {
            ProfilesHistory profilesHistory = (ProfilesHistory) transObj(des, groupObj);
            profilesHistory = profilesHistoryService.updateProfilesHistory(profilesHistory);
            return setNullByField(profilesHistory, "student", ProfilesHistory.class);
        }
        if ("discuss".equalsIgnoreCase(groupName)) {
            Discuss discuss = (Discuss) transObj(des, groupObj);
            discuss = discussService.updateDiscuss(discuss);
            return setNullByField(discuss, "student", Discuss.class);
        }
        if ("schoolfellow".equalsIgnoreCase(groupName)) {
            Schoolfellow schoolfellow = (Schoolfellow) transObj(des, groupObj);
            schoolfellow = schoolfellowService.updateSchoolfellow(schoolfellow);
            return setNullByField(schoolfellow, "student", Schoolfellow.class);
        }

        return null;
    }

    @Transactional
    public void registerorAbandon(String mode, String studentId, List<OperationLog> operationLogs) throws Exception {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        String sql = null;
        if("register".equals(mode)){
            sql = " update SCMS_SCHOOLROLL set registed = 'AX0002'," +
                    " registerState = 'AW0001', registerYear = extract(year from sysdate)"+
                    " where studentid = '"+studentId +"'";
        }
        if("abandon".equals(mode)){
            sql = " update SCMS_SCHOOLROLL set registerState = 'AW0003'" +
                    " where studentid = '"+studentId +"'";
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
                // "LEAVEDATE = to_timestamp('"+schoolRoll.getLeaveDate()+"','yyyy-mm-dd hh24:mi:ss:ff') " +
                " where studentid in " + sbIds;
        System.out.println(sql);
        getBaseDao().updateBySql(sql);
    }

}
