package gov.gwssi.csc.scms.domain.ticket;

import gov.gwssi.csc.scms.domain.scholarship.Scholarship;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/16.
 * 机票管理明细
 */
@Entity
@Table(name = "SCMS_AIRTICKET_DETAIL")
public class TicketDetail {
    @Id
    @SequenceGenerator(name = "SCMS_AIRTICKET_DETAIL_ID",sequenceName = "SCMS_AIRTICKET_DETAIL_SEQ",allocationSize = 1)
    @GeneratedValue(generator = "SCMS_AIRTICKET_DETAIL_ID",strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     *CSC登记号
     */
    @Column(name = "cscId",length=12)
    private String cscId;
    /**
     *申请乘机日期
     */
    @Column(name = "applyDate")
    private Date applyDate;

    /**
     *离境城市
     */
    @Column(name = "leaveCity",length=100)
    private String leaveCity;

    /**
     *机票类型 0来华 1回国
     */
    @Column(name = "type",length=1)
    private String type;

    /**
     *订票状态 0未导出 1已导出 2已反馈
     */
    @Column(name = "state",length=1)
    private String state;

    /**
     *乘机日期
     */
    @Column(name = "flightDate")
    private Date flightDate;

    /**
     *航线
     */
    @Column(name = "airLine",length=100)
    private String airLine;

    /**
     *机票价格（元）
     */
    @Column(name = "price",length=16)
    private Double price;

    /**
     *机票号码
     */
    @Column(name = "ticketNo",length=100)
    private String ticketNo;

    /**
     *备注
     */
    @Column(name = "remark",length=200)
    private String remark;

    /**
     *
     * 主表id
     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "TICKETID")
//    private Ticket ticket;

}
