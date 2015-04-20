package gov.gwssi.csc.scms.domain.insurance;

import gov.gwssi.csc.scms.domain.student.Student;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/16.
 */
@Entity
@Table(name="SCMS_INSURANCE_DETAIL")
public class InsuranceDetail {
    @Id
    @SequenceGenerator(name = "SCMS_INSURANCE_ID",sequenceName = "SCMS_INSURANCE_DETAIL_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "SCMS_INSURANCE_ID",strategy = GenerationType.SEQUENCE)
    @Column(length = 16)
    private Long id;

    /**
     *CSC登记号
     */
    @Column(name = "cscId",length=12)
    private String cscId;

    /**
     *预定状态 0未导出 1已导出 2已反馈
     */
    @Column(name = "preSta",length=1)
    private String preSta;

    /**
     *保单号
     */
    @Column(name = "insurNo",length=1)
    private String insurNo;

    //导入时间、用户是否还需要

    /**
     *
     * 主表id
     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "INSURANCEID")
//    private Insurance insurance;
}
