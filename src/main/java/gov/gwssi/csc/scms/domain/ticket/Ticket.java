package gov.gwssi.csc.scms.domain.ticket;

import gov.gwssi.csc.scms.domain.scholarship.ScholarshipDetail;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wang Rui on 2015/4/16.
 * ��Ʊ����
 */
@Entity
@Table(name = "SCMS_AIRTICKET")
public class Ticket {
    @Id
    @SequenceGenerator(name = "SCMS_AIRTICKET_ID",sequenceName = "SCMS_AIRTICKET_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "SCMS_AIRTICKET_ID",strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     *ԺУ ���Բ���Ҫ ����
     */
    @Column(name = "school",length=4)
    private String school;

    /**
     *������
     */
    @Column(name = "totalNum",length=8)
    private Long totalNum;

    /**
     *��������
     */
    @Column(name = "expNum",length=8)
    private Long expNum;

    /**
     *δ��������
     */
    @Column(name = "unExpNum",length=8)
    private Long unExpNum;

    /**
     *��������
     */
    @Column(name = "backNum",length=8)
    private Long backNum;

    /**
     *�û�ID
     */
    @Column(name = "userId",length=20)
    private String userId;

    /**
     *�û���
     */
    @Column(name = "userName",length=50)
    private String userName;

    /**
     *����ʱ��
     */
    @Column(name = "time")
    private Date time;

    /**
     * ��Ʊ��ϸ
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticketid")
    private List<TicketDetail> ticketDetail = new ArrayList<TicketDetail>();
}
