package gov.gwssi.csc.scms.domain.scholarship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gov.gwssi.csc.scms.domain.student.Student;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gaochen 20150817.
 * 奖学金评审
 */
@Entity
@Table(name = "V_SCHOLARSHIP_LASTYEAR")
public class ScholarshipX implements Comparable<ScholarshipX>
{
    @Id
    //@Column(length = 19)
    private String id;

    /**
     * 学生
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENTID")
    private Student student;

    @Column
    private String cscId;
    @Column
    private String scholarshipId;
    @Column
    private String schReview;
    @Column
    private String cscReview;
    @Column
    private String schResult;
    @Column
    private String cscResult;
    @Column
    private String schReason;
    @Column
    private Date   schStartTime;
    @Column
    private Date   schEndTime;
    @Column
    private String cscReason;
    @Column
    private Date   cscStartTime;
    @Column
    private Date   cscEndTime;
    @Column
    private Long   schoolQual;
    @Column
    private Long   schoolUnQual;
    @Column
    private Long   cscQual;
    @Column
    private Long   cscUnQual;
    @Column
    private Date   updated;
    @Column
    private String updateby;
    @Column
    private Date   created;
    @Column
    private String schoolSta;
    @Column
    private String cscSta;
    @Column
    private String cscrresult_lastyear;
    @Column
    private String passportName;

    /**
     * 年度
     */
    @Column
    private long   year;
    /**
     * 院校 DIM_UNIVERSITY代码
     */
    @Column
    private String school;

    public String getPassportName()
    {
        return passportName;
    }

    public void setPassportName(String passportName)
    {
        this.passportName = passportName;
    }

    public Long getSchoolQual()
    {
        return schoolQual;
    }

    public void setSchoolQual(Long schoolQual)
    {
        this.schoolQual = schoolQual;
    }

    public Long getSchoolUnQual()
    {
        return schoolUnQual;
    }

    public void setSchoolUnQual(Long schoolUnQual)
    {
        this.schoolUnQual = schoolUnQual;
    }

    public Long getCscQual()
    {
        return cscQual;
    }

    public void setCscQual(Long cscQual)
    {
        this.cscQual = cscQual;
    }

    public Long getCscUnQual()
    {
        return cscUnQual;
    }

    public void setCscUnQual(Long cscUnQual)
    {
        this.cscUnQual = cscUnQual;
    }

    public String getSchoolSta()
    {
        return schoolSta;
    }

    public void setSchoolSta(String schoolSta)
    {
        this.schoolSta = schoolSta;
    }

    public String getCscSta()
    {
        return cscSta;
    }

    public void setCscSta(String cscSta)
    {
        this.cscSta = cscSta;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public long getYear()
    {
        return year;
    }

    public void setYear(long year)
    {
        this.year = year;
    }

    public String getSchool()
    {
        return school;
    }

    public void setSchool(String school)
    {
        this.school = school;
    }


    public String getCscReview()
    {
        return cscReview;
    }

    public void setCscReview(String cscReview)
    {
        this.cscReview = cscReview;
    }


    public String getScholarshipId()
    {
        return scholarshipId;
    }

    public void setScholarshipId(String scholarshipId)
    {
        this.scholarshipId = scholarshipId;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public void setUpdated(Date updated)
    {
        this.updated = updated;
    }

    public String getUpdateby()
    {
        return updateby;
    }

    public void setUpdateby(String updateby)
    {
        this.updateby = updateby;
    }

    public String getSchResult()
    {
        return schResult;
    }

    public void setSchResult(String schResult)
    {
        this.schResult = schResult;
    }

    public String getCscId()
    {
        return cscId;
    }

    public void setCscId(String cscId)
    {
        this.cscId = cscId;
    }

    public String getSchReview()
    {
        return schReview;
    }

    public void setSchReview(String schReview)
    {
        this.schReview = schReview;
    }

    public String getCscResult()
    {
        return cscResult;
    }

    public void setCscResult(String cscResult)
    {
        this.cscResult = cscResult;
    }

    public String getSchReason()
    {
        return schReason;
    }

    public void setSchReason(String schReason)
    {
        this.schReason = schReason;
    }

    public Date getSchStartTime()
    {
        return schStartTime;
    }

    public void setSchStartTime(Date schStartTime)
    {
        this.schStartTime = schStartTime;
    }

    public Date getSchEndTime()
    {
        return schEndTime;
    }

    public void setSchEndTime(Date schEndTime)
    {
        this.schEndTime = schEndTime;
    }

    public String getCscReason()
    {
        return cscReason;
    }

    public void setCscReason(String cscReason)
    {
        this.cscReason = cscReason;
    }

    public Date getCscStartTime()
    {
        return cscStartTime;
    }

    public void setCscStartTime(Date cscStartTime)
    {
        this.cscStartTime = cscStartTime;
    }

    public Date getCscEndTime()
    {
        return cscEndTime;
    }

    public void setCscEndTime(Date cscEndTime)
    {
        this.cscEndTime = cscEndTime;
    }

    public String getCscrresult_lastyear()
    {
        return cscrresult_lastyear;
    }

    public void setCscrresult_lastyear(String cscrresult_lastyear)
    {
        this.cscrresult_lastyear = cscrresult_lastyear;
    }


    public Student getStudent()
    {
        return student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public int compareTo(ScholarshipX that)
    {
        int result = ((Long) this.getYear()).compareTo(that.getYear());
        result = result == 0 ? this.getCreated().compareTo(that.getCreated()) : result;
        return result;
    }
}
