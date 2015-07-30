package net.odtel.dzuser.api.web;

import java.util.List;

public class PageImpl<T> implements Page<T> {

    private List<T> listData;
    private int currentPage;
    private int totalPage;
    private int limit;
    private int offset;
    private int total;

     public PageImpl (int currentPage, int limit, int total) {
        this.currentPage = currentPage;
        this.limit = limit;
        this.total = total;
        assume();
    }

    @Override
    public List<T> getListData () {
        return listData;
    }

    @Override
    public void setListData (List<T> listData) {
        this.listData = listData;
    }

    @Override
    public int getLimit () {
        return this.limit;
    }

    @Override
    public int getOffset () {
        return this.offset;
    }

    @Override
    public int getTotal () {
        return this.total;
    }

    @Override
    public int getCurrentPage () {
        return this.currentPage;
    }

    @Override
    public int getTotalPage () {
        return this.totalPage;
    }

    @Override
    public boolean hasNext () {
        if (totalPage > currentPage) {
            return true;
        }

        return false;
    }

    @Override
    public boolean hasPrev () {
        if (currentPage > 0) {
            return true;
        }
        return false;
    }

    private void assume () {

        int tPage = (int) Math.ceil((double) this.total / (double) this.limit);

        this.totalPage = new Integer(tPage);

        if (currentPage == 0 || tPage == 0) {
            this.offset = 0;
            return;
        }

        int cPage = currentPage - 1;

        if (tPage > cPage) {
            this.offset = cPage * this.limit;
        } else  {
            this.offset = (tPage - 1) * this.limit;
        }

    }

//    public boolean isValidDate (String inputValue) {
//        Calendar cal = new GregorianCalendar();
//        cal.setLenient (true);
//        cal.clear ();
//        try {
//            int hourOfDay = Integer.parseInt (inputValue.substring (0, 2));
//            int minute = Integer.parseInt (inputValue.substring (3, 5));
//            int second = Integer.parseInt (inputValue.substring (6, 8));
//            cal.set(cal.HOUR_OF_DAY, hourOfDay);
//            cal.set(cal.MINUTE, minute);
//            cal.set(cal.SECOND, second);
//            java.util.Date dt = cal.getTime ();
//            return true;
//        }
//        catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {return false;}
//
//    }
//
//    public static void main (String[] args) {
//        PageImpl s = new PageImpl();
//        System.out.printf(":" + s.isValidDate("30:23:22"));
//    }

    @Override
    public String toString () {
        return "PageImpl{" +
                "listData=" + listData +
                ", currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", limit=" + limit +
                ", offset=" + offset +
                ", total=" + total +
                '}';
    }


}
