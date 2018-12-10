package com.sf.jfinal.qs.core.pagination;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

import java.util.List;

public class Page<T> {
    private int totalPage;
    private String link;
    private List<T> data;
    private int pageNumber;
    private int pageSize;
    private int totalRow;

    /**
     * Constructor.
     *
     * @param pageNumber the page number
     * @param pageSize   the page size
     */
    public Page(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        bound();
    }

    public Page(int pageNumber) {
        this(pageNumber, PropKit.getInt("pagination.pageSize", 10));
    }

    public Page(Controller ctr) {
        this(ctr.getParaToInt("page", 1), ctr.getParaToInt("pageSize", PropKit.getInt("pagination.pageSize", 10)));
    }

    private void bound() {
        this.pageNumber = Math.max(1, this.pageNumber);
    }

    public Page<T> href(String href) {
        this.link = href;
        return this;
    }

    public Page<T> data(List<T> data) {
        this.data = data;
        return this;
    }

    public Page<T> totalRow(int totalRow) {
        this.totalRow = totalRow;
        this.totalPage = totalRow / pageSize;
        if (totalRow % pageSize != 0) {
            this.totalPage++;
        }
        return this;
    }

    @Override
    public String toString() {
        return render();
    }

    public String render() {
        return renderer.render(this);
    }

    public void setRenderer(Renderable renderable) {
        this.renderer = renderable;
    }

    private Renderable renderer = new SimplePaginationRenderer();

    public String getHref() {
        return link;
    }

    /**
     * Return page number.
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Return page size.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Return total page.
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * Return total row.
     */
    public int getTotalRow() {
        return totalRow;
    }

    /**
     * Return the data for this page.
     */
    public List<T> getData() {
        return data;
    }

    public boolean isFirstPage() {
        return pageNumber == 1;
    }

    public boolean isLastPage() {
        return pageNumber == totalPage;
    }
}
