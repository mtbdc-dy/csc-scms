package gov.gwssi.csc.scms.controller.dictionary;

import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.service.dictionary.TranslateDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/4/27.
 * 转义代码表(一层)Restful接口
 */

@RestController
@RequestMapping("/code-tables")
public class TranslateDictController {

    @Autowired
    private TranslateDictService translateDictService;

    @RequestMapping(value = "/{codeTableName}", method = RequestMethod.GET, headers = {"Accept=application/json;charset=utf-8"})
    public List<DictTreeJson> getCodeTable(@PathVariable String codeTableName) {
        System.out.println("TranslateDictController.getCodeTable");
        System.out.println("codeTableName = [" + codeTableName + "]");

        List<DictTreeJson> list;

        try {
            if ("continents".equals(codeTableName)) {

                list = translateDictService.getContinentsWithCountries();

            } else if ("projectAttrs".equals(codeTableName)) {

                list = translateDictService.getProjectsWithTypeAndName();

            } else if ("provincesOnly".equals(codeTableName)) {

                list = translateDictService.getProvinces();

            } else if ("provincesUniversities".equals(codeTableName)) {

                list = translateDictService.getUniversities();

            } else if ("disciplines".equals(codeTableName)) {

                list = translateDictService.getSubjectsWithLeveThree();

            } else if ("abnormalTypes".equals(codeTableName)) {

                list = translateDictService.getAbnormalReasonsWithType();

            } else if ("tables".equals(codeTableName)) {

                // TODO.
                list = null;

            } else {

                list = translateDictService.getTranslateDictByClassEn(codeTableName);

            }


            System.out.println("list = " + list);

        } catch (Exception e) {

            e.printStackTrace();
            throw new RuntimeException(e);

        }

        return list;
    }

}
