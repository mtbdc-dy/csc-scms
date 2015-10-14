package gov.gwssi.csc.scms.domain.codemaintenance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by LiZhiSheng on 2015/9/10.
 */
@Entity
@Table(name = "v_dim_maintain")
public class CodeMainTenance {
    @Id
//id
    private long seq;
    //表英文名
    @Column
    private String tableEn;
    /**
     * 层级
     */
    @Column
    private Character flag;
    /**
     * 表中文名
     */
    @Column
    private String tableCh;
    /**
     * 类别
     */
    @Column(name = "class")
    private String  category;
    /**
     * 类型
     */
    @Column
    private String type;
    /**
     * sql语句
     */
    @Column
    private String sql;

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public String getTableEn() {
        return tableEn;
    }

    public void setTableEn(String tableEn) {
        this.tableEn = tableEn;
    }

    public Character getFlag() {
        return flag;
    }

    public void setFlag(Character flag) {
        this.flag = flag;
    }

    public String getTableCh() {
        return tableCh;
    }

    public void setTableCh(String tableCh) {
        this.tableCh = tableCh;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
