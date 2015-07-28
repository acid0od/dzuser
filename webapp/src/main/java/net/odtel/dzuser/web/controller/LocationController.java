package net.odtel.dzuser.web.controller;

import net.odtel.dzuser.api.model.Location;
import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.api.web.SearchRequestModel;
import net.odtel.dzuser.impl.service.LocationService;
import net.odtel.dzuser.impl.service.NasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@SessionAttributes("searchRequest")
@Scope("session")
public class LocationController {
    protected static final int DEFAULT_PAGE_NUM = 0;
    protected static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    protected NasService nasService;

    @Autowired
    private LocationService locationService;

    private SearchRequestModel searchRequest = new SearchRequestModel();

    @ModelAttribute("searchRequest")
    public SearchRequestModel get () {
        return searchRequest;
    }

    @ModelAttribute("nas")
    public Collection<Nas> populateNas () {
        return nasService.findActive();
    }

    @ModelAttribute("nasview")
    public Collection<Nas> populateNasView () {
        return nasService.findView();
    }

    @RequestMapping(value = "/locationlist")
    public String locationList (@RequestParam(value = "page", required = false) Integer page, Model model) {

        int pageNum = page != null ? page : DEFAULT_PAGE_NUM;

        Page<Location> pageLocations = locationService.findAll(pageNum, DEFAULT_PAGE_SIZE);

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("page", pageLocations);
        return "/locationlist";
    }

    @RequestMapping(value = "/locationedit/{idLocation}", method = RequestMethod.GET)
    public String updateLocation (@PathVariable Integer idLocation, Model model) {

        Location location = locationService.findById(idLocation);

        model.addAttribute("location", location);
        return "/locationedit";
    }

    @RequestMapping(value = "/locationedit/new", method = RequestMethod.GET)
    public String newLocation (@PathVariable Integer idLocation, Model model) {

        Location location = new Location();

        model.addAttribute("location", location);
        return "/locationedit";
    }

    @RequestMapping(value = "/locationdelete/{idLocation}", method = RequestMethod.GET)
    public String deleteLocation (@PathVariable Integer idLocation, Model model) {
        if (idLocation != null) {
            locationService.delete(idLocation);
        }
        return "redirect:/locationlist.html";
    }

    @RequestMapping(value = "/locationedit", method = RequestMethod.POST)
    public String updateLocationPost (@Valid @ModelAttribute("location") Location location, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "/locationedit";
        } else {
            locationService.save(location);
        }

        return "redirect:/locationlist.html";
    }

}
