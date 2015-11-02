package gov.gwssi.csc.scms.dao.ticket;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.student.StudentService;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.fileupload.FileItem;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lzs on 2015/6/1.
 * ticket DAO封装类
 */
@Service("ticketDAO")
public class TicketDAO extends BaseDAO {
    @Autowired
    private StudentService studentService;
    public List getStudentList(User user) {
        List studentList = null;

        String userType = user.getUserType();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT SCMS_STUDENT.ID,CSCID,PASSPORTNAME,GENDER,BIRTHDAY,COUNTRY,CERTIFICATENO,TRAVELTYPE FROM SCMS_STUDENT,SCMS_SCHOOLROLL schoolRoll,SCMS_BASIC_INFO ");
        stringBuilder.append(" where SCMS_STUDENT.id = SCMS_BASIC_INFO.STUDENTID and SCMS_STUDENT.id = schoolRoll.STUDENTID");
        if ("2".equals(userType)) {
            stringBuilder.append(" and schoolRoll.currentUniversity = '").append(user.getNode().getNodeId()).append("' ");
            stringBuilder.append(StudentFilter.LEAVEDATA_STUDENT_CONDITION);//  离华时间
            stringBuilder.append(StudentFilter.INTERNAL_STUDENT_CONDITION);// 在校生条件

        }
        stringBuilder.append(" and SCMS_STUDENT.id not in (select distinct STUDENTID from SCMS_AIRTICKET)");
        studentList = super.queryListBySql(stringBuilder.toString());
        return studentList;
    }
    public void doSt(String name, List list){
       // super.doStatement(name,list);
       String str =  super.doStatementForRtn(name,list);
//System.out.println("str  ="+str);
    }
    private String decodeNull(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString().trim();
    }
    public List<String> check(FileItem fileItem ,int year) throws IOException {
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
        if(maxRows<=2||maxColumns>17){
            stringList.add("校验结果：");
            stringList.add("导入的数据文件标题不正确或者列数大于17列！");
            return stringList;
        }
        String cscNo = "";
        String ticketLine = "";
        String airNo = "";
        String price = "";
        String time = "";
        String id = "";
        BigDecimal priceSave ;
        String remark = "";//备注
        String checkcscNo = "CSC登记号有空行：";
        String cscNoIsNull = "CSC登记号不存在：";
        String ticketIdIsError = "机票编号不存在：";
        String ticketIsError = "导入的订票信息存在错误行：";
        String remarkIsToLong = "导入的订票信息备注太长对应的行：";

//String linTime = "";
        //Map<String, String> check = new HashMap<String, String>();
        for (int m = 2; m < sheet.getRows(); m++) {
            id = String.valueOf(decodeNull(sheet.getCell(0, m).getContents()));
            cscNo = String.valueOf(decodeNull(sheet.getCell(1, m).getContents()));
            ticketLine = String.valueOf(decodeNull(sheet.getCell(13, m).getContents()));
            airNo= String.valueOf(decodeNull(sheet.getCell(15, m).getContents()));
            remark= String.valueOf(decodeNull(sheet.getCell(16, m).getContents()));
            price= String.valueOf(decodeNull(sheet.getCell(14, m).getContents()));
            time= String.valueOf(decodeNull(sheet.getCell(12, m).getContents()));
            if("".equals(time)||"".equals(ticketLine)||"".equals(airNo)||"".equals(price)){
                ticketIsError = ticketIsError+"第"+(m+1)+"行,";
                continue;
            }
            if(!"".equals(time)){
                if(sheet.getCell(12, m).getType() == CellType.DATE){
                    DateCell dc = (DateCell)sheet.getCell(12, m);
                    Date date = dc.getDate();
                    SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
                    time = ds.format(date);
                }
            }

            if(!"".equals(price)){
                priceSave = new BigDecimal(price);
            }
//            if(!"".equals(airNo)){
//
//            }
//            if(!"".equals(ticketNo)){
//
//            }
            Student student = studentService.getStudentByCscId(cscNo);
            int i =0;
            if (student != null) {
                String studentId = select(id);
                if(studentId.equals(student.getId())){
                    i =1;
                }
            }
            if(!"".equals(remark)){
                if(remark.length()>300){
                    remarkIsToLong = remarkIsToLong +"第"+(m+1)+"行,";
                }
            }
            if ("".equals(cscNo)) {
                // stringList.add("第"+(i+1)+"行CSC登记号不能为空,");
                checkcscNo = checkcscNo+"第"+(m+1)+"行,";
            }else{
                if(student == null){
                    cscNoIsNull = cscNoIsNull +"第"+(m+1)+"行,";
                }
                if(i==0){
                    // stringList.add("第"+(i+1)+"行CSC登记号不存在,");
                    ticketIdIsError = ticketIdIsError +"第"+(m+1)+"行,";
                }
            }



        }
        if(!"CSC登记号有空行：".equals(checkcscNo)){
            stringList.add(checkcscNo);
        }
        if(!"CSC登记号不存在：".equals(cscNoIsNull)){
            stringList.add(cscNoIsNull);
        }
        if(!"机票编号不存在：".equals(ticketIdIsError)){
            stringList.add(ticketIdIsError);
        }
//        String ticketIsError = "导入的订票信息存在错误行：";
        if(!"导入的订票信息存在错误行：".equals(ticketIsError)){
            stringList.add(ticketIsError);
        }
        if(!"导入的订票信息备注太长对应的行：".equals(ticketIsError)){
            stringList.add(remarkIsToLong);
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
            stringList.add("以上是全部错误");
            System.out.println(stringList);
            return stringList;
        }

    }
    public List<String> doImport(FileItem fileItem,int year) throws IOException {
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
        String id = "";
        String ticketLine = "";
        String airNo = "";
        String price = "";
        String time = "";
        String remark = "";//备注
        BigDecimal priceSave = new BigDecimal(0) ;
        //Map<String, String> check = new HashMap<String, String>();
        for (int m = 2; m < sheet.getRows(); m++) {

            id = String.valueOf(decodeNull(sheet.getCell(0, m).getContents()));
            cscNo = String.valueOf(decodeNull(sheet.getCell(1, m).getContents()));
            ticketLine = String.valueOf(decodeNull(sheet.getCell(13, m).getContents()));
            airNo= String.valueOf(decodeNull(sheet.getCell(15, m).getContents()));
            price= String.valueOf(decodeNull(sheet.getCell(14, m).getContents()));
            time= String.valueOf(decodeNull(sheet.getCell(12, m).getContents()));
            remark= String.valueOf(decodeNull(sheet.getCell(16, m).getContents()));
            if(!"".equals(time)){
                if(sheet.getCell(12, m).getType() == CellType.DATE){
                    DateCell dc = (DateCell)sheet.getCell(12, m);
                    Date date = dc.getDate();
                    SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
                    time = ds.format(date);
                }
            }
            if(!"".equals(price)){
                priceSave = new BigDecimal(price);
            }
            saveDate(id,ticketLine,airNo,priceSave,time,remark);

            stringList.add(cscNo);

        }

        return stringList;

    }
    @Transactional
    public void saveDate(String id,String ticketLine,String airNo,BigDecimal priceSave,String time,String remark){
        String sql = "update SCMS_AIRTICKET t " +
                " set t.TICKETNO = '"+airNo+"',t.AIRLINE = '"+ticketLine+"',t.state = 'AT0003', t.remark = '"+remark+"'" +
                " t.PRICE = '"+priceSave+"',t.FLIGHTDATE =to_date('"+time+"','yyyy-MM-dd') where t.id='"+id+"'";

        super.updateBySql(sql);
    }
    public String select(String id){
        String sql = "select t.STUDENTID from SCMS_AIRTICKET t where t.id = '"+id+"'";
String studentId = "";
       List list = super.queryListBySql(sql);
        if(list.size()>0){
            studentId = ((Map)list.get(0)).get("STUDENTID").toString();
        }
        return studentId;
    }
}
