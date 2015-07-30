package net.odtel.dzuser.web.controller;

import net.odtel.dzuser.api.model.RadiusAttribute;
import net.odtel.dzuser.api.model.RadiusGroup;
import net.odtel.dzuser.api.model.RadiusOperation;
import net.odtel.dzuser.api.model.RadiusUser;
import net.odtel.dzuser.api.model.RadiusUserValue;
import net.odtel.dzuser.api.web.Page;
import net.odtel.dzuser.impl.service.RadiusGroupService;
import net.odtel.dzuser.impl.service.RadiusUserService;
import net.odtel.dzuser.web.util.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@SessionAttributes("searchRequest")
@Scope("session")
public class RadiusUserController {

    private Integer totalElementPerPage = 50;

    private SearchRequest searchRequest = new SearchRequest();

    @Autowired
    private RadiusUserService radiusUserService;

    @Autowired
    private RadiusGroupService radiusGroupService;

    @ModelAttribute("radiusGroupList")
    public Collection<RadiusGroup> populateRadiusGroup () {
        return radiusGroupService.findAllDistinctByGroupreplyname();
    }

    @ModelAttribute("radiusAttribute")
    public Collection<RadiusAttribute> populateRadiusAttribute () {
        List<RadiusAttribute> stringList = new ArrayList<>();
        for (RadiusAttribute radiusAttribute : RadiusAttribute.values()) {
            stringList.add(radiusAttribute);
        }
        return stringList;
    }

    @ModelAttribute("radiusOperation")
    public Collection<RadiusOperation> populateRadiusOperation () {
        List<RadiusOperation> stringList = new ArrayList<>();
        for (RadiusOperation radiusOperation : RadiusOperation.values()) {
            stringList.add(radiusOperation);
        }
        return stringList;
    }

    @ModelAttribute("searchRequest")
    public SearchRequest getSearchRequest () {
        return searchRequest;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String viewRadiusUserList (@RequestParam(value = "pageNumber", required = false) Integer pageNumber, @ModelAttribute("searchRequest") SearchRequest searchRequest, Model model) {

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

        searchRequest.setPageNumber(pageNumber);
        model.addAttribute("page", "radiususers");
        model.addAttribute("users", radiusUsers);
        model.addAttribute("searchRequest", searchRequest);

        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String viewRadiusUserListByName (@ModelAttribute("searchRequest") SearchRequest searchRequest, final BindingResult result, final ModelMap modelMap) {
        Integer pageNumber = 1;

        Page<RadiusUser> radiusUsers = null;

        if (searchRequest == null || searchRequest.getSearchString() == null || searchRequest.getSearchString().trim().length() == 0) {
            radiusUsers = radiusUserService.findAllWithRange(pageNumber, totalElementPerPage);
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>:" + searchRequest);
            radiusUsers = radiusUserService.findAllOfOrderWithRange(searchRequest.getSearchString(), pageNumber, totalElementPerPage);
        }

        modelMap.addAttribute("page", "radiususers");
        modelMap.addAttribute("users", radiusUsers);
        modelMap.addAttribute("searchRequest", searchRequest);

        return "users";
    }

    @RequestMapping(value = "/users", params = {"editRadiusUser"})
    public String editRadiusUser (Integer pageNumber, @ModelAttribute("searchRequest") SearchRequest searchRequest, Model model, final HttpServletRequest req) {

        final Long id = Long.valueOf(req.getParameter("editRadiusUser"));

        RadiusUser radiusUser = radiusUserService.findOne(id);
        //TODO remove this to the Service class
        List<String> groups = radiusGroupService.getGroupForUser(radiusUser.getUsername());
        radiusUser.setRadiusUserGroup(groups);
        List<RadiusUserValue> radiusUserValues = radiusUserService.findUserValue(radiusUser.getUsername());
        if (radiusUserValues == null) {
            radiusUserValues = new AutoPopulatingList<RadiusUserValue>(RadiusUserValue.class);
        }
        radiusUser.setRadiusUserValues(radiusUserValues);

        //List<RadiusUserValue> radiusUserValues = new AutoPopulatingList<>(RadiusUserValue.class);
        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("radiusUser", radiusUser);
        model.addAttribute("page", "editradiususer");

        return "edituser";

    }

    @RequestMapping(value = "/updateradiususer")
    public String updateRadiusUser (@ModelAttribute("searchRequest") SearchRequest searchRequest, @ModelAttribute("radiusUser") RadiusUser radiusUser, Model model, final HttpServletRequest req) {

        System.out.println("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{" + radiusUser.toString());
        System.out.println("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{" + searchRequest.toString());

        Page<RadiusUser> radiusUsers = null;
        String radiusUserSearchString = searchRequest.getSearchString();

        radiusGroupService.updateGroupForUser(radiusUser);
        radiusUserService.update(radiusUser);

        if (radiusUserSearchString == null || radiusUserSearchString.trim().length() == 0) {
            radiusUsers = radiusUserService.findAllWithRange(searchRequest.getPageNumber(), totalElementPerPage);
        } else {
            radiusUsers = radiusUserService.findAllOfOrderWithRange(radiusUserSearchString, searchRequest.getPageNumber(), totalElementPerPage);
        }

        model.addAttribute("searchRequest", searchRequest);
        model.addAttribute("users", radiusUsers);
        model.addAttribute("page", "radiususers");

        return "radiususers";

    }

    @RequestMapping(value = "/updateradiususer", params = "addValues")
    public String updateRadiusUserAddValues (@ModelAttribute("radiusUser") RadiusUser radiusUser, final BindingResult result) {
        if (radiusUser.getRadiusUserValues() == null) {
            List<RadiusUserValue> radiusUserValues = new ArrayList<>();
            radiusUser.setRadiusUserValues(radiusUserValues);
        }

        radiusUser.getRadiusUserValues().add(new RadiusUserValue());
        return "editradiususer";
    }

    @RequestMapping(value = "/updateradiususer", params = "removeValue")
    public String updateRadiusUserRemoveValues (@ModelAttribute("radiusUser") RadiusUser radiusUser, final BindingResult result, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeValue"));
        radiusUser.getRadiusUserValues().remove(rowId.intValue());
        return "editradiususer";
    }

    // Groups

    @RequestMapping(value = "/radiusgroups", method = RequestMethod.GET)
    public String viewRadiusGroupList (@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                       @ModelAttribute("searchRequest") SearchRequest searchRequest,
                                       Model model) {

        if (pageNumber == null) {
            pageNumber = 1;
        }

        Page<RadiusGroup> radiusGroupPage = null;
        String radiusGroup = searchRequest.getSearchString();

        if (radiusGroup == null || radiusGroup.trim().length() == 0) {
            radiusGroupPage = radiusGroupService.findAll(pageNumber, totalElementPerPage);
        } else {
            radiusGroupPage = radiusGroupService.findAllByGroupName(radiusGroup, pageNumber, totalElementPerPage);
        }

        searchRequest.setPageNumber(pageNumber);
        model.addAttribute("page", "radiusgroups");
        model.addAttribute("groups", radiusGroupPage);
        model.addAttribute("searchRequest", searchRequest);

        return "radiusgroups";
    }

    @RequestMapping(value = "/radiusgroups", method = RequestMethod.POST)
    public String viewRadiusGroupListByName (@ModelAttribute("searchRequest") SearchRequest searchRequest, final BindingResult result, final ModelMap modelMap) {
        Integer pageNumber = 1;

        Page<RadiusGroup> radiusGroupPage = null;

        if (searchRequest == null || searchRequest.getSearchString() == null || searchRequest.getSearchString().trim().length() == 0) {
            radiusGroupPage = radiusGroupService.findAll(pageNumber, totalElementPerPage);
        } else {
            radiusGroupPage = radiusGroupService.findAllByGroupName(searchRequest.getSearchString(), pageNumber, totalElementPerPage);
        }

        modelMap.addAttribute("page", "radiusgroups");
        modelMap.addAttribute("groups", radiusGroupPage);
        modelMap.addAttribute("searchRequest", searchRequest);

        return "radiususers";
    }

}
