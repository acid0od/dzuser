package net.odtel.usercheck.controller;

import net.odtel.usercheck.domain.RadiusUser;
import net.odtel.usercheck.service.IRadiusUserService;
import net.odtel.usercheck.service.RadiusUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RadiusUserController {

    private List<RadiusUser> customerAnswerList = new ArrayList<>();

    @Autowired
    private IRadiusUserService radiusUserService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewRadiusUserList(final String radiusUser, Model model) {
        System.out.println(">>>>>>>>>>>>>>>>>>>:" + radiusUser);

        List<RadiusUser> radiusUsers = null;
        if (customerAnswerList.size() == 0) {
            radiusUsers = radiusUserService.findAllOfOrder(radiusUser);
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
        }

        model.addAttribute("radiusUsers", radiusUsers);
        return "radiususers";
    }

}
