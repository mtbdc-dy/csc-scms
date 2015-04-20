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
     *CSC�ǼǺ�
     */
    @Column(name = "cscId",length=12)
    private String cscId;

    /**
     *����Ԥ������ԭ��
     */
    @Column(name = "addReason",length=300)
    private String addReason;

    /**
     *�����û�ID
     */
    @Column(name = "addUserId",length=20)
    private String addUserId;

    /**
     *�����û���
     */
    @Column(name = "addUserName",length=50)
    private String addUserName;

    /**
     *����ʱ��
     */
    @Column(name = "addTime")
    private Date addTime;

    /**
     *�Ƴ�Ԥ������ԭ��
     */
    @Column(name = "rmReason",length=300)
    private String rmReason;

    /**
     *�Ƴ��û�ID
     */
    @Column(name = "rmUserId",length=20)
    private String rmUserId;

    /**
     *�Ƴ��û���
     */
    @Column(name = "rmUserName",length=50)
    private String rmUserName;

    /**
     *�Ƴ�ʱ��
     */
    @Column(name = "rmTime")
    private Date rmTime;
}
