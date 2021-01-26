package com.yonyou.cloud.common.response;

import java.io.Serializable;
import java.util.List;

public class Page<E> implements Serializable {
    private static final long serialVersionUID = -1130323946177600821L;

    /** 数据 */
    private List<E> rows;

    /** 偏移量 */
    private int offset;

    /** 每页数据量 */
    private int limit;

    /** 总条数 */
    private long total;

    /** 总页数 */
    private long totalPage;

    public Page() {

    }

    public Page(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotalPage() {
        if (total > 0) {
            return (total + limit - 1) / limit;
        }
        return this.totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }
}
