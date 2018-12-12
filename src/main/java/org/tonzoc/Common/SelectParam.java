package org.tonzoc.Common;

import java.util.List;

public class SelectParam {
    private String order;
    private String sort;
    private int pageSize;
    private int pageIndex;
    private List whereParams;

    public SelectParam(String order, String sort, int pageSize, int pageIndex, List whereParams) {
        this.order = order;
        this.sort = sort;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.whereParams = whereParams;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public List getWhereParams() {
        return whereParams;
    }

    public int getOffset() {
        return (pageIndex -1)  * pageSize;
//        return pageIndex   * pageSize;
    }

    public String getOrder() {
        return order;
    }

    public String getSort() {
        return sort;
    }
}
