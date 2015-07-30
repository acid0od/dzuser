package net.odtel.dzuser.web.util;

import net.odtel.dzuser.api.model.RadiusOperation;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class RadiusOperationFormatter implements Formatter<RadiusOperation> {

    @Override
    public RadiusOperation parse (String text, Locale locale) throws ParseException {
        return RadiusOperation.getKey(text);
    }

    @Override
    public String print (RadiusOperation radiusOperation, Locale locale) {
        return radiusOperation.getValue();
    }
}
