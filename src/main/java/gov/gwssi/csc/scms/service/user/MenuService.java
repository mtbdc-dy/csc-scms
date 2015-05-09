package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Menu;
import gov.gwssi.csc.scms.domain.user.Role;
import gov.gwssi.csc.scms.repository.user.MenuRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lei on 2015/5/8.
 * 菜单服务类
 */
@Service("menuService")
public class MenuService extends BaseService {

    @Autowired
    MenuRepository menuRepository;

    public Menu getMenuByMenuId(String menuId) {
        return menuRepository.findOne(menuId);
    }

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public List<Menu> getMenuTree() {
        List<Menu> root = menuRepository.findMenuByMenuType(Menu.ROOT_LEVEL);
        return root;
    }

    public List<Menu> getMenuByRole(Role role) {
        List<Menu> root = menuRepository.findMenuByRoleAndMenuType(role, Menu.ROOT_LEVEL);
        root = getChildrenMenuByRole(root, role);
        return root;
    }

    private List<Menu> getChildrenMenuByRole(Menu parentMenu, Role role) {
        List<Menu> childrenNode = menuRepository.findMenuByRoleAndParent(role, parentMenu);
        return childrenNode;
    }

    private List<Menu> getChildrenMenuByRole(List<Menu> menus, Role role) {
        if (menus == null || menus.size() == 0)
            return null;

        List<Menu> childrenNode = null;
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
