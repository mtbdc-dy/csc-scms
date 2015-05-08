package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Menu;
import gov.gwssi.csc.scms.repository.user.MenuRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lei on 2015/5/8.
 * 菜单服务类
 */
@Service("menuService")
public class MenuService extends BaseService {

    @Autowired
    MenuRepository menuRepository;

    public List<Menu> getMenusByIds(List<String> ids) {
        return (List<Menu>) menuRepository.findAll(ids);
    }

    public Menu getMenuByMenuId(String menuId) {
        return menuRepository.findOne(menuId);
    }

    public List<Menu> getAllMenus() {
        return (List<Menu>) menuRepository.findAll();
    }
}
