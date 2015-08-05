package gov.gwssi.csc.scms.service.export;

import gov.gwssi.csc.scms.dao.export.ExportDAO;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gc on 2015/7/17.
 * 保险服务类
 */
@Service("exportService")
public class ExportService extends BaseService {

    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private ExportDAO exportDAO;

    public void exportByfilter(String tablename, String ids) {
        List exportList = exportDAO.getExportList(tablename);
        List seachList = exportDAO.getSeachList(tablename);//除标题行外
        String sql = "select ";//查询语句
        String headArray[][] = new String[1][seachList.size()];
        String headArray1[][] = new String[1][seachList.size()];
        String titleExcel = "";//excel标题
        int columnLength[] = new int[seachList.size()];//字段显示宽度
        for (int i = 0; i < exportList.size(); i++) {//全部配置信息
            HashMap map = (HashMap) exportList.get(i);
            if ((map.get("TITLE").toString()).equals("0")) {//excel大标题
                titleExcel = map.get("COLCH").toString();
            }
        }
        for (int i = 0; i < seachList.size(); i++) {//非标题行
            HashMap map = (HashMap) seachList.get(i);
            headArray[0][i] = map.get("COLCH").toString();
            headArray1[0][i] = map.get("COLEN").toString();
            columnLength[i] = Integer.parseInt(map.get("WIDTH").toString());
            sql += map.get("COLEN").toString() + ',';
        }
        String id[] = ids.split(",");//前台结果集条件
        String idins = "";
        for (int i = 0; i < id.length; i++) {
            idins = idins + "'" + id[i] + "',";
        }
        sql = sql.substring(0, sql.length() - 1) + " from " + tablename + " where 1=1 ";
        sql = sql + " and id in(" + idins.substring(0, idins.length() - 1) + ")";
        List resultList = exportDAO.getListbysql(sql);
        List<String[]> recordList = new ArrayList<String[]>();//结果集展示数组
        for (int i = 0; i < resultList.size(); i++) {
            HashMap map = (HashMap) resultList.get(i);
            String result[] = new String[headArray1[0].length];
            for (int k = 0; k < headArray1[0].length; k++) {
                String colen = headArray1[0][k].toUpperCase();//map中区分大小写问题
                if (map.get(colen) != null) {
                    result[k] = map.get(colen).toString();
                } else {
                    result[k] = "";
                }
            }
            recordList.add(result);
        }

        String hjh[] = null;
        int mergeArray[][] = null;
        //导出excel
        ExcelExportUtil es = new ExcelExportUtil();

        short excelAlginArray[] = {HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER};
        String dir = "C:/jjw";
        String dirTmp = "C:/jjw/tmp";
        int maxJlsl = 1000;//一个excel中显示的最多纪录数，超过时，分多个进行导出，并压缩打包
        try {
            es.writeExcel(titleExcel, recordList, hjh, headArray, mergeArray, columnLength, excelAlginArray, dir, dirTmp, maxJlsl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //生成保险管理清单
//    public List<InsuranceResultObject> getInsuranceList(User user) {
//        List listParameter = new ArrayList();
//        List<InsuranceResultObject> InsuranceResultObjectList;
//        listParameter.add("1");//传入“1”：正式
//        insuranceDAO.doSt("p_scms_insurance",listParameter);//调用存储生成当年需要投保的保单记录
//        int startPosition, pageSize;
//
//        String sql = getSql(user);
//        if (sql == null) {
//            return null;
//        }
//
//
//            startPosition =FilterObject.OFFSETDEFULT;
//            pageSize =FilterObject.PAGESIZEDEFULT;
//
//
//        InsuranceResultObjectList = super.getBaseDao().getObjectListByHQL(sql, InsuranceResultObject.class, startPosition, pageSize);
//        return InsuranceResultObjectList;
//
//    }

}
