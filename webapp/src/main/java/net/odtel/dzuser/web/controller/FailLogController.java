package net.odtel.dzuser.web.controller;

import net.odtel.dzuser.api.model.FailReject;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Collection;

@Controller
@SessionAttributes("searchRequest")
@Scope("session")
public class FailLogController {
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
        LOGGER.info("Nas nas[]");
        return nasService.findActive();
    }

    @ModelAttribute("nasreject")
    public Collection<Nas> populateNasReject () {
        return nasService.findReject();
    }

    @ModelAttribute("nasview")
    public Collection<Nas> populateNasView () {
        LOGGER.info("Nas view []");
        return nasService.findView();
    }

    @RequestMapping(value = "/faillog", method = RequestMethod.POST)
    public String listFailLogPost (@RequestParam(value = "page", required = false) Integer page, @ModelAttribute("searchRequest") SearchRequestModel searchRequest, Model model) {

        int pageNum = page != null ? page : DEFAULT_PAGE_NUM;

        Page<FailReject> paging = failRejectService.viewStatistics(searchRequest, pageNum, DEFAULT_PAGE_SIZE);

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("page", paging);
        return "/faillog";
    }

    @RequestMapping(value = "/faillog", method = RequestMethod.GET)
    public String listFailLogGet (@RequestParam(value = "page", required = false) Integer page, @ModelAttribute("searchRequest") SearchRequestModel searchRequest, Model model) {

        int pageNum = page != null ? page : DEFAULT_PAGE_NUM;

        Page<FailReject> paging = failRejectService.viewStatistics(searchRequest, pageNum, DEFAULT_PAGE_SIZE);

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("page", paging);
        return "/faillog";
    }

}
