package org.tonzoc.Common;

import java.util.List;

public class PageList {
    private final long total;
    private final List list;

    public PageList(long total, List list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public List getList() {
        return list;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
