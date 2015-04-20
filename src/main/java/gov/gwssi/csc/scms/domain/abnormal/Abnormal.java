package gov.gwssi.csc.scms.domain.abnormal;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/15.
 * 异动管理
 */
@Entity
@Table(name = "SCMS_ABNORMAL")
public class Abnormal {
    @Id
    @SequenceGenerator(name = "SCMS_ABNORMAL_ID",sequenceName = "SCMS_ABNORMAL_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "SCMS_ABNORMAL_ID",strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * CSCID
     */
    @Column(name = "cscId",length=12)
    private String cscId;

    /**
     *异动原因代码
     */
    @Column(name = "reasonId",length=2)
    private String reasonId;//异动原因代码
    /**
     *异动说明 20150204会议纪要 限制150字符、必填
     */
    @Column(name = "reason",length=300)
    private String reason;//异动说明

    /**
     *异动上传pdf文件路径
     */
    @Column(name = "applyUri",length=4000)
    private String applyUri;//异动上传pdf文件路径

    /**
     *审批结果 0不同意 1同意
     */
    @Column(name = "approResult",length=1)
    private String approResult;//审批结果

    /**
     *审批意见
     */
    @Column(name = "approOpinion",length=300)
    private String approOpinion;//审批意见

    /**
     *发文pdf文件路径
     */
    @Column(name = "publicUri",length=4000)
    private String publicUri;//发文pdf文件路径

    /**
     *申请人ID
     */
    @Column(name = "applyUserId",length=20)
    private String applyUserId;//申请人ID

    /**
     *申请人
     */
    @Column(name = "applyUserName",length=50)
    private String applyUserName;//申请人

    /**
     *申请日期
     */
    @Column(name = "applyTime")
    private Date applyTime;//申请日期

    /**
     *上报人ID
     */
    @Column(name = "reportUserId",length=20)
    private String reportUserId;//上报人ID
    /**
     *上报人
     */
    @Column(name = "reportUserName",length=50)
    private String reportUserName;//上报人

    /**
     *上报日期
     */
    @Column(name = "reportTime")
    private Date reportTime;//上报日期
    /**
     *审批人ID
     */
    @Column(name = "approUserId",length=20)
    private String approUserId;//审批人ID
    /**
     *审批人
     */
    @Column(name = "approUserName",length=50)
    private String approUserName;//审批人
    /**
     *审批日期
     */
    @Column(name = "approTime")
    private Date approTime;//审批日期
    /**
     *处理人ID
     */
    @Column(name = "handleUserId",length=20)
    private String handleUserId;//处理人ID
    /**
     *处理人
     */
    @Column(name = "handleUserName",length=50)
    private String handleUserName;//处理人
    /**
     *处理日期
     */
    @Column(name = "handleTime")
    private Date handleTime;//处理日期
    /**
     *处理状态 0未提交 1提交未上报 2上报未审核 3审核未处理 4 处理
     */
    @Column(name = "state",length=1)
    private String state;//处理状态 0未提交 1提交未上报 2上报未审核 3审核未处理 4 处理
}
