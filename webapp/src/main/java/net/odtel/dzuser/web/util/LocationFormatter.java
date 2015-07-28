package net.odtel.dzuser.web.util;

import net.odtel.dzuser.api.model.Location;
import net.odtel.dzuser.impl.service.NasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

public class LocationFormatter implements Formatter<Location> {

    @Autowired
    private NasService nasService;

    @Override
    public String print (Location location, Locale locale) {
        return location.getName();
    }

    @Override
    public Location parse (String text, Locale locale) throws ParseException {
        Collection<Location> locations = nasService.findLocation();
        Integer idValue = Integer.valueOf(text);
        for (Location location : locations) {
            if (location.getId().equals(idValue)) {
                return location;
            }

        }
        throw new ParseException("type not found:" + text, 0);
    }

}
