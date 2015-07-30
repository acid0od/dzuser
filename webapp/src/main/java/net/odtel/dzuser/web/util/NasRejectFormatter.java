package net.odtel.dzuser.web.util;

import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.impl.service.NasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

public class NasRejectFormatter implements Formatter<Nas> {

    @Autowired
    private NasService nasService;

    @Override
    public String print (Nas nas, Locale locale) {
        return nas.getIp();
    }

    @Override
    public Nas parse (String text, Locale locale) throws ParseException {
        Collection<Nas> nasList = nasService.findAll();
        Integer idValue = Integer.valueOf(text);
        for (Nas nas : nasList) {
            if (nas.getId().equals(idValue))
                return nas;
        }
        throw new ParseException("type not found:" + text, 0);
    }

}
