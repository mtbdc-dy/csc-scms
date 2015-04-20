package gov.gwssi.csc.scms.domain.ticket;

import gov.gwssi.csc.scms.domain.scholarship.ScholarshipDetail;

import javax.persistence.*;
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
    @SequenceGenerator(name = "SCMS_AIRTICKET_ID",sequenceName = "SCMS_AIRTICKET_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "SCMS_AIRTICKET_ID",strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     *院校 可以不需要 待定
     */
    @Column(name = "school",length=4)
    private String school;

    /**
     *总人数
     */
    @Column(name = "totalNum",length=8)
    private Long totalNum;

    /**
     *导出人数
     */
    @Column(name = "expNum",length=8)
    private Long expNum;

    /**
     *未导出人数
     */
    @Column(name = "unExpNum",length=8)
    private Long unExpNum;

    /**
     *反馈人数
     */
    @Column(name = "backNum",length=8)
    private Long backNum;

    /**
     *用户ID
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
     * 机票明细
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticketid")
    private List<TicketDetail> ticketDetail = new ArrayList<TicketDetail>();
}
