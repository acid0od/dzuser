package net.odtel.dzuser.web.util;

import net.odtel.dzuser.api.model.NasType;
import net.odtel.dzuser.impl.service.NasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

public class NasTypeFormatter implements Formatter<NasType> {

    @Autowired
    private NasService nasService;

    @Override
    public String print (NasType nasType, Locale locale) {
        return nasType.getName();
    }

    @Override
    public NasType parse (String text, Locale locale) throws ParseException {
        Collection<NasType> nasTypes = nasService.findNasType();
        Integer idValue = Integer.valueOf(text);

        for (NasType nasType : nasTypes) {
            if (nasType.getId().equals(idValue)) {
                return nasType;
            }
        }
        throw new ParseException("type not found:" + text, 0);
    }

}
