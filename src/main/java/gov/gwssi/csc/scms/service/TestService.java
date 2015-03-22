package gov.gwssi.csc.scms.service;


import gov.gwssi.csc.scms.dao.BaseDao;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by ynh2_000 on 3/22/2015.
 */
public class TestService {

    private BaseDao baseDao;

    @Required
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public void save (String data) {

        System.out.println("receiving data: " + data);

        baseDao.save(data);

    }
}
