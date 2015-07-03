package gov.gwssi.csc.scms.domain.query;

import java.util.Date;

/**
 * Created by lzs on 2015/6/1.
 * 机票管理信息列表结果类
 */
public class TicketResultObject  extends ResultObject{
    /**
     * ID
     */
    private String id;
    /**
     * ID
     */
    private String studentId;
    /**
     * CSCID
     */
    private String cscId;
    /**
     * 护照姓名
     */
    private String passportName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 学籍信息
     * 证件号码
     */
    private String certificateNO;

    /**
     * 国籍
     */
    private String country;

    /**
     *机票类型 0往返 1回国
     */

    private String type;
    /**
     *航线
     */

    private String airLine;
    /**
     *机票号码
     */
    private String ticketNo;
    /**
     *申请乘机日期
     */
    private Date applyDate;
    /**
     *乘机日期
     */
    private Date flightDate;
    /**
     *离境城市
     */
    private String leaveCity;
    /**
     *机票价格（元）
     */
    private long price;
    /**
     *订票状态：
     * 已提交（同未导出）、未提交、已导出、
     * 基金委已修改<指未通过正常订票流程，由基金委直接通过学籍信息修改完成>
     *     或已反馈<导入航空公司反馈信息>
     */
    private String state;

    /**
     *备注
     */
    private String remark;

    /**
     *创建人
     */
    private String createBy;

    /**
     *创建时间
     */
    private Date created;
    /**
     *修改人
     */
    private String updateBy;

    /**
     *修改时间
     */
    private Date updated;
    /**
     *能否修改
     */
    private boolean mod;

    public TicketResultObject(String id, String studentId,String cscId, String passportName, String gender,
                              Date birthday, String certificateNO, String country, String type,
                              String airLine, String ticketNo, Date applyDate, Date flightDate,
                              String leaveCity, long price, String state, String remark,
                              String createBy, Date created, String updateBy, Date updated) {
        this.id = id;
        this.studentId = studentId;
        this.cscId = cscId;
        this.passportName = passportName;
        this.gender = gender;
        this.birthday = birthday;
        this.certificateNO = certificateNO;
        this.country = country;
        this.type = type;
        this.airLine = airLine;
        this.ticketNo = ticketNo;
        this.applyDate = applyDate;
        this.flightDate = flightDate;
        this.leaveCity = leaveCity;
        this.price = price;
        this.state = state;
        this.remark = remark;
        this.createBy = createBy;
        this.created = created;
        this.updateBy = updateBy;
        this.updated = updated;
        this.mod = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCertificateNO() {
        return certificateNO;
    }

    public void setCertificateNO(String certificateNO) {
        this.certificateNO = certificateNO;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAirLine() {
        return airLine;
    }

    public void setAirLine(String airLine) {
        this.airLine = airLine;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public String getLeaveCity() {
        return leaveCity;
    }

    public void setLeaveCity(String leaveCity) {
        this.leaveCity = leaveCity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean isMod() {
        return mod;
    }

    public void setMod(boolean mod) {
        this.mod = mod;
    }

    public static String getResultObject() {
        /**
         * String id, String cscId, String passportName, String gender,
         Date birthday, String certificateNO, String country, String type,
         String airLine, String ticketNo, Date applyDate, Date flightDate,
         String leaveCity, long price, String state, String remark,
         String createBy, Date created, String userName, Date updated
         */
        String resultSql = "select new gov.gwssi.csc.scms.domain.query.TicketResultObject(ticket.id,student.id, student.cscId, basicInfo.passportName,basicInfo.gender, " +
                "basicInfo.birthday,schoolRoll.certificateNO,basicInfo.country,ticket.type,ticket.airLine,ticket.ticketNo," +
                "  ticket.applyDate,ticket.flightDate,ticket.leaveCity,ticket.price,ticket.state,ticket.remark," +
                "ticket.createBy,ticket.created,ticket.updateBy,ticket.updated) ";
        return resultSql;
    }
}
