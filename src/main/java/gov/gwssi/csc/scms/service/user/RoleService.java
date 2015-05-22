package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Menu;
import gov.gwssi.csc.scms.domain.user.Role;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.user.RoleRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        if (role == null)
            return null;
        return initMenu(role);
    }

    public List<Role> getRolesByEnable(String enable) {
        List<Role> roles = roleRepository.findRoleByEnable(enable);
        for (Role role : roles)
            initMenu(role);
        return roles;
    }

    public Role addRole(Role role, User user) {
        getRealMenu(role);
        role.setRoleId(getBaseDao().getIdBySequence("seq_role"));
        role.setCreateBy(user.getUserId());
        role.setCreateDate(new Date());
        return saveRole(role);
    }

    public Role saveRole(Role role) {
        return initMenu(roleRepository.save(role));
    }

    public Role updateRole(Role role, User user) throws NoSuchRoleException {
        Role role1 = getRoleByRoleId(role.getRoleId());
        if (role1 == null)
            throw new NoSuchRoleException("can not find the role with a roleId:" + role.getRoleId());
        getRealMenu(role);
        return doUpdateRole(role, user);
    }

    public Role deleteRole(String roleId, User user) throws RoleBeingUsedException, NoSuchRoleException {
        Role role = getRoleByRoleIdAndEnable(roleId, Role.ENABLE);
        if (role == null)
            throw new NoSuchRoleException("can not find the enabled role for delete:" + roleId);

        List<User> users = userService.getUsersByRole(role);
        if (users == null || users.size() == 0) {
            role.setEnable(Role.UNENABLE);
            return doUpdateRole(role, user);
        } else
            throw new RoleBeingUsedException("role is used by user:" + role.getRoleId());
    }

    private Role initMenu(Role role) {
        role.setMenus(menuService.getMenuByRole(role));
        return role;
    }

    private Role doUpdateRole(Role role, User user) {
        role.setUpdateBy(user.getUserId());
        role.setUpdateDate(new Date());
        return saveRole(role);
    }

    private void getRealMenu(Role role) {
        List<Menu> menus = role.getMenus();
        List<String> menuIds = new ArrayList<String>(menus.size());
        for (int index = 0; index < menus.size(); index++) {
            menuIds.set(index, menus.get(index).getMenuId());
        }
        menus = menuService.getMenusByIds(menuIds);
        role.setMenus(menus);
    }
}


