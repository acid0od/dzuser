package net.odtel.usercheck.controller;

import net.odtel.usercheck.domain.RadiusUser;
import net.odtel.usercheck.service.RadiusUserService;
import net.odtel.usercheck.web.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RadiusUserController {

    private List<RadiusUser> customerAnswerList = new ArrayList<>();

    private Integer totalElementPerPage = 50;

    @Autowired
    private RadiusUserService radiusUserService;

    @RequestMapping(value = "/radiususers", method = RequestMethod.GET)
    public String viewRadiusUserList(@RequestParam(value = "page", required = false) final Integer pageNumber, final String radiusUser,  Model model) {
        System.out.println(">>>>>>>>>>>>>>>>>>>:" + radiusUser);


        Page<RadiusUser> radiusUsers = null;

        //if (customerAnswerList.size() == 0) {
        radiusUsers = radiusUserService.findAllOfOrder(radiusUser, pageNumber, totalElementPerPage);
        System.out.println(">>>>>>>>>>>>>>>>>>>:" + radiusUsers.getTotal());
/*

            for (Question question : knowledgeList.get(0).getQuestions()) {
                List<CheckedAnswer> checkedAnswerList = new ArrayList<>();
                for (Answer answer : question.getAnswer()) {
                    checkedAnswerList.add(new CheckedAnswer(answer.getId(), answer.getTitle(), false));
                }
                CustomerAnswer customerAnswer = new CustomerAnswer(question.getId(), question.getTitle(), false, checkedAnswerList);
                customerAnswerList.add(customerAnswer);
            }
*/
        //}

        model.addAttribute("page", radiusUsers);

        return "radiususers";
    }

}
