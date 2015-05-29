package gov.gwssi.csc.scms.domain.ticket;

import gov.gwssi.csc.scms.domain.scholarship.ScholarshipDetail;
import gov.gwssi.csc.scms.domain.student.Student;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wang Rui on 2015/4/16.
 * 机票管理
 */
@Entity
@Table(name = "SCMS_AIRTICKET")
public class Ticket {
    @Id

    private String id;
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "studentid")
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
    @Column(name = "price",length=16)
    private long price;
    /**
     *订票状态 0未导出 1已导出 2已反馈
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
    private String userName;

    /**
     *修改时间
     */
    @Column(name = "updated")
    private Date updated;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
