package gov.gwssi.csc.scms.utils;

import org.junit.Test;

import java.util.Map;

/**
 * Created by Lei on 2015/6/5.
 */
public class WebTokenTest {

    @Test
    public void tokenTest () {
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJyb290IiwiZW1haWwiOiJ5bmguMkBvdXRsb29rLmNvbSIsInBpY3R1cmUiOiIvYXBpL3VzZXJzL3VuZGVmaW5lZC9waWN0dXJlIiwiaWF0IjoxNDMzNzQyNjk5LCJleHAiOjE0NDE1MTg2OTl9._Ec-OjrF1sXCEjapDzx6B5e8Mb9Jn-rXsrLZb199pgI";

        Map<String, Object> map = JWTUtil.decode(token);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " :: " + entry.getValue());
        }
    }
}
