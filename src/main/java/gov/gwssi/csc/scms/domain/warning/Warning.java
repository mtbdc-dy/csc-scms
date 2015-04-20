package gov.gwssi.csc.scms.domain.warning;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/16.
 */
@Entity
@Table(name = "SCMS_WARNING")
public class Warning {
    @Id
    @SequenceGenerator(name = "SCMS_WARNING_ID",sequenceName = "SCMS_WARNING_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "SCMS_WARNING_ID",strategy = GenerationType.SEQUENCE)

    /**
     *CSC登记号
     */
    @Column(name = "cscId",length=12)
    private String cscId;

    /**
     *加入预警名单原因
     */
    @Column(name = "addReason",length=300)
    private String addReason;

    /**
     *加入用户ID
     */
    @Column(name = "addUserId",length=20)
    private String addUserId;

    /**
     *加入用户名
     */
    @Column(name = "addUserName",length=50)
    private String addUserName;

    /**
     *加入时间
     */
    @Column(name = "addTime")
    private Date addTime;

    /**
     *移除预警名单原因
     */
    @Column(name = "rmReason",length=300)
    private String rmReason;

    /**
     *移除用户ID
     */
    @Column(name = "rmUserId",length=20)
    private String rmUserId;

    /**
     *移除用户名
     */
    @Column(name = "rmUserName",length=50)
    private String rmUserName;

    /**
     *移除时间
     */
    @Column(name = "rmTime")
    private Date rmTime;
}
