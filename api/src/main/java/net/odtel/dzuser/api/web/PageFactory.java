package net.odtel.dzuser.api.web;


public class PageFactory<T> {

    private int pageNumber;
    private int limit;
    private int total;

    public PageFactory (int pageNumber, int limit, long total) {
        this.pageNumber = pageNumber;
        this.limit = limit;
        this.total = ((Number) total).intValue();

    }

    public Page<T> createPage () {
        Page<T> page = new PageImpl<T>(pageNumber, limit, total);
        return page;
    }
}
