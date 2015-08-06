package gov.gwssi.csc.scms.dao.export;

import gov.gwssi.csc.scms.dao.BaseDAO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gc on 2015/6/1.
 * Export  DAO封装类
 */
@Service("exportDAO")
public class ExportDAO extends BaseDAO {
    public List getExportList(String tableName) {
        List exportList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * from SCMS_INI_EXPORT  ");
        stringBuilder.append(" where SCMS_INI_EXPORT.subtable='0' ");
        stringBuilder.append(" and SCMS_INI_EXPORT.tablename = '").append(tableName).append("' ");//根据表名找到配置
        stringBuilder.append("  order by seq");
        exportList = super.queryListBySql(stringBuilder.toString());
        return exportList;
    }

    //获取查询字段
    public List getSeachList(String tableName) {
        List seachList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * from SCMS_INI_EXPORT  ");
        stringBuilder.append(" where SCMS_INI_EXPORT.subtable='0' and SCMS_INI_EXPORT.title='99' ");
        stringBuilder.append(" and SCMS_INI_EXPORT.tablename = '").append(tableName).append("' ");//根据表名找到配置
        stringBuilder.append("  order by seq");
        seachList = super.queryListBySql(stringBuilder.toString());
        return seachList;
    }

    //根据传入sql 查询数据库，得到结果list
    public List getListbysql(String sql) {
        List resurtList = super.queryListBySql(sql.toString());
        return resurtList;
    }

}
