package gov.gwssi.csc.scms.utils;

import org.junit.Test;

/**
 * Created by Lei on 2015/5/7.
 */
public class UtilsTest {

    @Test
    public void UnicodeTest(){

        String str = "큰바보";

        System.out.println("str ====== ：" + str);
        String str1 = UnicodeUtil.toUNICODE(str);
        System.out.println("str1 ====== ：" + str1);
        String str2 = UnicodeUtil.toCharSequence(str1);
        System.out.println("str2 ====== ：" + str2);

    }
}
