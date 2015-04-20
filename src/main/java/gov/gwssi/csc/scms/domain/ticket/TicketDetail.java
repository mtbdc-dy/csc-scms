package gov.gwssi.csc.scms.domain.ticket;

import gov.gwssi.csc.scms.domain.scholarship.Scholarship;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/16.
 * ��Ʊ������ϸ
 */
@Entity
@Table(name = "SCMS_AIRTICKET_DETAIL")
public class TicketDetail {
    @Id
    @SequenceGenerator(name = "SCMS_AIRTICKET_DETAIL_ID",sequenceName = "SCMS_AIRTICKET_DETAIL_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "SCMS_AIRTICKET_DETAIL_ID",strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     *CSC�ǼǺ�
     */
    @Column(name = "cscId",length=12)
    private String cscId;
    /**
     *����˻�����
     */
    @Column(name = "applyDate")
    private Date applyDate;

    /**
     *�뾳����
     */
    @Column(name = "leaveCity",length=100)
    private String leaveCity;

    /**
     *��Ʊ���� 0���� 1�ع�
     */
    @Column(name = "type",length=1)
    private String type;

    /**
     *��Ʊ״̬ 0δ���� 1�ѵ��� 2�ѷ���
     */
    @Column(name = "state",length=1)
    private String state;

    /**
     *�˻�����
     */
    @Column(name = "flightDate")
    private Date flightDate;

    /**
     *����
     */
    @Column(name = "airLine",length=100)
    private String airLine;

    /**
     *��Ʊ�۸�Ԫ��
     */
    @Column(name = "price",length=16)
    private Double price;

    /**
     *��Ʊ����
     */
    @Column(name = "ticketNo",length=100)
    private String ticketNo;

    /**
     *��ע
     */
    @Column(name = "remark",length=200)
    private String remark;

    /**
     *
     * ����id
     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "TICKETID")
//    private Ticket ticket;

}
