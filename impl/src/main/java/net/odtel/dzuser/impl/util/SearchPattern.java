package net.odtel.dzuser.impl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchPattern {

    public static final int NO_NAME = 0;
    public static final int PARTIAL_NAME = 1;
    public static final int FULL_NAME = 2;

    private String searchPattern;
    private int par;

    public SearchPattern () {
        par = NO_NAME;
    }

    public SearchPattern (String str) {
        this.par = NO_NAME;
        Pattern end = Pattern.compile("(.*)\\*$");
        Pattern start = Pattern.compile("^\\*(.*)");

        if (str != null) {

            this.setPar(SearchPattern.FULL_NAME);

            Matcher m = end.matcher(str.trim());

            if (m.matches()) {
                this.setPar(SearchPattern.PARTIAL_NAME);
                str = m.group(1).concat("%");
            }

            Matcher l = start.matcher(str);

            if (l.matches()) {
                this.setPar(SearchPattern.PARTIAL_NAME);
                str = "".concat("%") + l.group(1);
            }

            this.setSearchPattern(str);
        }

    }

    public String getSearchPattern () {
        return searchPattern;
    }

    public void setSearchPattern (String searchPattern) {
        this.searchPattern = searchPattern;
    }

    public int getPar () {
        return par;
    }

    public void setPar (int par) {
        this.par = par;
    }
}
