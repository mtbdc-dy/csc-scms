package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Menu;
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

    public final static String ROOT_LEVEL = "1";

    @Autowired
    MenuRepository menuRepository;

    public Menu getMenuByMenuId(String menuId) {
        return menuRepository.findOne(menuId);
    }

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public List<Menu> getMenuTree() {
        List<Menu> root = menuRepository.findMenuByMenuType(ROOT_LEVEL);
        return root;
    }

    private List<Menu> getMenuRoot() {
        return null;
    }

    public List<Menu> getAllMenus() {
        return (List<Menu>) menuRepository.findAll();
    }
}
