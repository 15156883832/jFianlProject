package com.sf.jfinal.qs.core.pagination.element;

import com.sf.jfinal.qs.core.pagination.Page;
import com.sf.jfinal.qs.core.pagination.Renderable;
import com.sf.jfinal.qs.core.tools.StrKit;

import java.util.*;

/**
 * @author gaols
 */
public class Element implements Renderable {
    private final String tagName;
    private String text;
    private String id;
    private Map<String, String> attr = new TreeMap<>();
    private List<Element> innerElements = new ArrayList<Element>();

    public String render(Page<?> page) {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(tagName);
        renderAttributes(sb);
        if (StrKit.isBlank(text) && innerElements.isEmpty()) {
            sb.append("/>");
        } else {
            sb.append(">");
            renderInnerContent(page, sb);
            sb.append("</");
            sb.append(tagName);
            sb.append(">");
        }
        return sb.toString();
    }

    private void renderInnerContent(Page<?> page, StringBuilder sb) {
        if (StrKit.isNotBlank(text)) {
            sb.append(text);
        } else {
            for (Element element : innerElements) {
                sb.append(element.render(page));
            }
        }
    }

    private void renderAttributes(StringBuilder sb) {
        Set<Map.Entry<String, String>> entrySet = attr.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            sb.append(" ");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append("\"");
            sb.append(entry.getValue());
            sb.append("\"");
        }
    }

    public Element(String tag) {
        this.tagName = tag;
    }

    public Element text(String text) {
        this.text = text;
        return this;
    }

    public Element addChild(Element element) {
        innerElements.add(element);
        return this;
    }

    public Element attr(String name, String value) {
        attr.put(name, value);
        return this;
    }

    public Element clazz(String clazz) {
        attr("class", clazz);
        return this;
    }

    public Element id(String id) {
        attr("id", id);
        return this;
    }

    public Element appendTo(Element parent) {
        parent.addChild(this);
        return this;
    }

    public String getTagName() {
        return tagName;
    }
}
