package example.com.cv4j.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhigang on 2018/1/2.
 */

public class MenuItemsFactory implements MenuConstants {

    public static List<String> initFilterMenuItems() {
        List<String> menuItems = new ArrayList<>();
        menuItems.add(FastEPFilter);
        menuItems.add(SinCityFilter);
        menuItems.add(FloSteDitheringFilter);
        menuItems.add(GlowFilter);
        menuItems.add(OilPaintFilter);
        return menuItems;
    }

    public static List<String> initFileMenuItems() {
        List<String> menuItems = new ArrayList<>();
        menuItems.add(FILE_OPEN);
        return menuItems;
    }

}
