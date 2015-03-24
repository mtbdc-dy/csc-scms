package gov.gwssi.csc.scms.service;

import org.springframework.stereotype.Service;

/**
 * Created by ynh2_000 on 3/23/2015.
 */
@Service
public class UserService extends BaseService {

    public int getCurrentMaxUserId() {

        super.getBean("baseDao");

        return 1;

    }
}
