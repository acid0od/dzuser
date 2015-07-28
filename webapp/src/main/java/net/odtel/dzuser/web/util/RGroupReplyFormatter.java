package net.odtel.dzuser.web.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RGroupReplyFormatter implements Formatter<String> {

    @Override
    public String parse (String text, Locale locale) throws ParseException {
        return text;
    }

    @Override
    public String print (String object, Locale locale) {
        return (object != null ? object : "");
    }
}
