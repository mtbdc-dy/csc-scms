package gov.gwssi.csc.scms.domain.dynamicReport;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by WangZhenghua on 2015/5/13.
 * 动态查询统计表头配置信息表
 */

@Entity
@Table(name ="SCMS_D_HEADER")
public class DHeader implements Serializable{
    @Id
    private String id;
    // 配置编号
    private String configId;
    // 单元格行坐标
    private String xcoordinate;
    // 单元格列坐标
    private String ycoordinate;
    // 单元格值
    private String value;

    public DHeader() {
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

    public String getXcoordinate() {
        return xcoordinate;
    }

    public void setXcoordinate(String xcoordinate) {
        this.xcoordinate = xcoordinate;
    }

    public String getYcoordinate() {
        return ycoordinate;
    }

    public void setYcoordinate(String ycoordinate) {
        this.ycoordinate = ycoordinate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
