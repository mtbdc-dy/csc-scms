package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.Date;

/**商议信息
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "SCMS_DISCUSS")
public class Discuss {
    @Id private Long id;
    @OneToOne private Student student;
    private String appropriations;//经费办法
    private String stu_type;//学生类别
    private String teach_language;//授课语言
    private String disciplines;//学科门类
    private String subject;//一级学科
    private String major;//专业
    private Boolean chn_frm_needed;//是否汉补
    private Date chn_frm_start_time;//汉补开始时间
    private Date chn_frm_end_time;//汉补结束时间
    private String province_1;//省市1
    private String province_2;//省市2
    private String province_3;//省市3
    private String university_1;//院校1
    private String university_2;//院校2
    private String university_3;//院校3
    private String matriculate;//商议状态 1表 2录 3否 4待 5发 6备 7单报
    private String handle_status;//经办情况
    private String remark;//备注

    }
