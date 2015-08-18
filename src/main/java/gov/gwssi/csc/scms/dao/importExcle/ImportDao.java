package gov.gwssi.csc.scms.dao.importExcle;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.utils.ExcelPoiTools;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by LiZhiSheng on 2015/8/16.
 */
@Service("importDao")
public class ImportDao extends BaseDAO {
    private static ImportDao manager;

    public static ImportDao getInstance(){
        if(manager == null){
            manager = new ImportDao();
        }
        return manager;
    }
    @Transactional
    public Vector<Vector<String>> doExcelImport(FileItem fileItem) throws IOException{
        Vector<Vector<String>> productData  = new Vector<Vector<String>>();
        List<Map<String,String>> listData = new ArrayList<Map<String, String>>();
        try {
            productData = ExcelPoiTools.readFile(fileItem);
        } catch (IOException e1) {
               throw new IOException("Excle文件读取异常");
        }
        // 获取抬头，检验是否包含非空字段列
        Vector<String> title = (Vector<String>) productData.get(1);
        Map<String, Integer> colNum = getColNum(title);
        /* 数据包装导入 */
        for (int i = 2; i < productData.size(); i++) {

            Vector<String> data = (Vector<String>) productData.get(i);
            // 根据列的数目，补足一行数据的个数，如列有10个，一行数据仅有9个，则补足1个空白字段；
            int last = colNum.size() - data.size();
            while (last > 0) {
                data.add("");
                last--;
            }
            // 获得各字段对应的字符串 ，校验字符串是否符合标准，
            // 不符合标准的放到failData集合中，不影响后续记录的导入。
            Map<String, String> value = new HashMap<String, String>();


                value = getValueAndCheck(data, colNum);
            listData.add(value);


        }
        if(listData.size()==productData.size()-2){
            for (int i = 0;i<listData.size();i++){
                Map<String, String> value = listData.get(i);
                saveDate(value.get("cicNo"),value.get("elcregisteNo"));
            }
           return productData;
        }else{
            throw new IOException("Excle文件数据错误");
        }

    }

    public void saveDate(String cicNo,String elcregisteNo){
        String sql = "update scms_schoolroll t set t.elcregisteno = '"+elcregisteNo+"' " +
                " where t.studentid = (select t.studentid from scms_schoolroll t, scms_student s" +
                "  where t.studentid = s.id  and s.cscid = '"+cicNo+"')";
       super.updateBySql(sql);
    }
    /**
     * 获取一行数据的校验结果
     *
     * @author:lizhisheng
     *
     *
     * @param data
     * @param colNum
     * @return
     *
     *             Map<String,String>
     */
    private Map<String, String> getValueAndCheck(Vector<String> data,
                                                 Map<String, Integer> colNum) throws IOException,
            UnsupportedEncodingException {
        Map<String, String> value = new HashMap<String, String>();

        String cicNo = (String) data.get(colNum.get("cicNo")).trim();
        String elcregisteNo = (String) data.get(colNum.get("elcregisteNo")).trim();
        if ("".equals(cicNo)) {
            throw new IOException("CSC登记号不能为空");
        }
        if ("".equals(elcregisteNo)) {
            throw new IOException("学籍电子注册号不能为空");
        }



        // 校验通过的记录返回，否则抛出异常，存入failData中
        value.put("cicNo", cicNo);
        value.put("elcregisteNo", elcregisteNo);

        return value;
    }
    private Map<String, Integer> getColNum(Vector<String> title) throws IOException {
        Map<String, Integer> colNum = new HashMap<String, Integer>();
        for (int i = 0; i < title.size(); i++) {
            String name = (String) title.get(i);

            if (name.matches(".*CSC登记号.*")) {
                colNum.put("cicNo", i);
            }
            if (name.matches(".*学籍电子注册号.*")) {
                colNum.put("elcregisteNo", i);
            }

        }
        if (CollectionUtils.isEmpty(colNum) || colNum.size() < 2) {

                throw new IOException("导入的数据文件格式不正确！");

        }
        if (colNum.get("cicNo") == null) {
            throw new IOException("导入数据必须包含cic登记号！");
        }
        if (colNum.get("elcregisteNo") == null) {
            throw new IOException("导入数据必须包含学籍电子注册号！");
        }

        return colNum;
    }

}
