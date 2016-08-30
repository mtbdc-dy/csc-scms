package gov.gwssi.csc.scms.dao.insurance;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.domain.user.User;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gc on 2015/7/17.
 * insurance DAO封装类
 */
@Service("insuranceDAO")
public class InsuranceDAO extends BaseDAO {
    @Autowired
    private StudentService studentService;

    public void doSt(String name, List list) {
        super.doStatementForRtn(name, list);

    }

    public int getStuInsurance(String studentid, int year) {
        String sql = "select t.id from SCMS_INSURANCE t where t.studentid = '" + studentid + "' and t.year = " + year;
        List list = super.queryListBySql(sql);
        if (list.size() > 0) {
            return 1;
        }
        return 0;
    }

    private String decodeNull(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString().trim();
    }

    public List<String> check(FileItem fileItem, int year) throws IOException {
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
            wb = Workbook.getWorkbook(inp);
        } catch (BiffException e) {
            e.printStackTrace();
        }
        // 获得该工作区的第一个sheet

        Sheet sheet = wb.getSheet(0);
        int maxRows = sheet.getRows();
        int maxColumns = sheet.getColumns();
        if (maxRows <= 2 || maxColumns > 10) {
            stringList.add("校验结果：");
            stringList.add("导入的数据文件标题不正确或者列数大于10列！");
            return stringList;
        }
        String cscNo = "";
        String insuranceNo = "";
        String checkcscNo = "CSC登记号有空行：";
        String cscNoIsNull = "CSC登记号不存在：";
        String checkInsuranceNo = "保单号有空行：";
        //Map<String, String> check = new HashMap<String, String>();
        for (int m = 2; m < sheet.getRows(); m++) {

            cscNo = String.valueOf(decodeNull(sheet.getCell(0, m).getContents()));
            insuranceNo = String.valueOf(decodeNull(sheet.getCell(9, m).getContents()));
            Student student = studentService.getStudentByCscId(cscNo);
            int i = 0;
            if (student != null) {
                i = getStuInsurance(student.getId(), year);
            }
            if ("".equals(cscNo)) {
                // stringList.add("第"+(i+1)+"行CSC登记号不能为空,");
                checkcscNo = checkcscNo + "第" + (m + 1) + "行,";
            } else {
                if (i == 0) {
                    // stringList.add("第"+(i+1)+"行CSC登记号不存在,");
                    cscNoIsNull = cscNoIsNull + "第" + (m + 1) + "行,";
                }
            }
            if ("".equals(insuranceNo)) {
                checkInsuranceNo = checkInsuranceNo + "第" + (m + 1) + "行,";

            }


        }
        if (!"CSC登记号有空行：".equals(checkcscNo)) {
            stringList.add(checkcscNo);
        }
        if (!"CSC登记号不存在：".equals(cscNoIsNull)) {
            stringList.add(cscNoIsNull);
        }
        if (!"保单号有空行：".equals(checkInsuranceNo)) {
            stringList.add(checkInsuranceNo);
        }
        if (stringList.size() == 0) {
            stringList.clear();
            stringList.add("成功导入");
            System.out.println(stringList);
            return stringList;
        } else if (stringList.size() > 15) {
            stringList.clear();
            stringList.add("校验结果：");
            stringList.add("错误信息太多");
            stringList.add("请更正后重新导入！");
            System.out.println(stringList);
            return stringList;
        } else {
            stringList.add("以上是全部错误");
            System.out.println(stringList);
            return stringList;
        }

    }

    public List<String> doImport(FileItem fileItem, int year) throws IOException {
        List<String> stringList = new ArrayList<String>();
        InputStream inp = fileItem.getInputStream();

        //Workbook wb =  new HSSFWorkbook(inp);
        Workbook wb = null;
        try {
            wb = Workbook.getWorkbook(inp);
        } catch (BiffException e) {
            e.printStackTrace();
        }
        // 获得该工作区的第一个sheet

        Sheet sheet = wb.getSheet(0);


        String cscNo = "";
        String elcregisteNo = "";
        String insuranceNo = "";
        //Map<String, String> check = new HashMap<String, String>();
        for (int m = 2; m < sheet.getRows(); m++) {

            cscNo = String.valueOf(decodeNull(sheet.getCell(0, m).getContents()));
            // elcregisteNo = String.valueOf(decodeNull(sheet.getCell(1, m).getContents()));
            insuranceNo = String.valueOf(decodeNull(sheet.getCell(9, m).getContents()));
            saveDate(cscNo, insuranceNo, year);

            stringList.add(cscNo);

        }

        return stringList;

    }

    public List<String> checkOld(FileItem fileItem, int year) throws IOException {
        List<String> stringList = new ArrayList<String>();
        //stringList.add("错误信息");

        Vector<Vector<String>> productData = new Vector<Vector<String>>();
        List<Map<String, String>> listData = new ArrayList<Map<String, String>>();
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
        stringList = getColNum(title, stringList);
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

            stringList = getValueAndCheck(data, colNum, stringList, i, year);
            //listData.add(value);


        }
//        if(listData.size()==productData.size()-2){
//            for (int i = 0;i<listData.size();i++){
//                Map<String, String> value = listData.get(i);
//                saveDate(value.get("cicNo"),value.get("elcregisteNo"));
//            }
//
//        }

        if (stringList.size() == 0) {

            stringList.add("成功导入");
            System.out.println(stringList);
            return stringList;
        } else if (stringList.size() > 30) {
            List<String> stringList1 = new ArrayList<String>();
            stringList.add("错误信息太多请更正后重新导入！");
            System.out.println(stringList1);
            return stringList1;
        } else {
            stringList.add("以上是全部错误");
            System.out.println(stringList);
            return stringList;
        }


    }

    @Transactional
    public Vector<Vector<String>> doExcelImport(FileItem fileItem, int year) throws IOException {
        Vector<Vector<String>> productData = new Vector<Vector<String>>();
        List<Map<String, String>> listData = new ArrayList<Map<String, String>>();
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
        if (listData.size() == productData.size() - 2) {
            for (int i = 0; i < listData.size(); i++) {
                Map<String, String> value = listData.get(i);
                saveDate(value.get("cicNo"), value.get("elcregisteNo"), year);
            }
            return productData;
        } else {
            return new Vector<Vector<String>>();
            // throw new IOException("Excle文件数据错误");
        }

    }

    @Transactional
    public void saveDate(String cscNo, String elcregisteNo, int year) {
        String sql = "update SCMS_INSURANCE t set t.INSURNO = '" + elcregisteNo + "',t.PRESTA = 'AV0003'  " +
                " where t.YEAR = " + year + "  and t.studentid = (select s.id from scms_student s" +
                "  where s.cscid = '" + cscNo + "')";

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
                                          Map<String, Integer> colNum, List<String> list, int i, int year) throws IOException,
            UnsupportedEncodingException {
        Map<String, String> value = new HashMap<String, String>();
        List<String> stringList = list;
        String cicNo = (String) data.get(0).trim();
        String elcregisteNo = (String) data.get(1).trim();
        Student student = studentService.getStudentByCscId(cicNo);
        int m = 0;
        if (student != null) {
            m = getStuInsurance(student.getId(), year);
        }
        if ("".equals(cicNo)) {
            stringList.add("第" + (i + 1) + "行CSC登记号不能为空,");
        } else {
            if (m == 0) {
                stringList.add("第" + (i + 1) + "行CSC登记号不存在,");
            }
        }
        if ("".equals(elcregisteNo)) {
            stringList.add("第" + (i + 1) + "行保单号不能为空,");
        }


        // 校验通过的记录返回，否则抛出异常，存入failData中
        value.put("cicNo", cicNo);
        value.put("elcregisteNo", elcregisteNo);

        return stringList;
    }

    private List<String> getColNum(Vector<String> title, List<String> list) throws IOException {
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
            stringList.add("导入数据必须包含csc登记号！");
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

    public String[] getInsuranceIds(Filter filter, String mode, User user) {
        boolean needBasicInfo = filter.getPassportName() != null
                || filter.getContinent() != null
                || filter.getCountry() != null
                || filter.getProjectType() != null
                || filter.getProjectName() != null
                || filter.getPlanned() != null
                || filter.getDispatchType() != null
                || filter.getDispatch() != null
                || filter.getTravelType() != null
                || filter.getAnnual() != null;
        boolean needSchoolRoll = filter.getRegisterState() != null
                || filter.getStudentType() != null
                || filter.getFundType() != null
                || filter.getTeachLanguage() != null
                || filter.getSchoolRollState() != null
                || filter.getArrivalDateBegin() != null
                || filter.getArrivalDateEnd() != null
                || filter.getLeaveDateBegin() != null
                || filter.getLeaveDateEnd() != null
                || filter.getCramDateBeginBegin() != null
                || filter.getCramDateBeginEnd() != null
                || filter.getCramDateEndBegin() != null
                || filter.getCramDateEndEnd() != null
                || filter.getMajorStartDateBegin() != null
                || filter.getMajorStartDateEnd() != null
                || filter.getPlanLeaveDateBegin() != null
                || filter.getPlanLeaveDateEnd() != null
                || filter.getCurrentProvince() != null
                || filter.getCurrentUniversity() != null;
        StringBuffer select = new StringBuffer("select insurance.id");
        StringBuffer from = new StringBuffer(" from SCMS_INSURANCE insurance");
        StringBuffer where = new StringBuffer(" where 1=1");
        if (filter.getPreSta() != null) {
            where.append(" and insurance.PRESTA = '").append(filter.getPreSta()).append("'");
        }
        if ("insurance".equals(mode)) {
            where.append(" and insurance.INSURSTA = '1'");
        } else {
            where.append(" and insurance.INSURSTA = '0'");
        }
        Calendar calendar = Calendar.getInstance();
        int currnetYear = calendar.get(Calendar.YEAR);
        where.append(" and insurance.YEAR = ").append(currnetYear);

        from.append(",SCMS_STUDENT student");
        where.append(" and insurance.studentid = student.id");
        if (filter.getCscId() != null) {
            where.append(" and student.CSCID like '%").append(filter.getCscId()).append("%'");
        }

        if (needBasicInfo) {
            from.append(",SCMS_BASIC_INFO basicInfo");
            where.append(" and insurance.studentid = basicInfo.studentid");
            if (filter.getPassportName() != null) {
                where.append(" and basicInfo.PASSPORTNAME like '%").append(filter.getPassportName()).append("%'");
            }
            if (filter.getContinent() != null) {
                where.append(" and basicInfo.CONTINENT = '").append(filter.getContinent()).append("'");
            }
            if (filter.getCountry() != null) {
                where.append(" and basicInfo.COUNTRY = '").append(filter.getCountry()).append("'");
            }
            if (filter.getProjectType() != null) {
                where.append(" and basicInfo.PROJECTTYPE = '").append(filter.getProjectType()).append("'");
            }
            if (filter.getProjectName() != null) {
                where.append(" and basicInfo.PROJECTNAME = '").append(filter.getProjectName()).append("'");
            }
            if (filter.getPlanned() != null) {
                where.append(" and basicInfo.PLANNED = '").append(filter.getPlanned()).append("'");
            }
            if (filter.getDispatchType() != null) {
                where.append(" and basicInfo.DISPATCHTYPE = '").append(filter.getDispatchType()).append("'");
            }
            if (filter.getDispatch() != null) {
                where.append(" and basicInfo.DISPATCH = '").append(filter.getDispatch()).append("'");
            }
            if (filter.getTravelType() != null) {
                where.append(" and basicInfo.TRAVELTYPE = '").append(filter.getTravelType()).append("'");
            }
            if (filter.getAnnual() != null) {
                where.append(" and basicInfo.ANNUAL = ").append(filter.getAnnual());
            }
        }
        if (needSchoolRoll) {
            from.append(",SCMS_SCHOOLROLL schoolRoll");
            where.append(" and insurance.studentid = schoolRoll.studentid");
            if (filter.getRegisterState() != null) {
                where.append(" and schoolRoll.REGISTERSTATE = '").append(filter.getRegisterState()).append("'");
            }
            if (filter.getStudentType() != null) {
                where.append(" and schoolRoll.STUDENTTYPE = '").append(filter.getStudentType()).append("'");
            }
            if (filter.getFundType() != null) {
                where.append(" and schoolRoll.APPROPRIATION = '").append(filter.getFundType()).append("'");
            }
            if (filter.getTeachLanguage() != null) {
                where.append(" and schoolRoll.TEACHLANGUAGE = '").append(filter.getTeachLanguage()).append("'");
            }
            if (filter.getSchoolRollState() != null) {
                where.append(" and schoolRoll.STATE = '").append(filter.getSchoolRollState()).append("'");
            }
            if (filter.getArrivalDateBegin() != null) {
                where.append(" and schoolRoll.Arrivaldate >= to_date('").append(new SimpleDateFormat("yyyy-MM-dd").format(filter.getArrivalDateBegin())).append("','yyyy-mm-dd')");
            }
            if (filter.getArrivalDateEnd() != null) {
                where.append(" and schoolRoll.Arrivaldate <= to_date('").append(new SimpleDateFormat("yyyy-MM-dd").format(filter.getArrivalDateEnd())).append("','yyyy-mm-dd')");
            }
            if (filter.getLeaveDateBegin() != null) {
                where.append(" and schoolRoll.LEAVEDATE >= to_date('").append(new SimpleDateFormat("yyyy-MM-dd").format(filter.getLeaveDateBegin())).append("','yyyy-mm-dd')");
            }
            if (filter.getLeaveDateEnd() != null) {
                where.append(" and schoolRoll.LEAVEDATE <= to_date('").append(new SimpleDateFormat("yyyy-MM-dd").format(filter.getLeaveDateEnd())).append("','yyyy-mm-dd')");
            }
            if (filter.getCramDateBeginBegin() != null) {
                where.append(" and schoolRoll.CRAMDATEBEGIN >= to_date('").append(new SimpleDateFormat("yyyy-MM-dd").format(filter.getCramDateBeginBegin())).append("','yyyy-mm-dd')");
            }
            if (filter.getCramDateBeginEnd() != null) {
                where.append(" and schoolRoll.CRAMDATEBEGIN <= to_date('").append(new SimpleDateFormat("yyyy-MM-dd").format(filter.getCramDateBeginEnd())).append("','yyyy-mm-dd')");
            }
            if (filter.getCramDateEndBegin() != null) {
                where.append(" and schoolRoll.CRAMDATEEND >= to_date('").append(new SimpleDateFormat("yyyy-MM-dd").format(filter.getCramDateEndBegin())).append("','yyyy-mm-dd')");
            }
            if (filter.getCramDateEndEnd() != null) {
                where.append(" and schoolRoll.CRAMDATEEND <= to_date('").append(new SimpleDateFormat("yyyy-MM-dd").format(filter.getCramDateEndEnd())).append("','yyyy-mm-dd')");
            }
            if (filter.getMajorStartDateBegin() != null) {
                where.append(" and schoolRoll.MAJORSTARTDATE >= to_date('").append(new SimpleDateFormat("yyyy-MM-dd").format(filter.getMajorStartDateBegin())).append("','yyyy-mm-dd')");
            }
            if (filter.getMajorStartDateEnd() != null) {
                where.append(" and schoolRoll.MAJORSTARTDATE <= to_date('").append(new SimpleDateFormat("yyyy-MM-dd").format(filter.getMajorStartDateEnd())).append("','yyyy-mm-dd')");
            }
            if (filter.getPlanLeaveDateBegin() != null) {
                where.append(" and schoolRoll.PLANLEAVEDATE >= to_date('").append(new SimpleDateFormat("yyyy-MM-dd").format(filter.getPlanLeaveDateBegin())).append("','yyyy-mm-dd')");
            }
            if (filter.getPlanLeaveDateEnd() != null) {
                where.append(" and schoolRoll.PLANLEAVEDATE <= to_date('").append(new SimpleDateFormat("yyyy-MM-dd").format(filter.getPlanLeaveDateEnd())).append("','yyyy-mm-dd')");
            }
            if (filter.getCurrentUniversity() != null) {
                where.append(" and schoolRoll.CURRENTUNIVERSITY = '").append(filter.getCurrentUniversity()).append("'");
            }
            if (filter.getCurrentProvince() != null) {
                where.append(" and schoolRoll.CURRENTPROVINCE = '").append(filter.getCurrentProvince()).append("'");
            }
        }

        // 添加用户权限
        String userType = user.getUserType();
        String identity = user.getRole().getIdentity();
        String nodeId = user.getNode().getNodeId();
        if ("Y0002".equals(identity)) {
            List<Project> projects = user.getProjects();
            List dispatches = this.getDispatchesByUserId(user.getUserId());
            if(!needBasicInfo){
                from.append(",SCMS_BASIC_INFO basicInfo");
                where.append(" and insurance.studentid = basicInfo.studentid");
            }
            if(projects.size()>0){
                where.append(" and basicInfo.projectname in(");
                for (int i = 0; i < projects.size(); i++) {
                    where.append("'").append(projects.get(i).getProjectId()).append("',");
                }
                where.deleteCharAt(where.length()-1);
                where.append(")");
            }else{
                where.append(" and 1=2");
            }
            if(dispatches.size()>0){
                where.append(" and basicInfo.DISPATCH in(");
                for (int i = 0; i < dispatches.size(); i++) {
                    where.append("'").append(dispatches.get(i)).append("',");
                }
                where.deleteCharAt(where.length()-1);
                where.append(")");
            }else{
                where.append(" and 1=2");
            }
        }else if("2".equals(userType)){
            if(!needSchoolRoll){
                from.append(",SCMS_SCHOOLROLL schoolRoll");
                where.append(" and insurance.studentid = schoolRoll.studentid");
            }
            where.append(" and schoolRoll.CURRENTUNIVERSITY = '").append(nodeId).append("'");
        }
        StringBuffer order = new StringBuffer(" order by student.CSCID");
        String sql = select.append(from).append(where).append(order).toString();
        List list = this.queryListBySql(sql);
        String[] ids=new String[list.size()];
        for(int i=0;i<list.size();i++){
            Map<String,String> map = (Map)list.get(i);
            ids[i] = map.get("ID");
        }
        return ids;
    }
    @Transactional
    public void updateInsurancePrestaBySql(String[] id){
        StringBuffer sql = new StringBuffer("update SCMS_INSURANCE set PRESTA = 'AV0002' where PRESTA = 'AV0001' and ID in (");
        //you cant have a list with more than 1000 elements in a single "where" condition if you are working with Oracle DB
        int index = 0;
        for(int i=0;i<id.length;i++){
            sql.append("'").append(id[i]).append("',");
            index++;
            if(index == 1000){
                sql.deleteCharAt(sql.length()-1).append(") OR ID in (");
                index = 0;
            }
        }

        String updateSql =sql.deleteCharAt(sql.length()-1).append(")").toString();
        this.updateBySql(updateSql);
    }

}
