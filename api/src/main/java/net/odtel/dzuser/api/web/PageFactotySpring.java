package net.odtel.dzuser.api.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageFactotySpring <T> {

    private List<T> content;
    private int pageNumber;
    private int limit;
    private int total;

    public PageFactotySpring (List<T> content, int limit) {
        this.limit = limit;
        this.content = content;

    }

    public Page<T> createPage () {
        Pageable pageable = new PageRequest(0,limit);
        Page<T> page = new PageImpl<T>(content, pageable, content.size());
        return page;
    }
}
