package gov.gwssi.csc.scms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import gov.gwssi.csc.scms.controller.student.JsonBody;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Murray on 2015/4/28.
 */
public class StudentControllerTest {

    @Test
    public void jsonParmTest() {
        String jsonBody = "{\"value\" : \"{\'id\':\'sss\',\'name\':\'小明\'}\",\"log\" : \"null\",\"user\" : \"null\"}";
        try {
            JsonBody jbosy = new ObjectMapper().readValue(jsonBody, JsonBody.class);

            System.out.println("value :: " + jbosy.getValue());
            System.out.println("user :: " + jbosy.getUser());
            System.out.println("log :: " + jbosy.getLog());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
