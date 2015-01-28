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
    private Integer totalPage;
    private boolean hasNext;
    private boolean hasPrev;
    private List<T> array;

    public Page (Integer limit){
        this.limit = limit;
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
        assume();
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

        if (totalPage == null || page == null) {
            return false;
        }

        if (totalPage.compareTo(page) > 0) {
            return true;
        }

        return false;
    }

    public boolean isHasPrev() {

        if (totalPage == null || page == null) {
            return false;
        }

        if (page.compareTo(1) > 0 ) {
            return true;
        }

        return false;
    }


    public void setHasNext(boolean hasNext) {

        this.hasNext = hasNext;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    private void assume () {

        int tPage = (int) Math.ceil((double) this.total / (double) this.limit);

        this.totalPage = new Integer(tPage);

        if (page == null || page.equals(0) || tPage == 0) {
            this.offset = 0;
            return;
        }

        int currentPage = page - 1;

        if (tPage > currentPage) {
            this.offset = currentPage * this.limit;
        } else  {
            this.offset = (tPage - 1) * this.limit;
        }

    }


}
