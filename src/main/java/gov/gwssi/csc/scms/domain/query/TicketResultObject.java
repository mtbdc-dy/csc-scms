package gov.gwssi.csc.scms.domain.query;

import java.util.Date;

/**
 * Created by lzs on 2015/6/1.
 * 机票管理信息列表结果类
 */
public class TicketResultObject  extends ResultObject{
    /**
     * 学生ID
     */
    private String id;
    /**
     * CSCID
     */
    private String cscId;
    /**
     * 护照姓名
     */
    private String passportName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 学籍信息
     * 证件号码
     */
    private String certificateNO;
    /**
     * 学籍信息
     * 学生类别
     */
    private String studentType;
    /**
     * 国籍
     */
    private String country;

    /**
     * 学籍信息
     * 报到状态（0未处理 1报到 2放弃来华）
     */
    private String registerState;

}
