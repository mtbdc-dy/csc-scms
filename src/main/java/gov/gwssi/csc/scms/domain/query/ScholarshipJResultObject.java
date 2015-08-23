package gov.gwssi.csc.scms.domain.query;


import java.util.Date;

/**
 * Created by gc on 2015/8/18.
 * 奖学金评审管理信息列表结果类-JJW
 */
public class ScholarshipJResultObject extends ResultObject{
    /**
     * ID   奖学金子表id
     */
    private String id;


    /**
     * 年度
     */
    private Long year;
    /**
     * 奖学金评审状态
     */
    
    private String state;
    /**
     * 省市
     */
    
    private String province;
    /**
     * 当前院校名称
     */
    
    private String univ;
    /**
     * 学校评审提交时间
     */
    
    private Date schooldate;
    /**
     * 是否有不合格人数
     */
    
    private String qualified;
    /**
     * 参评人数
     */
    
    private Long count;
    /**
     * 合格人数
     */
    
    private Long qualnum;
    /**
     * 奖学金评审状态
     */
    
    private Long unqualnum;
    /**
     * 基金委批复时间
     */
    
    private Date cscdate;


    public ScholarshipJResultObject(String id, Long year,String state , String province, String univ,Date schooldate ,String qualified ,Long count ,Long qualnum ,Long unqualnum,Date cscdate
    ) {
        this.id = id;
        this.year=year;
        this.state=state;
        this.province=province;
        this.univ=univ;
        this.schooldate=schooldate;
        this.qualified=qualified;
        this.count=count;
        this.qualnum=qualnum;
        this.unqualnum=unqualnum;
        this.cscdate=cscdate;
    }

    public static String getResultObject() {

        String resultSql = "select new gov.gwssi.csc.scms.domain.query.ScholarshipJResultObject(" +
                "ScholarshipJ.id,ScholarshipX.year,ScholarshipX.state, student.province, ScholarshipX.univ,ScholarshipX.schooldate," +
                "ScholarshipX.qualified,ScholarshipX.count,ScholarshipX.qualnum," +
                "ScholarshipX.unqualnum,ScholarshipX.cscdate)";
        return resultSql;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUniv() {
        return univ;
    }

    public void setUniv(String univ) {
        this.univ = univ;
    }

    public Date getSchooldate() {
        return schooldate;
    }

    public void setSchooldate(Date schooldate) {
        this.schooldate = schooldate;
    }

    public String getQualified() {
        return qualified;
    }

    public void setQualified(String qualified) {
        this.qualified = qualified;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getQualnum() {
        return qualnum;
    }

    public void setQualnum(Long qualnum) {
        this.qualnum = qualnum;
    }

    public Long getUnqualnum() {
        return unqualnum;
    }

    public void setUnqualnum(Long unqualnum) {
        this.unqualnum = unqualnum;
    }

    public Date getCscdate() {
        return cscdate;
    }

    public void setCscdate(Date cscdate) {
        this.cscdate = cscdate;
    }
}
