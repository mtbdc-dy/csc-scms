package gov.gwssi.csc.scms.domain.scholarship;

import gov.gwssi.csc.scms.domain.insurance.Insurance;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/4/14.
 * ��ѧ��������ϸ��
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
     *csc�ǼǺ�
     */
    @Column(name = "cscId", length = 12)
    private String cscId;//csc�ǼǺ�

    /**
     *������(ԺУ) 0���ϸ� 1�ϸ�
     */
    @Column(name = "schReview", length = 1)
    private String schReview;//������(ԺУ)

    /**
     *��������ԺУ�� 0��ֹ 1ȡ��
     */
    @Column(name = "schResult", length = 1)
    private String schResult;//��������ԺУ��

    /**
     *������������ί�� 0���ϸ� 1�ϸ�
     */
    @Column(name = "cscReview", length = 1)
    private String cscReview;//������������ί��

    /**
     *������������ί�� 0��ֹ 1ȡ��
     */
    @Column(name = "cscRresult", length = 1)
    private String cscRresult;//������������ί��

    /**
     *����ԭ��
     */
    @Column(name = "reason", length = 300)
    private String reason;//����ԭ��

    /**
     *���ϸ�ʱ����
     */
    @Column(name = "startTime")
    private Date startTime;//���ϸ�ʱ����

    /**
     *���ϸ�ʱ��ֹ
     */
    @Column(name = "endTime")
    private Date endTime;//���ϸ�ʱ��ֹ

    /**
     * ����id
     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "SCHOLARSHIPID")
//    private Scholarship scholarship;
}
