package gov.gwssi.csc.scms.utils;

import com.auth0.jwt.JWTVerifier;

import java.util.Map;

/**
 * Created by Lei on 2015/6/5.
 */
public class JWTUtil {

    public static final String HEADER_AUTHORIZATION = "Authorization";

    private static String TOKEN_SECRET = "We need Wang Zhenghua to save the world!";

    public static Map<String, Object> decode(String secretStr) {
        try {
            if(secretStr.startsWith("Bearer ")){
                secretStr = secretStr.substring(7).trim();
            }
            byte[] secret = TOKEN_SECRET.getBytes();
            Map<String, Object> decodedPayload =
                    new JWTVerifier(secret).verify(secretStr);
            return decodedPayload;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
