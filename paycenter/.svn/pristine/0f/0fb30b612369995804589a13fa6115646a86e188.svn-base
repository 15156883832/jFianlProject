package com.sf.jfinal.qs.core.tools.weixin;

import com.sf.jfinal.qs.core.tools.StrKit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuItem {

    public static final String TYPE_CLICK = "click";
    public static final String TYPE_VIEW = "view";
    public static final String TYPE_SCANCODE_PUSH = "scancode_push";
    public static final String TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";
    public static final String TYPE_PIC_SYSPHOTO = "pic_sysphoto";
    public static final String TYPE_PHOTO_OR_ALBUM = "pic_photo_or_album";
    public static final String TYPE_PIC_WEIXIN = "pic_weixin";
    public static final String TYPE_LOCAITON_SELECT = "location_select";
    public static final String TYPE_MEDIA_ID = "media_id";
    public static final String TYPE_VIEW_LIMITED = "view_limited";

    private String key;
    private String label;
    private String url;
    private String type;
    private String mediaId;
    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuItem(String label) {
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("name")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = SFWxKit.getAuthorizeURL(url, true);
    }

    public void setAbsoluteUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    @JsonProperty("sub_button")
    public List<MenuItem> getSubMenuItems() {
        return menuItems;
    }

    @JsonIgnore
    public void validate() {
        if (label == null || label.length() > 20) {
            throw new RuntimeException("label invalid:" + label);
        }

        if (StrKit.isNotBlank(type) && StrKit.isBlank(type)) {
            throw new RuntimeException("type is required:" + label);
        }

        // 微信规定二级菜单不应超过5个
        if (menuItems.size() > 5) {
            throw new RuntimeException("too many sub menu items");
        }

        Set<String> requireKeyTypes = new HashSet<>();
        requireKeyTypes.add(TYPE_CLICK);
        requireKeyTypes.add(TYPE_LOCAITON_SELECT);
        requireKeyTypes.add(TYPE_PHOTO_OR_ALBUM);
        requireKeyTypes.add(TYPE_PIC_SYSPHOTO);
        requireKeyTypes.add(TYPE_PIC_WEIXIN);
        requireKeyTypes.add(TYPE_SCANCODE_PUSH);
        requireKeyTypes.add(TYPE_SCANCODE_WAITMSG);
        if (StrKit.isBlank(key) && type != null && requireKeyTypes.contains(type)) {
            throw new RuntimeException("key is required for type: " + type);
        }

        if (StrKit.isBlank(url) && TYPE_VIEW.equals(type)) {
            throw new RuntimeException("url is required for type: " + type);
        }

        if (StrKit.isBlank(url) && TYPE_VIEW.equals(type)) {
            throw new RuntimeException("url is required for type: " + type);
        }

        if (StrKit.isBlank(mediaId) && (TYPE_VIEW_LIMITED.equals(type) || TYPE_MEDIA_ID.equals(type))) {
            throw new RuntimeException("media id is required for type: " + type);
        }

        for (MenuItem item : menuItems) {
            item.validate();
        }
    }
}
