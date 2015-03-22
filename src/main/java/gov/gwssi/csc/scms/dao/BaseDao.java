package gov.gwssi.csc.scms.dao;

import org.springframework.stereotype.Component;

/**
 * Created by WangZishi on 3/22/2015.
 */
@Component
public class BaseDao {
    public void save(String data){
        System.out.println("[BaseDao] saving: " + data);
    }
}
