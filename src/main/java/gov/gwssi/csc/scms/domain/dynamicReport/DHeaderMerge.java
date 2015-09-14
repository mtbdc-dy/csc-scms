package gov.gwssi.csc.scms.domain.dynamicReport;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by WangZhenghua on 2015/5/13.
 * 动态查询统计表头合并表
 */

@Entity
@Table(name ="SCMS_D_HEADERMERGE")
public class DHeaderMerge implements Serializable{
    @Id
    private String id;
    // 配置编号
    private String configId;
    // 合并开始行坐标
    private String xcoordinate1;
    // 合并开始列坐标
    private String ycoordinate1;
    // 合并结束行坐标
    private String xcoordinate2;
    // 合并结束列坐标
    private String ycoordinate2;

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

    public String getXcoordinate1() {
        return xcoordinate1;
    }

    public void setXcoordinate1(String xcoordinate1) {
        this.xcoordinate1 = xcoordinate1;
    }

    public String getYcoordinate1() {
        return ycoordinate1;
    }

    public void setYcoordinate1(String ycoordinate1) {
        this.ycoordinate1 = ycoordinate1;
    }

    public String getXcoordinate2() {
        return xcoordinate2;
    }

    public void setXcoordinate2(String xcoordinate2) {
        this.xcoordinate2 = xcoordinate2;
    }

    public String getYcoordinate2() {
        return ycoordinate2;
    }

    public void setYcoordinate2(String ycoordinate2) {
        this.ycoordinate2 = ycoordinate2;
    }
}
