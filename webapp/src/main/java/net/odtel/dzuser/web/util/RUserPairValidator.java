package net.odtel.dzuser.web.util;

import net.odtel.dzuser.api.model.RUser;
import net.odtel.dzuser.api.model.RUserPair;
import net.odtel.dzuser.api.model.RadiusAttribute;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RUserPairValidator implements Validator {

    @Override
    public boolean supports (Class<?> clazz) {
        return RUserPair.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate (Object target, Errors errors) {
        RUserPair p = (RUserPair) target;

        if (StringUtils.isBlank(p.getName())) {
            //TODO: - have to create checking for duble user name
            errors.rejectValue("name", "nullname.user.pair.list");
        }

        if (StringUtils.isBlank(p.getPassword())) {
            errors.rejectValue("password","nullpassword.user.pair.list");
        }


        if (p.getListUser() != null && p.getListUser().size() > 0) {
            int k = 0;
            for (RUser user : p.getListUser()) {
                if (StringUtils.isBlank(user.getUserval())) {
                    errors.rejectValue("listUser[" + k + "]", "emptyvalue.user");
                }

                if (user.getUserattr() == RadiusAttribute.CLEARTEXT_PASSWORD) {
                    errors.rejectValue("listUser[" + k + "]", "doubleattr.user");
                }
                k++;
            }
        }
    }
}
