package net.odtel.usercheck.web.utils;

import net.odtel.usercheck.domain.RadiusGroup;
import net.odtel.usercheck.service.IRadiusGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RadiusGroupFormatter implements Formatter<RadiusGroup> {

    @Autowired
    private IRadiusGroupService service;

    @Override
    public RadiusGroup parse(String text, Locale locale) throws ParseException {
        final Long id = Long.valueOf(text);
        return service.findOne(id);
    }

    @Override
    public String print(final RadiusGroup radiusGroup, Locale locale) {
        return (radiusGroup != null ? radiusGroup.getId().toString() : "");
    }
}
