package gov.gwssi.csc.scms.domain.insurance;

import gov.gwssi.csc.scms.domain.student.Grade;

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
    @SequenceGenerator(name = "SCMS_INSURANCE_ID",sequenceName = "SCMS_INSURANCE_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "SCMS_INSURANCE_ID",strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     *保险种类 0预定 1正式
     */
    @Column(name = "insurSta",length=1)
    private String insurSta;

    /**
     *总人数
     */
    @Column(name = "totalNum",length=8)
    private Long totalNum;

    /**
     *导出人数
    /**
     *用户id
     */
    @Column(name = "userId",length=20)
    private String userId;

    /**
     *用户名
     */
    @Column(name = "userName",length=50)
    private String userName;

    /**
     *创建时间
     */
    @Column(name = "time")
    private Date time;
    /**
     * 保险明细
     */
    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "insuranceid")
  //  private List<InsuranceDetail> Grades = new ArrayList<InsuranceDetail>();

}
