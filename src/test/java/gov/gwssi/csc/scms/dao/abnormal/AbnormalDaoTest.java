package gov.gwssi.csc.scms.dao.abnormal;

import gov.gwssi.csc.scms.base.UnitTestBase;
import org.junit.Test;

import java.util.List;

/**
 * Created by lzs on 2015/4/29.
 */

public class AbnormalDaoTest  extends UnitTestBase {
    @Test
    public  void testQueryAllAbnormal(){
        AbnormalDAO abnormalDAO = getBean("abnormalDAO");
         List abnormalList = abnormalDAO.queryAllAbnormal();
    }


}
