package net.odtel.dzuser.web.controller;

import net.odtel.dzuser.api.model.JAcct;
import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.api.web.SearchRequestModel;
import net.odtel.dzuser.impl.service.JAcctService;
import net.odtel.dzuser.impl.service.NasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Collection;

@Controller
@SessionAttributes("searchRequest")
@Scope("session")
public class JAcctController {
    protected static final int DEFAULT_PAGE_NUM = 0;
    protected static final int DEFAULT_PAGE_SIZE = 20;

    private static Logger LOGGER = LoggerFactory.getLogger(JAcctController.class);
    @Autowired
    protected JAcctService jAcctService;

    @Autowired
    protected NasService nasService;

    private SearchRequestModel searchRequest = new SearchRequestModel();

    @ModelAttribute("searchRequest")
    public SearchRequestModel get () {
        return searchRequest;

    }

    @ModelAttribute("nas")
    public Collection<Nas> populateNas () {
        LOGGER.info("Nas nas[]");
        return nasService.findActive();
    }

    @RequestMapping(value = "/jacctlist", method = RequestMethod.GET)
    public String viewTop20 (@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "sort", required = false) String sort,
                             @RequestParam(value = "order", required = false) boolean order,
                             @ModelAttribute("searchRequest") SearchRequestModel searchRequest,
                             Model model) {

        int pageNum = page != null ? page : DEFAULT_PAGE_NUM;

        if (sort == null || sort.length() < 1) {
            sort = "out";
        }

        Page<JAcct> paging = jAcctService.viewTopLastMonth(searchRequest, pageNum, DEFAULT_PAGE_SIZE);

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("order", order);
        model.addAttribute("page", paging);
        model.addAttribute("sort", sort);

        return "/jacctlist";
    }

    @RequestMapping(value = "/jacctlist", method = RequestMethod.POST)
    public String viewTop20Post (@ModelAttribute("searchRequest") SearchRequestModel searchRequest,
                                 final BindingResult result,
                                 ModelMap model) {

        int pageNum = DEFAULT_PAGE_NUM;

        String sort = "out";
        Boolean order = false;

        Page<JAcct> paging = jAcctService.viewTopLastMonth(searchRequest, pageNum, DEFAULT_PAGE_SIZE);

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("order", order);
        model.addAttribute("page", paging);
        model.addAttribute("sort", sort);

        return "/jacctlist";
    }

}
