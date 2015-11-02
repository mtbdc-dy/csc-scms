package gov.gwssi.csc.scms.domain.dictionary;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * 代码单元
 * Created by wangzishi on 15/10/29.
 */
@Entity
@Table(name = "V_DIM_ALL")
public class Code {
    private String id;
    private String value;

    @Id
    @Column(name = "TRANSLATEID")
    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    @Column(name = "NAMECH")
    public String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }
}
