package net.odtel.dzuser.web.controller;

import net.odtel.dzuser.api.model.CurrentUser;
import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.api.web.SearchRequestModel;
import net.odtel.dzuser.impl.service.CurrentUserService;
import net.odtel.dzuser.impl.service.FailRejectService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Collection;

@Controller
@SessionAttributes("searchRequest")
@Scope("session")
public class CurrentUserController {

    protected static final int DEFAULT_PAGE_NUM = 0;
    protected static final int DEFAULT_PAGE_SIZE = 20;

    private static Logger LOGGER = LoggerFactory.getLogger(CurrentUserController.class);
    @Autowired
    protected CurrentUserService currentUserService;
    @Autowired
    protected NasService nasService;
    @Autowired
    protected FailRejectService failRejectService;

    private SearchRequestModel searchRequest = new SearchRequestModel();

    @ModelAttribute("searchRequest")
    public SearchRequestModel get () {
        return searchRequest;
    }

    @ModelAttribute("nas")
    public Collection<Nas> populateNas () {
        return nasService.findActive();
    }

    @ModelAttribute("nasreject")
    public Collection<Nas> populateNasReject () {
        return nasService.findReject();
    }

    @ModelAttribute("nasview")
    public Collection<Nas> populateNasView () {
        return nasService.findView();
    }

    @RequestMapping(value = "/currentusers", method = RequestMethod.GET)
    public String listGet (@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "sort", required = false) String sort,
                           @RequestParam(value = "order", required = false) boolean order,
                           @ModelAttribute("searchRequest") SearchRequestModel searchRequest,
                           Model model) {
        LOGGER.debug("Controller List model.[Start]");

        int pageNum = page != null ? page : DEFAULT_PAGE_NUM;

        if (sort == null || sort.length() < 1) {
            sort = "userName";
        }

        LOGGER.info("Controller List searchRequest.[{}]", searchRequest.toString());

        Page<CurrentUser> paging = currentUserService.findByUserNameOrNasWithOrder(searchRequest, pageNum,
                DEFAULT_PAGE_SIZE, sort, order);

        LOGGER.debug("Controller List paging. [{}]", paging.toString());
        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("order", order);
        model.addAttribute("page", paging);
        model.addAttribute("sort", sort);

        return "/currentusers";
    }

    @RequestMapping(value = "/currentusers", method = RequestMethod.POST)
    public String listPost (@ModelAttribute("searchRequest") SearchRequestModel searchRequest, final BindingResult result, final ModelMap modelMap) {

        LOGGER.debug("Controller List model.[Start] in POST");
        int pageNum = DEFAULT_PAGE_NUM;

        String sort = "userName";
        boolean order = false;

        LOGGER.info("Controller List searchRequest.[{}]", searchRequest.toString());

        Page<CurrentUser> paging = currentUserService.findByUserNameOrNasWithOrder(searchRequest, pageNum,
                DEFAULT_PAGE_SIZE, sort, order);

        LOGGER.debug("Controller List paging.[{}]", paging.toString());

        modelMap.addAttribute("searchRequest", searchRequest);
        modelMap.addAttribute("order", order);
        modelMap.addAttribute("page", paging);
        modelMap.addAttribute("sort", sort);

        return "/currentusers";
    }

    @RequestMapping(value = "/currentusers/delete/{userName}", method = RequestMethod.GET)
    public String viewUserStatistics (@PathVariable String userName,
                                      @RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "sort", required = false) String sort,
                                      @RequestParam(value = "order", required = false) boolean order,
                                      Model model) {
        int pageNum = page != null ? page : DEFAULT_PAGE_NUM;

        if (sort == null || sort.length() < 1) {
            sort = "userName";
        }

        currentUserService.remove(userName);

        LOGGER.info("currentUsers: remove {}", userName);

        Page<CurrentUser> paging = currentUserService.findByUserNameOrNasWithOrder(searchRequest, pageNum, DEFAULT_PAGE_SIZE, sort, order);

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("order", order);
        model.addAttribute("page", paging);
        model.addAttribute("sort", sort);

        return "redirect:/currentusers.html";
    }

}
