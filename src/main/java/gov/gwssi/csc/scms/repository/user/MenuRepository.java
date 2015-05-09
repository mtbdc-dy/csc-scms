package gov.gwssi.csc.scms.repository.user;

import gov.gwssi.csc.scms.domain.user.Menu;
import gov.gwssi.csc.scms.domain.user.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Lei on 2015/5/5.
 */
public interface MenuRepository extends CrudRepository<Menu, String> {

    List<Menu> findMenuByMenuType(String menuType);

    List<Menu> findMenuByRoleAndMenuType(Role role, String menuType);

    List<Menu> findMenuByRoleAndParent(Role role, Menu menu);


}
