package gov.gwssi.csc.scms.domain.scholarship;

import gov.gwssi.csc.scms.domain.insurance.Insurance;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/14.
 * 奖学金评审明细表
 */
@Entity
@Table(name = "SCMS_SCHOLARSHIP_DETAIL")
public class ScholarshipDetail {
    @Id
    @SequenceGenerator(name = "SCMS_SCHOLARSHIP_DETAIL_ID",sequenceName = "SCHOLARSHIP_DETAIL_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "SCMS_SCHOLARSHIP_DETAIL_ID",strategy = GenerationType.SEQUENCE)
    @Column(length = 16)
    private Long id;

    /**
     *csc登记号
     */
    @Column(name = "cscId", length = 12)
    private String cscId;//csc登记号

    /**
     *评审结果(院校) 0不合格 1合格
     */
    @Column(name = "schReview", length = 1)
    private String schReview;//评审结果(院校)

    /**
     *处理结果（院校） 0中止 1取消
     */
    @Column(name = "schResult", length = 1)
    private String schResult;//处理结果（院校）

    /**
     *评审结果（基金委） 0不合格 1合格
     */
    @Column(name = "cscReview", length = 1)
    private String cscReview;//评审结果（基金委）

    /**
     *处理结果（基金委） 0中止 1取消
     */
    @Column(name = "cscRresult", length = 1)
    private String cscRresult;//处理结果（基金委）

    /**
     *处理原因
     */
    @Column(name = "reason", length = 300)
    private String reason;//处理原因

    /**
     *不合格时间起
     */
    @Column(name = "startTime")
    private Date startTime;//不合格时间起

    /**
     *不合格时间止
     */
    @Column(name = "endTime")
    private Date endTime;//不合格时间止

    /**
     * 主表id
     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "SCHOLARSHIPID")
//    private Scholarship scholarship;
}
