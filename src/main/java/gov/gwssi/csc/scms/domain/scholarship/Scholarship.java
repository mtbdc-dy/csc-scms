package gov.gwssi.csc.scms.domain.scholarship;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wang Rui on 2015/4/14.
 * 奖学金评审
 */
@Entity
@Table(name = "SCMS_SCHOLARSHIP")
public class Scholarship {
    @Id
    @SequenceGenerator(name = "SCMS_SCHOLARSHIP_ID",sequenceName = "SCHOLARSHIP_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "SCMS_SCHOLARSHIP_ID",strategy = GenerationType.SEQUENCE)
    @Column(length = 16)
    private Long id;
    /**
     *院校 DIM_UNIVERSITY代码
     */
    @Column(name = "school",length=4)
    private String school;
    /**
     *年度
     */
    @Column(name = "year",length=4)
    private String year;

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
     *创建日期
     */
    @Column(name = "time")
    private Date time;

    /**
     *合格人数
     */
    @Column(name = "qualNum",length=8)
    private Long qualNum;

    /**
     *不合格人数
     */
    @Column(name = "unQualNum",length=8)
    private Long unQualNum;

    /**
     * 奖学金明细表
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "scholarshipid")
    private List<ScholarshipDetail> scholarshipDetail = new ArrayList<ScholarshipDetail>();

}
