package gov.gwssi.csc.scms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Murray on 2015/4/28.
 */
public class StudentControllerTest {

    @Test
    public void jsonParmTest() {
        String jsonBody = "{\"value\" : \"{\'id\':\'sss\',\'name\':\'小明\'}\",\"log\" : \"[{\'aa\' : \'bb\',\'cc\' : \'dd\'},{\'aa\' : \'bb\',\'cc\' : \'dd\'}]\",\"user\" : \"null\"}";
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
