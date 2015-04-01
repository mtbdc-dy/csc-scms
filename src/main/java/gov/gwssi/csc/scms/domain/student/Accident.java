package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Wang Rui on 2015/3/30.
 * 突发事件
 */
@Entity
@Table(name = "SCMS_ACCIDENT")
public class Accident {
    @Id
    private String id;
    @ManyToOne
    private Student student;
    private String responsibility_type;//责任类别
    private String type;//类别
    private String reason;//原因
    private String happen_time;//发生时间
    private String happen_place;//发生地点
    private String handle_status;//处理状态
    private String summary;//情况摘要
    private Date update_date;//记录时间
}
