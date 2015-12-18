package gov.gwssi.csc.scms.dao.scholarshipJ;

import gov.gwssi.csc.scms.dao.BaseDAO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gc on 2015/8/18.
 * scholarshipJ DAO封装类
 */
@Service("scholarshipJDAO")
public class ScholarshipJDAO extends BaseDAO {
    public List getListBy(String year,String state,String province,String univ,String qualified) {
        List allList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * from v_scholarship_unreport t where 1 = 1");
        if(!"".equals(year)&&(!year.equals("null"))){
            stringBuilder.append(" and t.year like '%"+year+"%'");
        }
        if(!"".equals(state)&&(!state.equals("null"))){
            stringBuilder.append("  and t.state = '"+state+"'");
        }
        if(!"".equals(province)&&(!province.equals("null"))){
            stringBuilder.append(" and t.province like '%"+province+"%'");
        }
        if(!"".equals(univ)&&(!univ.equals("null"))){
            stringBuilder.append("  and t.univ like '%"+univ+"%'");
        }
        if(!"".equals(qualified)&&(!qualified.equals("null"))){
            stringBuilder.append("  and t.qualified = '"+qualified+"'");
        }
        stringBuilder.append(" order by t.state desc");
        allList = super.queryListBySql(stringBuilder.toString());
        return allList;
    }
    public List getDetailListBy(String scholarshipId) {
        List allList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * from SCMS_SCHOLARSHIP_DETAIL t where  scholarshipid = '"+scholarshipId+"'");
        allList = super.queryListBySql(stringBuilder.toString());
        return allList;
    }
    public List getDetailListExport(String scholarshipId) {
        List allList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * from SCMS_SCHOLARSHIP_DETAIL t where  scholarshipid = '"+scholarshipId+"' and cscresult in ('AP0002','AP0003','AP0004')");
        allList = super.queryListBySql(stringBuilder.toString());
        return allList;
    }

}
