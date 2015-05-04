package gov.gwssi.csc.scms.domain.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Lei on 2015/5/4.
 * 权限表
 */
@Entity
@Table(name = "DIM_REGION")
public class Right {
    /**
     * 地区Id
     */
    @Id
    private String regionId;
    /**
     * 地区名称（英文）
     */
    private String maneEN;
    /**
     * 地区名称（汉语）
     */
    private String nameCH;
    /**
     * 上级节点
     */
    private String parentId;
    /**
     * 地域类型：0洲/项目类别；1国家/项目名称；2省市、
     */
    private String regionType;
    /**
     * 类型（前缀）：A  洲；E  国家；T  项目类别；U  项目名称。
     */
    private String type;
    /**
     * 地域编号
     */
    private String regionno;
    /**
     *有效标志：0不启用；1启用
     */
    private String enabled;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getManeEN() {
        return maneEN;
    }

    public void setManeEN(String maneEN) {
        this.maneEN = maneEN;
    }

    public String getNameCH() {
        return nameCH;
    }

    public void setNameCH(String nameCH) {
        this.nameCH = nameCH;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(String regionType) {
        this.regionType = regionType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegionno() {
        return regionno;
    }

    public void setRegionno(String regionno) {
        this.regionno = regionno;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
