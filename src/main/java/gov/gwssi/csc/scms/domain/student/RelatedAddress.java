package gov.gwssi.csc.scms.domain.student;

import javax.persistence.*;

/**相关地址
 * Created by Wang Rui on 2015/3/30.
 */
@Entity
@Table(name = "SCMS_RELATED_ADDRESS")
public class RelatedAddress {
    @Id private Long id;
    @ManyToOne
    private Student student;
    private String type;//类别
    private String nature;//性质
    private String address_or_name;//详细地址/紧急联系人姓名
    private String phone;//电话
    private String fax;//传真
    private String email;//邮箱
    private String remark;//备注

    @ManyToOne(optional = false)
    private Student students;

    public Student getStudents() {
        return students;
    }

    public void setStudents(Student students) {
        this.students = students;
    }
}
