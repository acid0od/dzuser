package net.odtel.dzuser.web.util;

import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.impl.service.NasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

public class NasFormatter implements Formatter<Nas> {
    static Logger LOGGER = LoggerFactory.getLogger(NasFormatter.class);

    @Autowired
    NasService nasService;

    @Override
    public String print (Nas nas, Locale locale) {
        return nas.getDescription();
    }

    @Override
    public Nas parse (String text, Locale locale) throws ParseException {
        Collection<Nas> nasList = nasService.findAll();
        Integer idValue = Integer.valueOf(text);

        LOGGER.debug("NasFormatter: text[ {} ]", text);
        for (Nas nas : nasList) {

            if (nas.getId().equals(idValue)) {
                LOGGER.debug("NasFormatter: found[ {} ]", nas.toString());
                return nas;
            }
        }

        LOGGER.debug("NasFormatter: not found!");
        throw new ParseException("type not found:" + text, 0);
    }

}
