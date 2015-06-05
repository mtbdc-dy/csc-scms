package gov.gwssi.csc.scms.utils;

import org.junit.Test;

import java.util.Map;

/**
 * Created by Lei on 2015/6/5.
 */
public class WebTokenTest {


    @Test
    public void tokenTest() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ";

        Map map = JWTUtil.decode(token);

        System.out.println("name:" + map.get("name"));
    }
}
