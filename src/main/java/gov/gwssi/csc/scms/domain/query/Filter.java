package gov.gwssi.csc.scms.domain.query;

import gov.gwssi.csc.scms.domain.user.User;

/**
 * Created by Murray on 2015/4/2.
 */
public interface Filter {

    String getFilter(User user,String modleType,String userTpye);
}
