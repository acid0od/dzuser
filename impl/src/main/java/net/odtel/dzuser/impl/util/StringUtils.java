package net.odtel.dzuser.impl.util;

public class StringUtils {

    public static String setQuates (String beginString) {
        char[] val = beginString.toCharArray();
        int st = 0;
        int len = val.length;

        while ((st < len) && ((val[st] <= ' ') || (val[st] <= '"'))) {
            st++;
        }

        while ((st < len) && ((val[len - 1] <= ' ') || (val[len - 1] <= '"'))) {
            len--;
        }

        int length = len - st + 2;
        char[] a = new char[length];
        a[0] = a[length - 1] = '"';

        for (int i = 1; i < length - 1; i++) {
            a[i] = val[st + i - 1];
        }

        return new String(a);
    }

}

