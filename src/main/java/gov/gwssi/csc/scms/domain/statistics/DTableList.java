package gov.gwssi.csc.scms.domain.statistics;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by lenovo on 2015/5/13.
 * 动态查询统计表清单
 */

@Entity
@Table(name ="SCMS_D_TABLELIST")
public class DTableList implements Serializable{
    @Id
    private String id;
    // 配置编号
    private String configId;
    // 序号
    private String no;
    // 数据表英文名称
    private String tableEn;
    // 数据表中文名称
    private String tableCh;

    public DTableList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTableEn() {
        return tableEn;
    }

    public void setTableEn(String tableEn) {
        this.tableEn = tableEn;
    }

    public String getTableCh() {
        return tableCh;
    }

    public void setTableCh(String tableCh) {
        this.tableCh = tableCh;
    }
}
