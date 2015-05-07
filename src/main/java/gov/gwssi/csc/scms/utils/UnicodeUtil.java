package gov.gwssi.csc.scms.utils;

import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by Lei on 2015/5/7.
 */
public class UnicodeUtil {
    public static String toUNICODE(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            //ASCII表中的字符码值不够4位,补00
            if (s.charAt(i) < 256) {
                sb.append("\\00");
            } else {
                sb.append("\\");
            }
            sb.append(Integer.toHexString(s.charAt(i)));
        }
        return sb.toString();
    }

    public static String toCharSequence(String unicodeValues) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < unicodeValues.length() - 4; i++) {
            if (unicodeValues.charAt(i) == '\\') {
                String str = unicodeValues.substring(i + 1, i + 5);
                int ch = Integer.valueOf(str, 16);
                sb.append((char) ch);
            }
        }
        return sb.toString();
    }

}
