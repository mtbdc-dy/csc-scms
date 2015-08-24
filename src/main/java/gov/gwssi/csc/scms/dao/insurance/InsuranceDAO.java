package gov.gwssi.csc.scms.dao.insurance;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.utils.ExcelPoiTools;
import org.apache.commons.fileupload.FileItem;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by gc on 2015/7/17.
 * insurance DAO封装类
 */
@Service("insuranceDAO")
public class InsuranceDAO extends BaseDAO {
    @Autowired
    private StudentService studentService;
    public void doSt(String name, List list){
        super.doStatementForRtn(name,list);

    }
    public int getStuInsurance(String studentid,int year) {
        String sql = "select t.id from SCMS_INSURANCE t where t.studentid = '"+studentid+"' and t.year = "+year;
        List list = super.queryListBySql(sql);
        if(list.size()>0){
            return 1;
        }
        return 0;
    }
    public List<String> check(FileItem fileItem,int year) throws IOException{
        List<String> stringList = new ArrayList<String>();
        //stringList.add("错误信息");

        Vector<Vector<String>> productData  = new Vector<Vector<String>>();
        List<Map<String,String>> listData = new ArrayList<Map<String, String>>();
        InputStream inp = fileItem.getInputStream();
        if (!inp.markSupported()) {
            inp = new PushbackInputStream(inp, 8);
        }
        if (!POIFSFileSystem.hasPOIFSHeader(inp)) {
            stringList.add("校验结果：");
            stringList.add("请检验Excel版本，需为excle 97-2003 工作簿（*.xls）！");
            return stringList;
        }
        try {
            productData = ExcelPoiTools.readFile(fileItem);
        } catch (IOException e1) {
            stringList.add("校验结果：");
            stringList.add("Excle文件读取异常");
            return stringList;
            // throw new IOException("Excle文件读取异常");


        }


        // 获取抬头，检验是否包含非空字段列
        Vector<String> title = (Vector<String>) productData.get(1);
        Map<String, Integer> colNum = getColNum(title);
        stringList = getColNum(title,stringList);
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
            //  value = getValueAndCheck(data, colNum);

            stringList = getValueAndCheck(data, colNum,stringList,i,year);
            //listData.add(value);


        }
//        if(listData.size()==productData.size()-2){
//            for (int i = 0;i<listData.size();i++){
//                Map<String, String> value = listData.get(i);
//                saveDate(value.get("cicNo"),value.get("elcregisteNo"));
//            }
//
//        }

        if(stringList.size()==0){

            stringList.add("成功导入");
            System.out.println(stringList);
            return stringList;
        }else if(stringList.size()>30){
            List<String> stringList1 = new ArrayList<String>();
            stringList.add("错误信息太多请更正后重新导入！");
            System.out.println(stringList1);
            return stringList1;
        }else{
            stringList.add("以上是全部错误");
            System.out.println(stringList);
            return stringList;
        }



    }
    @Transactional
    public Vector<Vector<String>> doExcelImport(FileItem fileItem,int year) throws IOException{
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
                saveDate(value.get("cicNo"),value.get("elcregisteNo"),year);
            }
            return productData;
        }else{
            return new Vector<Vector<String>>();
            // throw new IOException("Excle文件数据错误");
        }

    }
    @Transactional
    public void saveDate(String cicNo,String elcregisteNo,int year){
        String sql = "update SCMS_INSURANCE t set t.INSURNO = '"+elcregisteNo+"' " +
                " where t.YEAR = "+year+" and t.studentid = (select s.id from scms_student s" +
                "  where s.cscid = '"+cicNo+"')";

        super.updateBySql(sql);
    }
    private Map<String, String> getValueAndCheck(Vector<String> data,
                                                 Map<String, Integer> colNum) throws IOException,
            UnsupportedEncodingException {
        Map<String, String> value = new HashMap<String, String>();

        String cicNo = (String) data.get(colNum.get("cicNo")).trim();
        String elcregisteNo = (String) data.get(colNum.get("elcregisteNo")).trim();
//        if ("".equals(cicNo)) {
//            throw new IOException("CSC登记号不能为空");
//        }
//        if ("".equals(elcregisteNo)) {
//            throw new IOException("学籍电子注册号不能为空");
//        }



        // 校验通过的记录返回，否则抛出异常，存入failData中
        value.put("cicNo", cicNo);
        value.put("elcregisteNo", elcregisteNo);

        return value;
    }
    private List<String> getValueAndCheck(Vector<String> data,
                                          Map<String, Integer> colNum,List<String> list,int i,int year) throws IOException,
            UnsupportedEncodingException {
        Map<String, String> value = new HashMap<String, String>();
        List<String> stringList = list;
        String cicNo = (String) data.get(0).trim();
        String elcregisteNo = (String) data.get(1).trim();
        Student student = studentService.getStudentByCscId(cicNo);
        int m =0;
        if (student != null) {
            m = getStuInsurance(student.getId(), year);
        }
        if ("".equals(cicNo)) {
            stringList.add("第"+(i+1)+"行CSC登记号不能为空,");
        }else{
            if(m == 0){
                stringList.add("第"+(i+1)+"行CSC登记号不存在,");
            }
        }
        if ("".equals(elcregisteNo)) {
            stringList.add("第"+(i+1)+"行保单号不能为空,");
        }



        // 校验通过的记录返回，否则抛出异常，存入failData中
        value.put("cicNo", cicNo);
        value.put("elcregisteNo", elcregisteNo);

        return stringList;
    }
    private List<String> getColNum(Vector<String> title,List<String> list) throws IOException {
        List<String> stringList = list;
        Map<String, Integer> colNum = new HashMap<String, Integer>();
        for (int i = 0; i < title.size(); i++) {
            String name = (String) title.get(i);

            if (name.matches(".*CSC登记号.*")) {
                colNum.put("cicNo", i);
            }
            if (name.matches(".*保单号.*")) {
                colNum.put("elcregisteNo", i);
            }

        }
        if (CollectionUtils.isEmpty(colNum) || colNum.size() < 2) {

            stringList.add("导入的数据文件表标题不正确！");

        }
        if (colNum.get("cicNo") == null) {
            stringList.add("导入数据必须包含cic登记号！");
        }
        if (colNum.get("elcregisteNo") == null) {
            stringList.add("导入数据必须包含保单号！");
        }

        return stringList;
    }
    private Map<String, Integer> getColNum(Vector<String> title) throws IOException {

        Map<String, Integer> colNum = new HashMap<String, Integer>();
        for (int i = 0; i < title.size(); i++) {
            String name = (String) title.get(i);

            if (name.matches(".*CSC登记号.*")) {
                colNum.put("cicNo", i);
            }
            if (name.matches(".*保单号.*")) {
                colNum.put("elcregisteNo", i);
            }

        }
//        if (CollectionUtils.isEmpty(colNum) || colNum.size() < 2) {
//
//                throw new IOException("导入的数据文件格式不正确！");
//
//        }
//        if (colNum.get("cicNo") == null) {
//            throw new IOException("导入数据必须包含cic登记号！");
//        }
//        if (colNum.get("elcregisteNo") == null) {
//            throw new IOException("导入数据必须包含学籍电子注册号！");
//        }

        return colNum;
    }
}
