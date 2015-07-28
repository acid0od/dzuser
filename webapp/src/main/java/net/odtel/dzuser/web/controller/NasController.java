package net.odtel.dzuser.web.controller;

import net.odtel.dzuser.api.model.Location;
import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.api.model.NasType;
import net.odtel.dzuser.api.web.SearchRequestModel;
import net.odtel.dzuser.impl.service.NasService;
import net.odtel.dzuser.impl.service.NasTypeService;
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
public class NasController {
    protected static final int DEFAULT_PAGE_NUM = 0;
    protected static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    protected NasService nasService;

    @Autowired
    private NasTypeService nasTypeService;

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

    @ModelAttribute("nastypes")
    public Collection<NasType> populateNasTypes () {
        return nasService.findNasType();
    }

    @ModelAttribute("locationList")
    public Collection<Location> populateLocation () {
        return nasService.findLocation();
    }

    @RequestMapping(value = "/naslist")
    public String nasList (@RequestParam(value = "page", required = false) Integer page, Model model) {
        int pageNum = page != null ? page : DEFAULT_PAGE_NUM;
        Page<Nas> nas = nasService.findAll(pageNum, DEFAULT_PAGE_SIZE);

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("page", nas);
        return "/naslist";
    }

    @RequestMapping(value = "/nasedit/{idNas}", method = RequestMethod.GET)
    public String updateNas (@PathVariable Integer idNas, Model model) {

        Nas nas = null;
        if (idNas > 0) {
            nas = nasService.findNasById(idNas);
        }

        model.addAttribute("nasEdit", nas);
        return "/nasedit";

    }

    @RequestMapping(value = "/nasedit/new", method = RequestMethod.GET)
    public String addNas (Model model) {

        Nas nas = new Nas();

        model.addAttribute("nasEdit", nas);

        return "/nasedit";
    }

    @RequestMapping(value = "/nasdelete/{idNas}", method = RequestMethod.GET)
    public String deleteNas (@PathVariable Integer idNas, Model model) {
        if (idNas != null) {
            nasService.remove(idNas);
        }
        return "redirect:/naslist.html";
    }

    @RequestMapping(value = "/nasedit", method = RequestMethod.POST)
    public String updateNasPost (@Valid @ModelAttribute("nasEdit") Nas nas, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "/nasedit";
        } else {
            nasService.save(nas);
        }

        return "redirect:/naslist.html";
    }
}
