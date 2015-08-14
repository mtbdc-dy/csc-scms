package gov.gwssi.csc.scms.domain.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wangzishi on 15/7/17.
 * 代码表键值对照类
 */
@Entity
@Table(name = "DIM_CLASS")
public class CodeTableClass {

    @Id
    @Column(name = "classId")
    private String classId;

    @Column
    private String classCh;

    @Column
    private String classEn;

    public String getClassId() {
        return classId;
    }

    public String getClassCh() {
        return classCh;
    }

    public String getClassEn() {
        return classEn;
    }
}
