package net.odtel.usercheck.web.utils;

import net.odtel.usercheck.service.RadiusGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RadiusGroupFormatter implements Formatter<String> {

    @Autowired
    private RadiusGroupService service;

    @Override
    public String parse(String text, Locale locale) throws ParseException {
        return text;
    }

    @Override
    public String print(String radiusGroup, Locale locale) {
        return (radiusGroup != null ? radiusGroup : "");
    }
}
