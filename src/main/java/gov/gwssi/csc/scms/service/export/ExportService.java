package gov.gwssi.csc.scms.service.export;

import gov.gwssi.csc.scms.dao.export.ExportDAO;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.OutputStream;
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

    public byte[] exportByfilter(String tablename,String subtable, String[] id) {
        String idins = "";
        if(id!=null){
            for (int i = 0; i < id.length; i++) {
                idins = idins + "'" + id[i] + "',";
            }
        }

        List exportList = exportDAO.getExportList(tablename,subtable);
        List seachList = exportDAO.getSeachList(tablename,subtable);//除标题行外
        List headarrayList = exportDAO.getHeadarrayList(tablename,subtable);//所有标题行

        List mergeList = exportDAO.getMergeList(tablename,subtable);//需要合并的标题
        String sql = "select ";//查询语句
        int headarrayint = exportDAO.getHeadarrayInt(tablename,subtable);//动态标题行数
        String headArray[][] = new String[headarrayint][seachList.size()];//需要显示的标题行
        String headArray1[][] = new String[1][seachList.size()];//查询的字段，英文，和数据库对应
        String titleExcel = "";//excel标题
        int columnLength[] = new int[seachList.size()];//字段显示宽度
        int mergeArray[][];
        if (mergeList.size() > 0) {//存在需要合并的行或者列
            mergeArray = new int[mergeList.size()][4];
        } else {
            mergeArray = null;
        }
        ///------
        if (headarrayint > 1) {//多于一行标题，需要进行多行标题和合并行列的处理
            for (int i = 0; i < mergeList.size(); i++) {//构造合并数组mergeArray
                HashMap map = (HashMap) mergeList.get(i);
                int title = Integer.parseInt(map.get("TITLE").toString());//所处的标题行的位置，从100开始
                int zb = Integer.parseInt(map.get("SEQ").toString());
                int mergec = 1;
                int mergel = 1;
                if (map.get("MERGECOLUMN") != null) {
                    mergec = Integer.parseInt(map.get("MERGECOLUMN").toString());
                }
                if (map.get("MERGELINE") != null) {
                    mergel = Integer.parseInt(map.get("MERGELINE").toString());
                }
                int[] temp = {title - 100, zb, title - 100 + mergel - 1, zb + mergec - 1};
                mergeArray[i] = temp;
            }
            for (int i = 0; i < headarrayint - 1; i++) {//需要构造的标题行循环
                for (int j = i * seachList.size(); j < (i + 1) * seachList.size(); j++) {//在最大列数中循环，即，title=99，的个数，得到一行标题
                    HashMap map = (HashMap) headarrayList.get(j);
                    if (map.get("COLCH") != null) {
                        headArray[i][j - i * seachList.size()] = map.get("COLCH").toString();
                    } else {
                        headArray[i][j - i * seachList.size()] = "";
                    }
                }
            }
        }
//------

        for (int i = 0; i < exportList.size(); i++) {//全部配置信息
            HashMap map = (HashMap) exportList.get(i);
            if ((map.get("TITLE").toString()).equals("0")) {//excel大标题
                titleExcel = map.get("COLCH").toString();
            }
        }
        for (int i = 0; i < seachList.size(); i++) {//title=99
            HashMap map = (HashMap) seachList.get(i);
            headArray[headarrayint - 1][i] = map.get("COLCH").toString();//TITLE=99,构造标题行数组headArray
            headArray1[0][i] = map.get("COLEN").toString();
            columnLength[i] = Integer.parseInt(map.get("WIDTH").toString());
            sql += map.get("COLEN").toString() + ',';
        }
        sql = sql.substring(0, sql.length() - 1) + " from " + tablename + " where 1=1 ";
        if(idins.length()>0){
            sql = sql + " and id in(" + idins.substring(0, idins.length() - 1) + ")";
        }else{
            sql = sql + " and id in('')";
        }
        List resultList = exportDAO.getListbysql(sql);
        List<String[]> recordList = new ArrayList<String[]>();//结果集展示数组
        for (int i = 0; i < resultList.size(); i++) {
            HashMap map = (HashMap) resultList.get(i);
            String result[] = new String[headArray1[0].length];
            for (int k = 0; k < headArray1[0].length; k++) {
                String colen = headArray1[0][k].toUpperCase();//map中区分大小写问题
                if (map.get(colen) != null) {
                    if("v_scholarship_lastyear".equals(tablename) && "SCHOOL".equals(colen)){
                        String sCode = map.get(colen).toString();
                        result[k] = baseDAO.getUnivByUnivId(sCode);
                    }else {
                        result[k] = map.get(colen).toString();
                    }
                } else {
                    result[k] = "";
                }
            }
            recordList.add(result);
        }

        String hjh[] = null;// {"合计","3","5","6","7","8","9","10"};


//导出excel
        ExcelExportUtil es = new ExcelExportUtil();
        short excelAlginArray[] = {HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER};
        String dir = "./exports/excel/";
        String dirTmp = "./exports/tmp";
        int maxJlsl = 1000;//一个excel中显示的最多纪录数，超过时，分多个进行导出，并压缩打包
        byte[] bytes = new byte[0];
        try {
            String filePath = es.writeExcel(titleExcel, recordList, hjh, headArray, mergeArray, columnLength, excelAlginArray, dir, dirTmp, maxJlsl);
            FileInputStream in = new FileInputStream(filePath);
            int size = in.available();
            bytes = new byte[size];
            in.read(bytes);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bytes;
    }




    //生成多个sheet页的导出
    public byte[] exportByfilter(String[] tablename, String subtable,String[] id) {
        String idins = "";

        for (int i = 0; i < id.length; i++) {
            idins = idins + "'" + id[i] + "',";
        }
        //定义传入参数数组
        ArrayList titleExcels=new ArrayList();
        ArrayList recordLists=new ArrayList();
        ArrayList headArrays=new ArrayList();
        ArrayList mergeArrays=new ArrayList();
        ArrayList columnLengths=new ArrayList();
        
        for (int k = 0; k < tablename.length; k++) {
            List exportList = exportDAO.getExportList(tablename[k],subtable);
            List seachList = exportDAO.getSeachList(tablename[k],subtable);//除标题行外
            List headarrayList = exportDAO.getHeadarrayList(tablename[k],subtable);//所有标题行

            List mergeList = exportDAO.getMergeList(tablename[k],subtable);//需要合并的标题
            String sql = "select ";//查询语句
            int headarrayint = exportDAO.getHeadarrayInt(tablename[k],subtable);//动态标题行数
            String headArray[][] = new String[headarrayint][seachList.size()];//需要显示的标题行
            String headArray1[][] = new String[1][seachList.size()];//查询的字段，英文，和数据库对应
            String titleExcel = "";//excel标题
            int columnLength[] = new int[seachList.size()];//字段显示宽度
            int mergeArray[][];
            if (mergeList.size() > 0) {//存在需要合并的行或者列
                mergeArray = new int[mergeList.size()][4];
            } else {
                mergeArray = null;
            }
            ///------
            if (headarrayint > 1) {//多于一行标题，需要进行多行标题和合并行列的处理
                for (int i = 0; i < mergeList.size(); i++) {//构造合并数组mergeArray
                    HashMap map = (HashMap) mergeList.get(i);
                    int title = Integer.parseInt(map.get("TITLE").toString());//所处的标题行的位置，从100开始
                    int zb = Integer.parseInt(map.get("SEQ").toString());
                    int mergec = 1;
                    int mergel = 1;
                    if (map.get("MERGECOLUMN") != null) {
                        mergec = Integer.parseInt(map.get("MERGECOLUMN").toString());
                    }
                    if (map.get("MERGELINE") != null) {
                        mergel = Integer.parseInt(map.get("MERGELINE").toString());
                    }
                    int[] temp = {title - 100, zb, title - 100 + mergel - 1, zb + mergec - 1};
                    mergeArray[i] = temp;
                }
                for (int i = 0; i < headarrayint - 1; i++) {//需要构造的标题行循环
                    for (int j = i * seachList.size(); j < (i + 1) * seachList.size(); j++) {//在最大列数中循环，即，title=99，的个数，得到一行标题
                        HashMap map = (HashMap) headarrayList.get(j);
                        if (map.get("COLCH") != null) {
                            headArray[i][j - i * seachList.size()] = map.get("COLCH").toString();
                        } else {
                            headArray[i][j - i * seachList.size()] = "";
                        }
                    }
                }
                mergeArrays.add(mergeArray);
            }
//------


            for (int i = 0; i < exportList.size(); i++) {//全部配置信息
                HashMap map = (HashMap) exportList.get(i);
                if ((map.get("TITLE").toString()).equals("0")) {//excel大标题
                    titleExcel = map.get("COLCH").toString();
                }
            }
            titleExcels.add(titleExcel);
            for (int i = 0; i < seachList.size(); i++) {//title=99
                HashMap map = (HashMap) seachList.get(i);
                headArray[headarrayint - 1][i] = map.get("COLCH").toString();//TITLE=99,构造标题行数组headArray
                headArray1[0][i] = map.get("COLEN").toString();
                columnLength[i] = Integer.parseInt(map.get("WIDTH").toString());
                sql += map.get("COLEN").toString() + ',';
            }
            headArrays.add(headArray);
            columnLengths.add(columnLength);
            sql = sql.substring(0, sql.length() - 1) + " from " + tablename[k] + " where 1=1 ";
            sql = sql + " and id in(" + idins.substring(0, idins.length() - 1) + ")";
            List resultList = exportDAO.getListbysql(sql);
            List<String[]> recordList = new ArrayList<String[]>();//结果集展示数组
            for (int i = 0; i < resultList.size(); i++) {
                HashMap map = (HashMap) resultList.get(i);
                String result[] = new String[headArray1[0].length];
                for (int k1 = 0; k1 < headArray1[0].length; k1++) {
                    String colen = headArray1[0][k1].toUpperCase();//map中区分大小写问题
                    if (map.get(colen) != null) {
                        result[k1] = map.get(colen).toString();
                    } else {
                        result[k1] = "";
                    }
                }
                recordList.add(result);
            }
            recordLists.add(recordList);
        }
        String hjh[] = null;// {"合计","3","5","6","7","8","9","10"};


//导出excel
        ExcelExportUtil es = new ExcelExportUtil();
       short excelAlginArray[] = {HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER};
       String dir = "./exports/excel/";
       String dirTmp = "./exports/tmp";
        int maxJlsl = 10000;//一个excel中显示的最多纪录数，后台设置一个相对较大的值，导出多个sheet页时，不因为记录数据过多而生成多个excel文件
        byte[] bytes = new byte[0];
        try {
            String filePath = es.writeExcel(titleExcels, recordLists, hjh, headArrays, mergeArrays, columnLengths, excelAlginArray, dir, dirTmp, maxJlsl);
            FileInputStream in = new FileInputStream(filePath);
            int size = in.available();
            bytes = new byte[size];
            in.read(bytes);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bytes;
    }

}
