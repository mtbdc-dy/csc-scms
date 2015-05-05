package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Role;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.user.RoleRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lei on 2015/5/5.
 */

@Service("roleService")
public class RoleService extends BaseService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    public Role getRoleByRoleId(String roleId) {
        return roleRepository.findOne(roleId);
    }

    public Role addRole(Role role) {
//        role.setRoleId();
        return saveRole(role);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Role role) throws NoSuchRoleException {
        Role role1 = getRoleByRoleId(role.getRoleId());
        if (role1 == null)
            throw new NoSuchRoleException();
        role.setMenus(role1.getMenus());
        return saveRole(role);
    }

    public void deleteRole(Role role) throws RoleBeingUsedException {
        List<User> users = userService.getUsersByRole(role);
        if (users == null || users.size() == 0) {
            roleRepository.delete(role);
        } else
            throw new RoleBeingUsedException();
    }

    public void enableRole(String roleId) throws RoleBeingUsedException, NoSuchRoleException {
        Role role = getRoleByRoleId(roleId);

        if (role == null)
            throw new NoSuchRoleException();

        if (role.getEnable() == "1") {
            List<User> users = userService.getUsersByRole(role);
            if (users == null || users.size() == 0) {
                role.setEnable("0");
            } else
                throw new RoleBeingUsedException();
        } else
            role.setEnable("1");
        saveRole(role);
    }
}


