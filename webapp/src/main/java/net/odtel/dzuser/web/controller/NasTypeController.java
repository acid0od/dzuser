package net.odtel.dzuser.web.controller;

import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.api.model.NasType;
import net.odtel.dzuser.api.web.SearchRequestModel;
import net.odtel.dzuser.impl.service.CurrentUserService;
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
public class NasTypeController {
    protected static final int DEFAULT_PAGE_NUM = 0;
    protected static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    protected CurrentUserService currentUserService;
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

    @RequestMapping(value = "/nastypelist")
    public String nasTypeList (@RequestParam(value = "page", required = false) Integer page, Model model) {

        Page<NasType> nasType = nasTypeService.findAll(DEFAULT_PAGE_NUM, DEFAULT_PAGE_SIZE);

        int pageNum = page != null ? page : DEFAULT_PAGE_NUM;

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("page", nasType);
        return "/nastypelist";
    }

    @RequestMapping(value = "/nastypeedit/{idNas}", method = RequestMethod.GET)
    public String updateNasType (@PathVariable Integer idNas, Model model) {

        NasType nasType = nasTypeService.findById(idNas);

        model.addAttribute("nasType", nasType);
        return "/nastypeedit";
    }

    @RequestMapping(value = "/nastypeedit/new", method = RequestMethod.GET)
    public String addNasType (Model model) {

        NasType nasType = new NasType();

        model.addAttribute("nasType", nasType);

        System.out.println("NasType - :" + nasType.toString());
        return "/nastypeedit";
    }

    @RequestMapping(value = "/nastypedelete/{idNas}", method = RequestMethod.GET)
    public String deleteNasType (@PathVariable Integer idNas, Model model) {
        if (idNas != null) {
            nasTypeService.remove(idNas);
        }
        return "redirect:/nastypelist.html";
    }

/*
    @RequestMapping(value = "/nastypeedit/{idNas}", method = RequestMethod.POST)
    public String updateTypeNas (@Valid @ModelAttribute("nasType") NasType nasType, BindingResult result) {

        if (result.hasErrors()) {
            return "/nastypeedit";
        } else {
            nasTypeService.save(nasType);
            System.out.println("Nas + :" + nasType.toString());
        }

        return "redirect:/nastypelist.html";
    }
*/

    @RequestMapping(value = "/nastypeedit", method = RequestMethod.POST)
    public String updateTypeNasId (@Valid @ModelAttribute("nasType") NasType nasType, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "/nastypeedit";
        } else {
            nasTypeService.save(nasType);
        }

        return "redirect:/nastypelist.html";
    }
}
