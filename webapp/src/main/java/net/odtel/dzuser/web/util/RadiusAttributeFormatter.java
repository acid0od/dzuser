package net.odtel.dzuser.web.util;

import net.odtel.dzuser.api.model.RadiusAttribute;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RadiusAttributeFormatter implements Formatter<RadiusAttribute> {

    @Override
    public String print (RadiusAttribute radiusAttribute, Locale locale) {
        return radiusAttribute.getValue();
    }

    @Override
    public RadiusAttribute parse (String text, Locale locale) throws ParseException {
        return RadiusAttribute.getKey(text);
    }
}
