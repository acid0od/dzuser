package net.odtel.dzuser.web.util;

import net.odtel.dzuser.api.model.RGroupReply;
import net.odtel.dzuser.api.model.RGroupReplyPair;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RGroupReplyPairValidator implements Validator {

    @Override
    public boolean supports (Class<?> clazz) {
        return RGroupReplyPair.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate (Object target, Errors errors) {
        RGroupReplyPair p = (RGroupReplyPair) target;

        if (StringUtils.isBlank(p.getName())) {
            errors.rejectValue("name", "nullname.pair.list");
        }

        if (p.getList() == null) {
            errors.rejectValue("list", "nullvalue");
        } else if (p.getList().size() < 1) {
            errors.rejectValue("list", "nullvalue");
        } else {
            int k = 0;
            for (RGroupReply rGroupReply : p.getList()) {
                if (StringUtils.isBlank(rGroupReply.getGroupreplyval())) {
                    errors.rejectValue("list[" + k + "]", "emptyvalue");
                }
                k++;
            }
        }
    }
}
