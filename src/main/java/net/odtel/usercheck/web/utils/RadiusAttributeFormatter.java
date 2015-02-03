package net.odtel.usercheck.web.utils;

import net.odtel.usercheck.domain.RadiusAttribute;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RadiusAttributeFormatter implements Formatter<RadiusAttribute> {

    @Override
    public RadiusAttribute parse(String text, Locale locale) throws ParseException {
        return RadiusAttribute.getKey(text);
    }

    @Override
    public String print(RadiusAttribute radiusAttribute, Locale locale) {
        return radiusAttribute.getValue();
    }
}
