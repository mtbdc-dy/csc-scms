package gov.gwssi.csc.scms.controller.dictionary;

import gov.gwssi.csc.scms.service.dictionary.TranslateDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WangZhenghua on 2015/4/27.
 * 转义代码表(一层)Restful接口
 */

@RestController
@RequestMapping("api/codeTable")
public class TranslateDictController {

    @Autowired
    private TranslateDictService translateDictService;

    private final String TRANSLATE_XTJFBZ = "B";
    private final String TRANSLATE_XTLHYY = "C";
    private final String TRANSLATE_XTXXCC = "D";
    private final String TRANSLATE_XTXJZZ = "F";
    private final String TRANSLATE_XTXSLB = "G";
    private final String TRANSLATE_XTGJLV = "H";
    private final String TRANSLATE_XTXW = "I";
    private final String TRANSLATE_XTDZLB = "J";
    private final String TRANSLATE_XTRJZJZL = "K";
    private final String TRANSLATE_XTTFSJCLZT = "L";
    private final String TRANSLATE_XTTFSJLB = "M";
    private final String TRANSLATE_XTTJQK = "N";
    private final String TRANSLATE_XTJFBFB = "O";
    private final String TRANSLATE_XTSS = "P";
    private final String TRANSLATE_XTYY = "Q";
    private final String TRANSLATE_XTPQTJ = "R";
    private final String TRANSLATE_XTYX = "S";
    private final String TRANSLATE_XTSF = "Y";

    // 获取资源-系统经费标准
    @RequestMapping(value="xtjfbz",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtgfbz(){
        String xtgfbzJsonData = "[]";
        try{
            xtgfbzJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTJFBZ);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtgfbzJsonData;
    }

    // 获取资源-系统离华原因
    @RequestMapping(value="xtlhyy",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtLhyy(){
        String xtlhyyJsonData = "[]";
        try{
            xtlhyyJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTLHYY);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtlhyyJsonData;
    }

    // 获取资源-学习层次
    @RequestMapping(value="xtxxcc",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtxxcc(){
        String xtxxccJsonData = "[]";
        try{
            xtxxccJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTXXCC);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtxxccJsonData;
    }

    // 获取资源-学籍状态
    @RequestMapping(value="xtxjzt",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtxjzt(){
        String xtxjzzJsonData = "[]";
        try{
            xtxjzzJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTXJZZ);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtxjzzJsonData;
    }

    // 获取资源-学生类别
    @RequestMapping(value="xtxslb",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtxslb(){
        String xtxslbJsonData = "[]";
        try{
            xtxslbJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTXSLB);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtxslbJsonData;
    }

    // 获取资源-国际旅费
    @RequestMapping(value="xtgjlv",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtgjlv(){
        String xtgjlvJsonData = "[]";
        try{
            xtgjlvJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTGJLV);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtgjlvJsonData;
    }

    // 获取资源-系统学位
    @RequestMapping(value="xtxw",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtxw(){
        String xtxwJsonData = "[]";
        try{
            xtxwJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTXW);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtxwJsonData;
    }

    // 获取资源-系统地址类别
    @RequestMapping(value="xtdzlb",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtdzlb(){
        String xtdzlbJsonData = "[]";
        try{
            xtdzlbJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTDZLB);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtdzlbJsonData;
    }

    // 获取资源-入境证件种类
    @RequestMapping(value="xtrjzjzl",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getRjzjzl(){
        String xtrjzjzlJsonData = "[]";
        try{
            xtrjzjzlJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTRJZJZL);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtrjzjzlJsonData;
    }

    // 获取资源-系统突发事件处理状态
    @RequestMapping(value="xttfsjclzt",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXttfsjclzt(){
        String xttfsjclztJsonData = "[]";
        try{
            xttfsjclztJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTTFSJCLZT);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xttfsjclztJsonData;
    }

    // 获取资源-系统突发事件类别
    @RequestMapping(value="xttfsjlb",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXttfsjlb(){
        String xttfsjlbJsonData = "[]";
        try{
            xttfsjlbJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTTFSJLB);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xttfsjlbJsonData;
    }

    // 获取资源-系统体检情况
    @RequestMapping(value="xttjqk",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXttjqk(){
        String xttjqkJsonData = "[]";
        try{
            xttjqkJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTTJQK);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xttjqkJsonData;
    }

    // 获取资源-系统经费办法表
    @RequestMapping(value="xtjfbfb",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtjfbfb(){
        String xtjfbfbJsonData = "[]";
        try{
            xtjfbfbJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTJFBFB);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtjfbfbJsonData;
    }

    // 获取资源-系统省市
    @RequestMapping(value="xtss",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtss(){
        String xtssJsonData = "[]";
        try{
            xtssJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTSS);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtssJsonData;
    }

    // 获取资源-系统语言
    @RequestMapping(value="xtyy",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtyy(){
        String xtyyJsonData = "[]";
        try{
            xtyyJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTYY);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtyyJsonData;
    }

    // 获取资源-系统派遣条件
    @RequestMapping(value="xtpqtj",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtpqtj(){
        String xtpqtjJsonData = "[]";
        try{
            xtpqtjJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTPQTJ);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtpqtjJsonData;
    }

    // 获取资源-系统院校
    @RequestMapping(value="xtyx",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtyx(){
        String xtyxJsonData = "[]";
        try{
            xtyxJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTYX);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtyxJsonData;
    }

    // 获取资源-系统院校
    @RequestMapping(value="xtsf",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getXtsf(){
        String xtsfJsonData = "[]";
        try{
            xtsfJsonData = translateDictService.getTranslateDictJsonData(TRANSLATE_XTSF);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return xtsfJsonData;
    }

}
