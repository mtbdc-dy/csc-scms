package gov.gwssi.csc.scms.dao.importExcle;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.service.student.StudentService;
import org.apache.commons.fileupload.FileItem;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LiZhiSheng on 2015/8/24.
 */
@Service("importStuRegDAO")
public class ImportStuRegDAO extends BaseDAO{
    @Autowired
    private StudentService studentService;
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
        if(maxRows<=2||maxColumns>2){
            stringList.add("校验结果：");
            stringList.add("导入的数据文件标题不正确或者列数大于2列！");
            return stringList;
        }
        String cscNo = "";
        String elcregisteNo = "";
        String checkcscNo = "CSC登记号有空行：";
        String cscNoIsNull = "CSC登记号不存在：";
        String checkElcregisteNo = "学籍电子注册号有空行：";
        //Map<String, String> check = new HashMap<String, String>();
        for (int m = 2; m < sheet.getRows(); m++) {

                cscNo = String.valueOf(decodeNull(sheet.getCell(0, m).getContents()));
                elcregisteNo = String.valueOf(decodeNull(sheet.getCell(1, m).getContents()));
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
                if ("".equals(elcregisteNo)) {
                    //stringList.add("第"+(i+1)+"行学籍电子注册号不能为空,");
                    checkElcregisteNo = checkElcregisteNo+"第"+(m+1)+"行,";
                }



        }
        if(!"CSC登记号有空行：".equals(checkcscNo)){
            stringList.add(checkcscNo);
        }
        if(!"CSC登记号不存在：".equals(cscNoIsNull)){
            stringList.add(cscNoIsNull);
        }
        if(!"学籍电子注册号有空行：".equals(checkElcregisteNo)){
            stringList.add(checkElcregisteNo);
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

        //Map<String, String> check = new HashMap<String, String>();
        for (int m = 2; m < sheet.getRows(); m++) {

            cscNo = String.valueOf(decodeNull(sheet.getCell(0, m).getContents()));
            elcregisteNo = String.valueOf(decodeNull(sheet.getCell(1, m).getContents()));
            saveDate(cscNo,elcregisteNo);
            stringList.add(cscNo);

        }

        return stringList;

    }
    @Transactional
    public void saveDate(String cicNo,String elcregisteNo){
        String sql = "update scms_schoolroll t set t.elcregisteno = '"+elcregisteNo+"' " +
                " where t.studentid = (select t.studentid from scms_schoolroll t, scms_student s" +
                "  where t.studentid = s.id  and s.cscid = '"+cicNo+"')";

        super.updateBySql(sql);
    }
    @Transactional
    public String saveInitFile(String fileName,String userName){
        String id = super.getIdBySequence("SEQ_IMPORT_LOG");
        String sql = "insert into SCMS_IMPORT_LOG t values('"+id+"','"+fileName+"',0,'2','1','"+userName+"',sysdate,'')";
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
}
