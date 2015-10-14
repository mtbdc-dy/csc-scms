package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Menu;
import gov.gwssi.csc.scms.domain.user.Role;
import gov.gwssi.csc.scms.repository.user.MenuRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Lei on 2015/5/8.
 * 菜单服务类
 */
@Service("menuService")
public class MenuService extends BaseService {

    @Autowired
    MenuRepository menuRepository;

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public List<Menu> getMenusByIds(List<String> ids) {
        return (List<Menu>) menuRepository.findAll(ids);
    }

    public List<Menu> getMenuTree() {
        List<Menu> root = menuRepository.findMenuByMenuType(Menu.ROOT_LEVEL);
        return setParentNull(root);

    }

    public List<Menu> setParentNull(List<Menu> root) {
        if (root == null || root.size() == 0)
            return null;
        for (Menu menu : root) {
            menu.setParent(null);
            setParentNull(menu.getChildren());
        }
        return root;
    }

    public List<Menu> getMenuByRole(Role role) {
//        List<Menu> root = menuRepository.findMenuByRoleAndMenuType(role, Menu.ROOT_LEVEL);
        String sql = "select * from PUB_MENU where menuid in" +
                " (select menuid from PUB_ROLE_MENU where roleid = '"+role.getRoleId()+"')" +
                " and menutype = '1'";
        List<Menu> root = getBaseDao().queryTListBySql(sql, Menu.class);
        root = getChildrenMenuByRole(root, role);
        return root;
    }



    private List<Menu> getChildrenMenuByRole(Menu parentMenu, Role role) {
        List<Menu> menus = menuRepository.findMenuByRoleAndParent(role, parentMenu);
        return menus;
    }



    private List<Menu> getChildrenMenuByRole(List<Menu> menus, Role role) {
        if (menus == null || menus.size() == 0)
            return null;

        List<Menu> childrenNode;
        for (Menu menu : menus) {
            childrenNode = getChildrenMenuByRole(menu, role);
            menu.setChildren(childrenNode);
            menu.setParent(null);

            if (childrenNode != null && childrenNode.size() > 0) {
                getChildrenMenuByRole(childrenNode, role);
            }
        }
        return menus;
    }

}
