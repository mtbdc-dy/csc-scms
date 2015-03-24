package gov.gwssi.csc.scms.domain;

import org.springframework.data.repository.CrudRepository;
/**
 * Created by WangZishi on 3/23/2015.
 */
public class UserIdGenerator {

    private int currentUserId;

    public int getCurrentUserId() {
        return currentUserId;
    }

    public int next(){
        return currentUserId + 1;
    }

}
