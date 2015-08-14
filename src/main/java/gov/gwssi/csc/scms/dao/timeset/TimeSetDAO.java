package gov.gwssi.csc.scms.dao.timeset;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.query.NewStuTimeSetResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/8/10.
 */
@Service("timeSetDAO")
public class TimeSetDAO extends BaseDAO {
    public List getNewStudentTimeSetList(String pro,String univ) {
        List<Map> getNewStudenTimeSettList = null;
        List<NewStuTimeSetResult> newStudenTimeSettList = new ArrayList<NewStuTimeSetResult>();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT t.province,t.univid,t.newsetby,t.newseted,t.newbegin,t.newend from dim_univ t where 1 = 1");
        if(!"".equals(pro)){
            stringBuilder.append(" and t.province = '"+pro+"'");
        }
        if(!"".equals(univ)){
            stringBuilder.append("  and t.univid = '"+univ+"'");
        }
        getNewStudenTimeSettList = super.queryListBySql(stringBuilder.toString());
        if (getNewStudenTimeSettList != null && getNewStudenTimeSettList.size() > 0) {
            for (Map map : getNewStudenTimeSettList) {
                Date newBegin = (Date) map.get("NEWBEGIN");
                String newSetBy = (String) map.get("NEWSETBY");
                Date newSeted = (Date) map.get("NEWSETED");
                String univId = (String) map.get("UNIVID");
                String province = (String) map.get("PROVINCE");
                Date newEnd = (Date) map.get("NEWEND");
                NewStuTimeSetResult newStuTimeSetResult = new NewStuTimeSetResult();

                boolean newBeginIsEmpty = newBegin == null || newBegin.toString().isEmpty();
                boolean newSetByIsEmpty = newSetBy == null || newSetBy.isEmpty();
                boolean newSetedIsEmpty = newSeted == null || newSeted.toString().isEmpty();
                boolean univIdIsEmpty = univId == null || univId.isEmpty();
                boolean provinceIsEmpty = province == null || province.isEmpty();
                boolean newEndIsEmpty = newEnd == null || newEnd.toString().isEmpty();

                newBegin = newBeginIsEmpty ? null : newBegin;
                newSetBy = newSetByIsEmpty ? "" : newSetBy;
                newSeted = newSetedIsEmpty ? null : newSeted;
                univId = univIdIsEmpty ? "" : univId;
                province = provinceIsEmpty ? "" : province;
                newEnd = newEndIsEmpty ? null : newEnd;


                newStuTimeSetResult.setNewBegin(newBegin);
                newStuTimeSetResult.setNewEnd(newEnd);
                newStuTimeSetResult.setNewSetBy(newSetBy);
                newStuTimeSetResult.setNewSeted(newSeted);
                newStuTimeSetResult.setProvince(province);
                newStuTimeSetResult.setUnivId(univId);


                newStudenTimeSettList.add(newStuTimeSetResult);

            }
        }
       // System.out.println("11111");
        return newStudenTimeSettList;
    }
    public int setTime(String userName,String begin,String end,String ids){
        //ids转为('1','2'...)
        StringBuilder sbIds = new StringBuilder("(");

        String idss[] = ids.split(",");
        for (String id : idss) {
            sbIds.append("'").append(id).append("',");
        }
        sbIds.setCharAt(sbIds.length() - 1, ')');
String sql = "update dim_univ t set t.newsetby = '"+userName+"' ,t.newseted = sysdate," +
        " t.newbegin = to_date('" + begin + "','yyyy-mm-dd'),t.newend = to_date('"+end+"','yyyy-mm-dd')" +
        " where t.univid in"+sbIds;
        System.out.print("sql="+sql);
        int m = super.updateBySql(sql);

        System.out.print("m="+m);
        return m;
    }
}
