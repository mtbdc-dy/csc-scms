package gov.gwssi.csc.scms.controller.dictionary;

import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.domain.dictionary.TranslateDictJson;
import gov.gwssi.csc.scms.service.dictionary.TranslateDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<TranslateDictJson> getXtgfbz(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTJFBZ);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-系统离华原因
    @RequestMapping(value="xtlhyy",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtLhyy(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTLHYY);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-学习层次
    @RequestMapping(value="xtxxcc",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtxxcc(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTXXCC);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-学籍状态
    @RequestMapping(value="xtxjzt",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtxjzt(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTXJZZ);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-学生类别
    @RequestMapping(value="xtxslb",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtxslb(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTXSLB);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-国际旅费
    @RequestMapping(value="xtgjlv",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtgjlv(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTGJLV);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-系统学位
    @RequestMapping(value="xtxw",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtxw(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTXW);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-系统地址类别
    @RequestMapping(value="xtdzlb",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtdzlb(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTDZLB);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-入境证件种类
    @RequestMapping(value="xtrjzjzl",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getRjzjzl(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTRJZJZL);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-系统突发事件处理状态
    @RequestMapping(value="xttfsjclzt",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXttfsjclzt(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTTFSJCLZT);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-系统突发事件类别
    @RequestMapping(value="xttfsjlb",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXttfsjlb(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTTFSJLB);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-系统体检情况
    @RequestMapping(value="xttjqk",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXttjqk(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTTJQK);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-系统经费办法表
    @RequestMapping(value="xtjfbfb",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtjfbfb(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTJFBFB);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-系统省市
    @RequestMapping(value="xtss",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtss(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTSS);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-系统语言
    @RequestMapping(value="xtyy",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtyy(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTYY);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-系统派遣条件
    @RequestMapping(value="xtpqtj",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtpqtj(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTPQTJ);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-系统院校
    @RequestMapping(value="xtyx",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtyx(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTYX);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // 获取资源-系统院校
    @RequestMapping(value="xtsf",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TranslateDictJson> getXtsf(){
        List<TranslateDictJson> list = null;
        try{
            list = translateDictService.getTranslateDictByClassId(TRANSLATE_XTSF);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

}
