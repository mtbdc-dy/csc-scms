package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.Date;

/**学籍信息
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "SCMS_SCHOOLROLL")
public class SchoolRoll {
    @Id private Long id;
    @OneToOne
    private Student student;
    private String certificate_type;//证件种类
    private String register_state;//报到状态 0未处理 1报到 2放弃来华
    private String disciplines;//学科门类
    private String subject;//一级学科
    private String major;//专业
    private String teach_language;//授课语言
    private String stu_type;//学生类别
    private String colleague;//院系名称
    private Date arrival_time;//来华时间
    private String school_roll_state;//学籍状态
    private String current_university;//当前院校
    private Date plan_leave_time ;//预计离华时间
    private Long scholarship_review_year;//奖学金评审年度
    private String scholarship_review_opinion;//奖学金处理意见
    private String degree_certificate_NO;//学位证书编号
    private String certificate_NO;//证件号码
    private String appropriations;//经费办法
    private String chn_frm_province;//汉补省市
    private String chn_frm_university;//汉补院校
    private Date chn_frm_start_time;//汉补开始时间
    private Date chn_frm_end_time;//汉补结束时间
    private String major_province;//专业学习省市
    private String major_university;//专业学习院校
    private String register_NO;//学籍电子注册号
    private Date major_start_time;//入专业院校时间
    private Boolean leave_china;//是否离华
    private Date leave_time;//离华时间
    private String leave_reason;//离华原因
    private String scholarship_review_result;//奖学金评审结果
    private String academic_certificate_NO;//学历证书编号
    private String abnormal_document_NO;//异动发文编号
    private String 在学记录; //有问题
    private String remark;//备注

}
