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
 * 角色服务类
 */

@Service("roleService")
public class RoleService extends BaseService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    public Role getRoleByRoleId(String roleId) {
        Role role = roleRepository.findOne(roleId);
        return initMenu(role);
    }

    public Role getRoleByRoleIdAndEnable(String roleId, String enable) {
        Role role = roleRepository.findRoleByRoleIdAndEnable(roleId, enable);
        return initMenu(role);
    }

    public List<Role> getRolesByEnable(String enable) {
        List<Role> roles = roleRepository.findRoleByEnable(enable);
        for (Role role : roles)
            initMenu(role);
        return roles;
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

        return saveRole(role);
    }

    public void deleteRole(Role role) throws RoleBeingUsedException, NoSuchRoleException {
        if (roleRepository.exists(role.getRoleId()))
            throw new NoSuchRoleException();

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

        if (Role.ENABLE.equals(role.getEnable())) {
            List<User> users = userService.getUsersByRole(role);
            if (users == null || users.size() == 0) {
                role.setEnable(Role.UNENABLE);
            } else
                throw new RoleBeingUsedException();
        } else
            role.setEnable(Role.ENABLE);
        saveRole(role);
    }

    private Role initMenu(Role role) {
        role.setMenus(menuService.getMenuByRole(role));
        return role;
    }
}


