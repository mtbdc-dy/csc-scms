package gov.gwssi.csc.scms.domain.insurance;

import gov.gwssi.csc.scms.domain.student.Grade;
import gov.gwssi.csc.scms.domain.student.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wang Rui on 2015/4/16.
 */
@Entity
@Table(name="SCMS_INSURANCE")
public class Insurance {
    @Id

    private String id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "studentid")
    private Student student;
    /**
     *保险种类 0预定 1正式
     */
    @Column(name = "insurSta",length=1)
    private String insurSta;
    /**
     *保单号
     */
    @Column(name = "insurNo",length=100)
    private String insurNo;

    /**
     *预定状态 0未导出 1已导出 2已反馈
     */
    @Column(name = "preSta",length=1)
    private String preSta;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getInsurSta() {
        return insurSta;
    }

    public void setInsurSta(String insurSta) {
        this.insurSta = insurSta;
    }

    public String getInsurNo() {
        return insurNo;
    }

    public void setInsurNo(String insurNo) {
        this.insurNo = insurNo;
    }

    public String getPreSta() {
        return preSta;
    }

    public void setPreSta(String preSta) {
        this.preSta = preSta;
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
