package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**
 * 来华前概况、申请信息
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name="SCMS_REGISTRATION_INFO")
public class RegistrationInfo {
    @Id private Long id;
    @OneToOne
    private Student student;
    private String stu_type;//学生类别
    private String teach_language;//授课语言
    private String disciplines;//学科门类
    private String subject;//一级学科
    private String major;//专业
    private String province_1;//省市1
    private String province_2;//省市2
    private String province_3;//省市3
    private String university_1;//院校1
    private String university_2;//院校2
    private String university_3;//院校3
}
