package com.sf.jfinal.qs.core.tools.weixin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jfinal.log.Log;
import com.sf.jfinal.qs.core.tools.PathKit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefineMenu {
    private static Log log = Log.getLog(DefineMenu.class);

    public static void main(String[] args) throws JsonProcessingException {
        defineMenu("/sfwx");
    }

    /**
     * 自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。
     * @param ctxPath
     */
    public static String defineMenu(String ctxPath) throws JsonProcessingException {
        List<MenuItem> mainMenus = new ArrayList<>();
        mainMenus.add(defineMainMenu1(ctxPath));
        mainMenus.add(defineMainMenu2(ctxPath));
        mainMenus.add(defineMainMenu3(ctxPath));
        for (MenuItem item : mainMenus) {
            item.validate();
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        Map<String, List<MenuItem>> items = new HashMap<>();
        items.put("button", mainMenus);
        log.debug("menu is :\n" + mapper.writeValueAsString(items));
        return mapper.writeValueAsString(items);
    }

    /**
     * 定义第一个一级菜单
     */
    private static MenuItem defineMainMenu1(String ctxPath) {
        MenuItem main1 = new MenuItem("产品介绍");
        main1.setType(MenuItem.TYPE_VIEW);
        main1.setUrl(PathKit.join(ctxPath, "/intro"));
        return main1;
    }
    
    /**
     * 定义第二个一级菜单
     */
    private static MenuItem defineMainMenu2(String ctxPath) {
        MenuItem main1 = new MenuItem("我的专属码");
        main1.setType(MenuItem.TYPE_VIEW);
        main1.setUrl(PathKit.join(ctxPath, "/siteShareCode"));
        return main1;
    }
    
    /**
     * 定义第三个一级菜单
     */
    private static MenuItem defineMainMenu3(String ctxPath) {
        MenuItem main1 = new MenuItem("我的思方ERP");
        main1.setType(MenuItem.TYPE_VIEW);
        main1.setUrl(PathKit.join(ctxPath, "/redirect"));
        return main1;
    }
}
