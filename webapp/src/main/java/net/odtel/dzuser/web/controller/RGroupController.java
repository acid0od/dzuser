package net.odtel.dzuser.web.controller;

import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.api.model.RGroup;
import net.odtel.dzuser.api.web.SearchRequestModel;
import net.odtel.dzuser.impl.service.NasService;
import net.odtel.dzuser.impl.service.RGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Collection;

@Controller
@SessionAttributes("searchRequest")
@Scope("session")
public class RGroupController {
    protected static final int DEFAULT_PAGE_NUM = 0;
    protected static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    protected NasService nasService;

    @Autowired
    protected RGroupService rGroupService;

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

    @RequestMapping(value = "/rgrouplist")
    public String rgroupList (@RequestParam(value = "page", required = false) Integer page, Model model) {

        int pageNum = page != null ? page : DEFAULT_PAGE_NUM;

        Page<RGroup> pageLocations = rGroupService.findAll(pageNum, DEFAULT_PAGE_SIZE);

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("page", pageLocations);
        return "/rgrouplist";
    }
}
