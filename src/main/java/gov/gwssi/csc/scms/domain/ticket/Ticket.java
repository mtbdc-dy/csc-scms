package gov.gwssi.csc.scms.domain.ticket;

import gov.gwssi.csc.scms.domain.student.Student;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/16.
 * 机票管理
 */
@Entity
@Table(name = "SCMS_AIRTICKET")
public class Ticket {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentid"/*,nullable = false*/)
    private Student student;
    /**
     * STUDENTID
     */
    private  String studentId;
    /**
     *院校 可以不需要 待定
     */
    @Column(name = "school",length=6)
    private String school;

    /**
     *机票类型 0来华 1回国
     */
    @Column(name = "type",length=1)
    private String type;
    /**
     *航线
     */
    @Column(name = "airLine",length=100)
    private String airLine;
    /**
     *机票号码
     */
    @Column(name = "ticketNo",length=100)
    private String ticketNo;
    /**
     *护照有效期
     */
    @Column(name = "validdate",length=6)
    private Date validdate;
    /**
     *申请乘机日期
     */
    @Column(name = "applyDate")
    private Date applyDate;
    /**
     *乘机日期
     */
    @Column(name = "flightDate")
    private Date flightDate;
    /**
     *离境城市
     */
    @Column(name = "leaveCity",length=100)
    private String leaveCity;
    /**
     *机票价格（元）
     */
    @Column(name = "price",length=18)
    private BigDecimal price;
    /**
     *订票状态：
     * 已提交（同未导出）、未提交、已导出、
     * 基金委已修改<指未通过正常订票流程，由基金委直接通过学籍信息修改完成>
     *     或已反馈<导入航空公司反馈信息>
     */
    @Column(name = "state",length=1)
    private String state;

    /**
     *备注
     */
    @Column(name = "remark",length=200)
    private String remark;

    /**
     *创建人
     */
    @Column(name = "createBy",length=20)
    private String createBy;

    /**
     *创建时间
     */
    @Column(name = "created")
    private Date created;
    /**
     *修改人
     */
    @Column(name = "updateBy",length=20)
    private String updateBy;

    /**
     *修改时间
     */
    @Column(name = "updated")
    private Date updated;

    public Date getValiddate() {
        return validdate;
    }

    public void setValiddate(Date validdate) {
        this.validdate = validdate;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
