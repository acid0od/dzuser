package net.odtel.usercheck.controller;

import net.odtel.usercheck.domain.RadiusUser;
import net.odtel.usercheck.service.RadiusUserService;
import net.odtel.usercheck.web.utils.Page;
import net.odtel.usercheck.web.utils.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("searchRequest")
@Scope("session")
public class RadiusUserController {

    private List<RadiusUser> customerAnswerList = new ArrayList<>();

    private Integer totalElementPerPage = 50;

    private SearchRequest searchRequest = new SearchRequest();

    @Autowired
    private RadiusUserService radiusUserService;

    @ModelAttribute("searchRequest")
    public SearchRequest getSearchRequest() {
        return searchRequest;
    }

    @RequestMapping(value = "/radiususers", method = RequestMethod.GET)
    public String viewRadiusUserList(@RequestParam(value = "page", required = false) Integer pageNumber, @ModelAttribute("searchRequest") SearchRequest searchRequest, Model model) {

        if (pageNumber == null) {
            pageNumber = 1;
        }

        Page<RadiusUser> radiusUsers = null;
        String radiusUser = searchRequest.getSearchString();

        if (radiusUser == null || radiusUser.trim().length() == 0) {
            radiusUsers = radiusUserService.findAllWithRange(pageNumber, totalElementPerPage);
        } else {
            radiusUsers = radiusUserService.findAllOfOrderWithRange(radiusUser, pageNumber, totalElementPerPage);
        }

        model.addAttribute("page", radiusUsers);
        model.addAttribute("searchRequest", searchRequest);

        return "radiususers";
    }

    @RequestMapping(value = "/radiususers", method = RequestMethod.POST)
    public String viewRadiusUserListByName(@ModelAttribute("searchRequest") SearchRequest searchRequest, final BindingResult result, final ModelMap modelMap) {
        Integer pageNumber = 1;

        Page<RadiusUser> radiusUsers = null;

        if (searchRequest == null || searchRequest.getSearchString() == null || searchRequest.getSearchString().trim().length() == 0) {
            radiusUsers = radiusUserService.findAllWithRange(pageNumber, totalElementPerPage);
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>:" + searchRequest);
            radiusUsers = radiusUserService.findAllOfOrderWithRange(searchRequest.getSearchString(), pageNumber, totalElementPerPage);
        }

        modelMap.addAttribute("page", radiusUsers);
        modelMap.addAttribute("searchRequest", searchRequest);

        return "radiususers";
    }

}
