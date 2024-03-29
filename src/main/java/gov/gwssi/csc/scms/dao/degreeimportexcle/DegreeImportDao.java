package gov.gwssi.csc.scms.dao.degreeimportexcle;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.query.ImportResult;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.utils.ExcelPoiTools;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
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
 * Created by LiZhiSheng on 2015/8/16.
 */
@Service("degreeImportDao")
public class DegreeImportDao extends BaseDAO {
    @Autowired
    private StudentService studentService;
    private static DegreeImportDao manager;

    public static DegreeImportDao getInstance(){
        if(manager == null){
            manager = new DegreeImportDao();
        }
        return manager;
    }
    //add by lzs 20150825

    private String decodeNull(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString().trim();
    }
    public List<String> check(FileItem fileItem) throws IOException {
        List<String> stringList = new ArrayList<String>();
        InputStream inp = fileItem.getInputStream();
        if (!inp.markSupported()) {
            inp = new PushbackInputStream(inp, 8);
        }
        if (!POIFSFileSystem.hasPOIFSHeader(inp)) {
            stringList.add("校验结果：");
            stringList.add("请检验Excel版本，需为excle 97-2003 工作簿（*.xls）！");
            return stringList;
        }
        //Workbook wb =  new HSSFWorkbook(inp);
        Workbook wb = null;
        try {
            wb=  Workbook.getWorkbook(inp);
        } catch (BiffException e) {
            e.printStackTrace();
        }
        // 获得该工作区的第一个sheet

        Sheet sheet = wb.getSheet(0);
        int maxRows = sheet.getRows();
        int maxColumns = sheet.getColumns();
        if(maxRows<=2||maxColumns>3){
            stringList.add("校验结果：");
            stringList.add("导入的数据文件标题不正确或者列数大于3列！");
            return stringList;
        }
        String cscNo = "";
        String elcregisteNo = "";
        String checkcscNo = "CSC登记号有空行：";
        String cscNoIsNull = "CSC登记号不存在：";

        //Map<String, String> check = new HashMap<String, String>();
        for (int m = 2; m < sheet.getRows(); m++) {

            cscNo = String.valueOf(decodeNull(sheet.getCell(0, m).getContents()));
           // elcregisteNo = String.valueOf(decodeNull(sheet.getCell(1, m).getContents()));
            Student student = studentService.getStudentByCscId(cscNo);
            if ("".equals(cscNo)) {
                // stringList.add("第"+(i+1)+"行CSC登记号不能为空,");
                checkcscNo = checkcscNo+"第"+(m+1)+"行,";
            }else{
                if(student==null){
                    // stringList.add("第"+(i+1)+"行CSC登记号不存在,");
                    cscNoIsNull = cscNoIsNull +"第"+(m+1)+"行,";
                }
            }




        }
        if(!"CSC登记号有空行：".equals(checkcscNo)){
            stringList.add(checkcscNo);
        }
        if(!"CSC登记号不存在：".equals(cscNoIsNull)){
            stringList.add(cscNoIsNull);
        }

        if(stringList.size()==0){
            stringList.clear();
            stringList.add("成功导入");
            System.out.println(stringList);
            return stringList;
        }else if(stringList.size()>15){
            stringList.clear();
            stringList.add("校验结果：");
            stringList.add("错误信息太多");
            stringList.add("请更正后重新导入！");
            System.out.println(stringList);
            return stringList;
        }else{
            stringList.add("<br/>以上是全部错误");
            System.out.println(stringList);
            return stringList;
        }

    }
    public List<String> doImport(FileItem fileItem) throws IOException {
        List<String> stringList = new ArrayList<String>();
        InputStream inp = fileItem.getInputStream();

        //Workbook wb =  new HSSFWorkbook(inp);
        Workbook wb = null;
        try {
            wb=  Workbook.getWorkbook(inp);
        } catch (BiffException e) {
            e.printStackTrace();
        }
        // 获得该工作区的第一个sheet

        Sheet sheet = wb.getSheet(0);


        String cscNo = "";
        String elcregisteNo = "";
        String degreeNo = "";
        //Map<String, String> check = new HashMap<String, String>();
        for (int m = 2; m < sheet.getRows(); m++) {

            cscNo = String.valueOf(decodeNull(sheet.getCell(0, m).getContents()));
            elcregisteNo = String.valueOf(decodeNull(sheet.getCell(1, m).getContents()));
            degreeNo = String.valueOf(decodeNull(sheet.getCell(2, m).getContents()));
            saveDate(cscNo,elcregisteNo,degreeNo);
            stringList.add(cscNo);

        }

        return stringList;

    }
    //end by lzs 20150825
    public List getList(String pro,String univ) {
        List<Map> getList = null;
        List<ImportResult> newStudenTimeSettList = new ArrayList<ImportResult>();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT t.FILENAME,t.CNT,t.STATE,t.CREATEBY,t.CREATED,t.error from SCMS_IMPORT_LOG t where 1 = 1 and t.class = '2' ");
        if(!"".equals(pro)){
            stringBuilder.append(" and t.CREATED >= to_date('"+pro+"','yyyy-MM-dd')");
        }
        if(!"".equals(univ)){
            stringBuilder.append("  and t.CREATED <= to_date('"+univ+"','yyyy-MM-dd')");
        }
        getList = super.queryListBySql(stringBuilder.toString());
        if (getList != null && getList.size() > 0) {
            for (Map map : getList) {
                String sumNo = String.valueOf(map.get("CNT"));
                String fileName = (String) map.get("FILENAME");
                Date optTime = (Date) map.get("CREATED");
                String importState = (String) map.get("STATE");
                String opter = (String) map.get("CREATEBY");
                String errorMsg = (String) map.get("ERROR");
                ImportResult newStuTimeSetResult = new ImportResult();

                boolean sumNoIsEmpty = sumNo == null || sumNo.isEmpty() ;
                boolean fileNameIsEmpty = fileName == null || fileName.isEmpty();
                boolean optTimeIsEmpty = optTime == null || optTime.toString().isEmpty();
                boolean importStateIsEmpty = importState == null || importState.isEmpty();
                boolean opterIsEmpty = opter == null || opter.isEmpty();
                boolean errorMsgIsEmpty = errorMsg == null || errorMsg.isEmpty();
                sumNo = sumNoIsEmpty ? "O" : sumNo;
                fileName = fileNameIsEmpty ? "" : fileName;
                optTime = optTimeIsEmpty ? null : optTime;
                importState = importStateIsEmpty ? "" : importState;
                opter = opterIsEmpty ? "" : opter;
                errorMsg = errorMsgIsEmpty?"":errorMsg;


                newStuTimeSetResult.setFileName(fileName);
                newStuTimeSetResult.setImportState(importState);
                newStuTimeSetResult.setOpter(opter);
                newStuTimeSetResult.setOptTime(optTime);
                newStuTimeSetResult.setSumNo(sumNo);
                newStuTimeSetResult.setErrorMsg(errorMsg);


                newStudenTimeSettList.add(newStuTimeSetResult);

            }
        }
        // System.out.println("11111");
        return newStudenTimeSettList;
    }
    public List<String> checkOld(FileItem fileItem) throws IOException{
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
        //Map<String, Integer> colNum = getColNum(title);
        Map<String, String> checkResult = new HashMap<String, String>();
        stringList = getColNum(title,stringList);
        if(stringList.size()>=2){
            return stringList;
        }
        String checkcscNo = "CSC登记号有空行：";
        String cscNoIsNull = "CSC登记号不存在：";
        //String checkElcregisteNo = "学籍电子注册号有空行：";
        /* 数据包装导入 */
        for (int i = 2; i < productData.size(); i++) {

            Vector<String> data = (Vector<String>) productData.get(i);
            // 根据列的数目，补足一行数据的个数，如列有10个，一行数据仅有9个，则补足1个空白字段；
//            int last = colNum.size() - data.size();
//            while (last > 0) {
//                data.add("");
//                last--;
//            }
            // 获得各字段对应的字符串 ，校验字符串是否符合标准，
            // 不符合标准的放到failData集合中，不影响后续记录的导入。
            Map<String, String> value = new HashMap<String, String>();
          //  value = getValueAndCheck(data, colNum);

            checkResult = getValueAndCheck(data, null,stringList,i);
            //listData.add(value);


        }
//        if(listData.size()==productData.size()-2){
//            for (int i = 0;i<listData.size();i++){
//                Map<String, String> value = listData.get(i);
//                saveDate(value.get("cicNo"),value.get("elcregisteNo"));
//            }
//
//        }
        checkcscNo = checkcscNo+checkResult.get("1");
        cscNoIsNull = cscNoIsNull+checkResult.get("2");
        if(!"CSC登记号有空行：".equals(checkcscNo)){
            stringList.add(checkcscNo);
        }
        if(!"CSC登记号不存在：".equals(cscNoIsNull)){
            stringList.add(cscNoIsNull);
        }
        if(stringList.size()==0){

            stringList.add("成功导入");
            System.out.println(stringList);
            return stringList;
        }else  if(stringList.size()>15){
            stringList.clear();
            stringList.add("校验结果：");
            stringList.add("错误信息太多");
            stringList.add("请更正后重新导入！");
            System.out.println(stringList);
            return stringList;
        }else{
            stringList.add("<br/>以上是全部错误");
            System.out.println(stringList);
            return stringList;
        }



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
            saveDate(value.get("cicNo"),value.get("elcregisteNo"),value.get("degreeNo"));
        }
        return productData;
    }else{
        return new Vector<Vector<String>>();
        // throw new IOException("Excle文件数据错误");
    }

}
    @Transactional
    public void saveDate(String cscNo,String elcregisteNo,String degreeNo){
        String sql = "update scms_schoolroll t set t.DEGREENO = '"+degreeNo+"' ,t.ACADEMICCERTIFICATENO = '"+elcregisteNo+"'" +
                " where t.studentid = (select t.studentid from scms_schoolroll t, scms_student s" +
                "  where t.studentid = s.id  and s.cscid = '"+cscNo+"')";

       super.updateBySql(sql);
    }
    @Transactional
    public String saveInitFile(String fileName,String userName){
        String id = super.getIdBySequence("SEQ_IMPORT_LOG");
        String sql = "insert into SCMS_IMPORT_LOG t values('"+id+"','"+fileName+"',0,'2','2','"+userName+"',sysdate,'')";
        int m = super.updateBySql(sql);
        if(m==1){
            return id;
        }else{
            return "";
        }
    }
    @Transactional
    public void updateFile(String id,int sum,String state,List<String> stringList){
        String errorMsg = "";
        if(stringList.size()>0&&!"成功导入".equals(stringList.get(0))){
for(int m = 0;m<stringList.size();m++){
    errorMsg = errorMsg+stringList.get(m);
}
        }
        //String id = super.getIdBySequence("SEQ_IMPORT_LOG");
        String sql = "update SCMS_IMPORT_LOG t set t.cnt = "+sum+",t.state = '"+state+"',t.error = '"+errorMsg+"' where t.id = '"+id+"'";
        int m = super.updateBySql(sql);
        System.out.println(m);
        System.out.println();
    }
    @Transactional
    public void saveOpt(String fileName,int sum,String userName){
        String id = super.getIdBySequence("SEQ_IMPORT_LOG");
        String sql = "insert into SCMS_IMPORT_LOG t values('"+id+"','"+fileName+"',"+sum+",'2','1','"+userName+"',sysdate)";
        int m = super.updateBySql(sql);
        System.out.println(m);
        System.out.println();
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
        String degreeNo = "";
        String elcregisteNo = "";
        if(data.size()>=2){
            degreeNo = (String) data.get(1).trim();
        }
        if(data.size()>=3){
            elcregisteNo = (String) data.get(2).trim();
        }
        //String elcregisteNo = (String) data.get(colNum.get("elcregisteNo")).trim();
//        if ("".equals(cicNo)) {
//            throw new IOException("CSC登记号不能为空");
//        }
//        if ("".equals(elcregisteNo)) {
//            throw new IOException("学籍电子注册号不能为空");
//        }



        // 校验通过的记录返回，否则抛出异常，存入failData中
        value.put("cicNo", cicNo);
        value.put("elcregisteNo", elcregisteNo);
        value.put("degreeNo", degreeNo);
        return value;
    }
    private Map<String,String>  getValueAndCheck(Vector<String> data,
                                                 Map<String, Integer> colNum,List<String> list,int i) throws IOException,
            UnsupportedEncodingException {
        Map<String, String> value = new HashMap<String, String>();
        Map<String, String> check = new HashMap<String, String>();
        List<String> stringList = list;
        String cicNo = (String) data.get(0).trim();
        String degreeNo = "";
        String elcregisteNo = "";
        if(data.size()>=2){
            degreeNo = (String) data.get(1).trim();
        }
        if(data.size()>=3){
           elcregisteNo = (String) data.get(2).trim();
        }

        String checkcscNo = "";
        String cscNoIsNull = "";
        Student student = studentService.getStudentByCscId(cicNo);
        if ("".equals(cicNo)) {
            checkcscNo = checkcscNo+"第"+(i+1)+"行,";
        }else{
        if(student==null){
            cscNoIsNull = cscNoIsNull +"第"+(i+1)+"行,";
        }
        }
//        if ("".equals(elcregisteNo)) {
//            stringList.add("第"+(i+1)+"行学籍电子注册号不能为空,");
//        }



        // 校验通过的记录返回，否则抛出异常，存入failData中
        check.put("1",checkcscNo);
        check.put("2",cscNoIsNull);
        value.put("cicNo", cicNo);
        value.put("elcregisteNo", elcregisteNo);
        value.put("degreeNo", degreeNo);
        return check;
    }
    private List<String> getColNum(Vector<String> title,List<String> list) throws IOException {
        List<String> stringList = list;
        Map<String, Integer> colNum = new HashMap<String, Integer>();
        for (int i = 0; i < title.size(); i++) {
            String name = (String) title.get(i);

            if (name.matches(".*CSC登记号.*")) {
                colNum.put("cicNo", i);
            }
            if (name.matches(".*学历证书编号.*")) {
                colNum.put("elcregisteNo", i);
            }
            if (name.matches(".*学位证书编号.*")) {
                colNum.put("degreeNo", i);
            }

        }
        if (CollectionUtils.isEmpty(colNum) || colNum.size() < 3) {
            stringList.add("校验结果：");
            stringList.add("导入的数据文件表标题不正确！");

        }
        if (colNum.get("cicNo") == null) {
            stringList.add("校验结果：");
            stringList.add("导入数据必须包含csc登记号！");
        }
//        if (colNum.get("elcregisteNo") == null) {
//            stringList.add("导入数据必须包含学籍电子注册号！");
//        }

        return stringList;
    }
    private Map<String, Integer> getColNum(Vector<String> title) throws IOException {

        Map<String, Integer> colNum = new HashMap<String, Integer>();
        for (int i = 0; i < title.size(); i++) {
            String name = (String) title.get(i);

            if (name.matches(".*CSC登记号.*")) {
                colNum.put("cicNo", i);
            }
            if (name.matches(".*学历证书编号.*")) {
                colNum.put("elcregisteNo", i);
            }
            if (name.matches(".*学位证书编号.*")) {
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
