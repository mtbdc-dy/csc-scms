package gov.gwssi.csc.scms.domain.query;

import gov.gwssi.csc.scms.domain.query.FilterCell;
import gov.gwssi.csc.scms.domain.query.FilterObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzs on 2015/4/29.
 * �춯�����б��ѯʵ����
 */
public class AbnormalFilterObject extends FilterObject {
    /**
     * ��Ҫ��ѯ����
     */
    private String cscId = null;//SCS�ǼǺ�
    private String passportName = null;//��������
    private String continent = null;//�ޱ�
    private String country = null;//����
    private String projectType = null;//��Ŀ���
    private String projectName = null;//��Ŀ����
    private String handleState = null;//�춯����״̬ 0δ�ύ 1�ύδ�ϱ� 2�ϱ�δ��� 3���δ���� 4 ����
    /**
     * ���ز�ѯ����
     */
    private String planned = null;//�������ʣ��ƻ��� �ƻ��⣩
    private String dispatch = null;//��ǲ;��
    private String travelType = null;//�����÷�
    private String annual = null; //���
    private String studentType = null;//ѧ�����
    private String appropriations = null;//���Ѱ취
    private String teachLanguage = null;//�ڿ�����
    private String schoolRollState = null;//ѧ��״̬
    private String arrivalDateBegin = null;//����ʱ����ʼʱ��
    private String arrivalDateEnd = null;//����ʱ����ֹʱ��
    private String leaveDateBegin = null;//�뻪ʱ����ʼʱ��
    private String leaveDateEnd = null;//�뻪ʱ����ֹʱ��
    private String cramDateBeginBegin = null;//������ʼʱ����ʼʱ��
    private String cramDateBeginEnd = null;//������ʼʱ����ֹʱ��
    private String cramDateEndBegin = null;//��������ʱ����ʼʱ��
    private String cramDateEndEnd = null;//��������ʱ����ֹʱ��
    private String majorStartDateBegin = null;//��רҵԺУʱ����ʼʱ��
    private String majorStartDateEnd = null;//��רҵԺУʱ����ֹʱ��

    public List<FilterCell> getConditions() {
        List<FilterCell> conditions = new ArrayList<FilterCell>();

        conditions = addCondition(conditions, "basicInfo", "passportName", "String", getPassportName());
        conditions = addCondition(conditions, "basicInfo", "continent", "String", getContinent());
        conditions = addCondition(conditions, "basicInfo", "country", "String", getCountry());
        conditions = addCondition(conditions, "basicInfo", "projectType", "String", getProjectType());
        conditions = addCondition(conditions, "basicInfo", "projectName", "String", getProjectName());
        conditions = addCondition(conditions, "basicInfo", "planted", "String", getPlanned());
        conditions = addCondition(conditions, "basicInfo", "dispatch", "String", getDispatch());
        conditions = addCondition(conditions, "basicInfo", "travleType", "String", getTravelType());
        conditions = addCondition(conditions, "basicInfo", "annual", "String", getAnnual());
        conditions = addCondition(conditions, "abnormal", "handleState", "String", getHandleState());
        conditions = addCondition(conditions, "schoolRoll", "studentType", "String", getStudentType());
        conditions = addCondition(conditions, "schoolRoll", "appropriations", "String", getAppropriations());
        conditions = addCondition(conditions, "schoolRoll", "teachLanguage", "String", getTeachLanguage());
        conditions = addCondition(conditions, "schoolRoll", "schoolrollstate", "String", getSchoolRollState());
        conditions = addCondition(conditions, "schoolRoll", "arrivalDate", "date", getArrivalDateBegin(), getArrivalDateEnd());
        conditions = addCondition(conditions, "schoolRoll", "leaveDate", "date", getLeaveDateBegin(), getLeaveDateEnd());
        conditions = addCondition(conditions, "schoolRoll", "cramDateBegin", "date", getCramDateBeginBegin(), getCramDateBeginEnd());
        conditions = addCondition(conditions, "schoolRoll", "cramDateEnd", "date", getCramDateEndBegin(), getCramDateEndEnd());
        conditions = addCondition(conditions, "schoolRoll", "majorStartDate", "date", getMajorStartDateBegin(), getMajorStartDateEnd());

        conditions = addCondition(conditions, "student", "cscId", "String", getCscId());

        return conditions;
    }
    public String getCscId() {
        return cscId;
    }

    public void setCscId(String cscId) {
        this.cscId = cscId;
    }

    public String getPassportName() {
        return passportName;
    }

    public void setPassportName(String passportName) {
        this.passportName = passportName;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getHandleState() {
        return handleState;
    }

    public void setHandleState(String handleState) {
        this.handleState = handleState;
    }

    public String getPlanned() {
        return planned;
    }

    public void setPlanned(String planned) {
        this.planned = planned;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public String getAnnual() {
        return annual;
    }

    public void setAnnual(String annual) {
        this.annual = annual;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getAppropriations() {
        return appropriations;
    }

    public void setAppropriations(String appropriations) {
        this.appropriations = appropriations;
    }

    public String getTeachLanguage() {
        return teachLanguage;
    }

    public void setTeachLanguage(String teachLanguage) {
        this.teachLanguage = teachLanguage;
    }

    public String getSchoolRollState() {
        return schoolRollState;
    }

    public void setSchoolRollState(String schoolRollState) {
        this.schoolRollState = schoolRollState;
    }

    public String getArrivalDateBegin() {
        return arrivalDateBegin;
    }

    public void setArrivalDateBegin(String arrivalDateBegin) {
        this.arrivalDateBegin = arrivalDateBegin;
    }

    public String getArrivalDateEnd() {
        return arrivalDateEnd;
    }

    public void setArrivalDateEnd(String arrivalDateEnd) {
        this.arrivalDateEnd = arrivalDateEnd;
    }

    public String getLeaveDateBegin() {
        return leaveDateBegin;
    }

    public void setLeaveDateBegin(String leaveDateBegin) {
        this.leaveDateBegin = leaveDateBegin;
    }

    public String getLeaveDateEnd() {
        return leaveDateEnd;
    }

    public void setLeaveDateEnd(String leaveDateEnd) {
        this.leaveDateEnd = leaveDateEnd;
    }

    public String getCramDateBeginBegin() {
        return cramDateBeginBegin;
    }

    public void setCramDateBeginBegin(String cramDateBeginBegin) {
        this.cramDateBeginBegin = cramDateBeginBegin;
    }

    public String getCramDateBeginEnd() {
        return cramDateBeginEnd;
    }

    public void setCramDateBeginEnd(String cramDateBeginEnd) {
        this.cramDateBeginEnd = cramDateBeginEnd;
    }

    public String getCramDateEndBegin() {
        return cramDateEndBegin;
    }

    public void setCramDateEndBegin(String cramDateEndBegin) {
        this.cramDateEndBegin = cramDateEndBegin;
    }

    public String getCramDateEndEnd() {
        return cramDateEndEnd;
    }

    public void setCramDateEndEnd(String cramDateEndEnd) {
        this.cramDateEndEnd = cramDateEndEnd;
    }

    public String getMajorStartDateBegin() {
        return majorStartDateBegin;
    }

    public void setMajorStartDateBegin(String majorStartDateBegin) {
        this.majorStartDateBegin = majorStartDateBegin;
    }

    public String getMajorStartDateEnd() {
        return majorStartDateEnd;
    }

    public void setMajorStartDateEnd(String majorStartDateEnd) {
        this.majorStartDateEnd = majorStartDateEnd;
    }
}
