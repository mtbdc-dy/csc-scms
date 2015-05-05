 package gov.gwssi.csc.scms.service.abnormal;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;

import gov.gwssi.csc.scms.domain.query.AbnormalFilterObject;
import gov.gwssi.csc.scms.domain.query.AbnormalResultObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

 /**
 * Created by lzs on 2015/4/27.
 */
public class AbnormalServiceTest  extends UnitTestBase {
    @Test
    public void getAbnormalAllTest() {
        AbnormalService abnormalService = getBean("abnormalService");
        String body = "{\"cscId\" : \"1\" ," +
                "\"offSet\" : \"0\" , \"pageSize\" : \"2\"}";
        AbnormalFilterObject abnormalResultObject;
        List<AbnormalResultObject> list1 = null;
        try {
            abnormalResultObject=new ObjectMapper().readValue(body, AbnormalFilterObject.class);
            list1 =  abnormalService.getAbnormalsByFilter(abnormalResultObject,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(list1);
        System.out.println("list size::" + list1.size());
        for (AbnormalResultObject sro : list1) {
            System.out.println("==============================");
            System.out.println("studentId::" + sro.getStudentId());
            System.out.println("CscId::" + sro.getCscId());

        }
    }
}
