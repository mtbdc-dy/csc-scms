package gov.gwssi.csc.scms.controller.dictionary;

import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.service.dictionary.TranslateDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangZhenghua on 2015/4/27.
 * 转义代码表(一层)Restful接口
 */

@RestController
@RequestMapping("/code-tables")
public class TranslateDictController {

    @Autowired
    private TranslateDictService translateDictService;

    @RequestMapping(
            method = RequestMethod.GET,
            headers = {"Accept=application/json;charset=utf-8"},
            params = {"codeTableName"}
    )
    public ResponseEntity<Map<String, List<DictTreeJson>>> getCodeTable(@RequestParam(value = "codeTableName") String[] codeTableNames){

        Map<String, List<DictTreeJson>> map = new HashMap<String, List<DictTreeJson>>();
        for (String codeTableName : codeTableNames) {
            map.put(codeTableName, translateDictService.getCodeTableList(codeTableName));
        }

        return new ResponseEntity<Map<String, List<DictTreeJson>>>(map, HttpStatus.OK);
    }

    @RequestMapping(value = "/{codeTableName}", method = RequestMethod.GET, headers = {"Accept=application/json;charset=utf-8"})
    public List<DictTreeJson> getCodeTable(@PathVariable String codeTableName) {
        System.out.println("TranslateDictController.getCodeTable");
        System.out.println("codeTableName = [" + codeTableName + "]");

        return translateDictService.getCodeTableList(codeTableName);
    }

}
