package gov.gwssi.csc.scms.domain.query;

import java.util.Date;

/**
 * Created by lzs on 2015/6/1.
 * ��Ʊ������Ϣ�б�����
 */
public class TicketResultObject  extends ResultObject{
    /**
     * ѧ��ID
     */
    private String id;
    /**
     * CSCID
     */
    private String cscId;
    /**
     * ��������
     */
    private String passportName;
    /**
     * �Ա�
     */
    private String gender;
    /**
     * ��������
     */
    private Date birthday;

    /**
     * ѧ����Ϣ
     * ֤������
     */
    private String certificateNO;
    /**
     * ѧ����Ϣ
     * ѧ�����
     */
    private String studentType;
    /**
     * ����
     */
    private String country;

    /**
     * ѧ����Ϣ
     * ����״̬��0δ���� 1���� 2����������
     */
    private String registerState;

}
