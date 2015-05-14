package gov.gwssi.csc.scms.domain.statistics;

import java.io.Serializable;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 所有表清单
 */

public class DimTable implements Serializable{

    // 表名（英）
    private String tableEn;
    // 表名（中）
    private String tableCh;
    // 表类型；1代码表；2业务表
    private String tableType;

    public DimTable() {
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

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
}
