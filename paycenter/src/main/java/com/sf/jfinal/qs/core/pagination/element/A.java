package com.sf.jfinal.qs.core.pagination.element;

public class A extends Element {
    public A() {
        super("a");
    }

    public A href(String href) {
        attr("href", href);
        return this;
    }
}
