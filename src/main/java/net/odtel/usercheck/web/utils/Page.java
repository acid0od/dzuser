package net.odtel.usercheck.web.utils;

import net.odtel.usercheck.config.MainConfig;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    private static final long serialVersionUID = -3925053519585664158L;

    private Integer limit;
    private Integer offset;
    private Long total;
    private Integer page;
    private boolean hasNext;
    private List<T> array;

    public Page (Integer limit){
        this.limit = limit;
        this.offset = offset;
    }

    public List<T> getArray() {
        return array;
    }

    public void setArray(List<T> array) {
        this.array = array;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        long tPage = (long) Math.ceil((double) this.total / (double) this.limit);
        if (page == null || page.equals(0)) {
            offset = 0;
            return offset;
        }
        
        if (tPage > page.longValue()) {
                offset = (total).intValue() - (this.page * this.limit);
        }
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}
