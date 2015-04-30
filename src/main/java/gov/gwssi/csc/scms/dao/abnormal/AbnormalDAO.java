package gov.gwssi.csc.scms.dao.abnormal;

import gov.gwssi.csc.scms.dao.BaseDAO;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by lzs on 2015/4/23.
 * 异动申请dao
 */
@Service("abnormalDAO")
public class AbnormalDAO extends BaseDAO {
    //select all abnormal
    public List queryAllAbnormal(){
        List abnormalList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select v.cscid,Translate(v.passportname USING CHAR_CS) passportname,v.gender,v.country,v.state rollState,a.applyusername,a.applytime,a.state handleState  from v_query_conditions v , scms_abnormal a");
        stringBuilder.append(" where v.id = a.studentid");
        //stringBuilder.append("select * from v_query_conditions");
        System.out.println(stringBuilder.toString());
        abnormalList = super.queryListBySql(stringBuilder.toString());
        System.out.println(stringBuilder.toString());
        return abnormalList;
    }

}
