package net.odtel.dzuser.api.web;

import java.util.List;

public interface Page<T> {
    void setListData (List<T> listData);

    List<T> getListData ();

    int getLimit ();

    int getOffset ();

    int getTotal ();

    int getCurrentPage ();

    int getTotalPage ();

    boolean hasNext ();

    boolean hasPrev ();
}
