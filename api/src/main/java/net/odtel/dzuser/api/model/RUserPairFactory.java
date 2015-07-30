package net.odtel.dzuser.api.model;

import java.util.ArrayList;
import java.util.List;

public class RUserPairFactory {
    private List<RGroupReply> groupReplyList;
    private List<RUser> userList;
    private List<RUser> userReplyList;

    public RUserPairFactory (List<RUser> userList, List<RUser> userReplyListList, List<RGroupReply> groupReplyList) {
        this.groupReplyList = groupReplyList;
        this.userReplyList = userReplyListList;
        this.userList = userList;

    }

    public RUserPair create () {
        RUserPair pair = new RUserPair();
        if (userReplyList != null && userReplyList.size() > 0) {
            pair.setListUser(userReplyList);
        } else {
            pair.setListUser(new ArrayList<RUser>());
        }

        for (RUser user : userList) {
            if (user.getUserattr().equals(RadiusAttribute.CLEARTEXT_PASSWORD)){
                pair.setId(user.getId());
                pair.setName(user.getUsername());
                pair.setPassword(user.getUserval());
                pair.setPasswordType(RadiusAttribute.CLEARTEXT_PASSWORD);
            } else {
                pair.getListUser().add(user);
            }
        }

        if (groupReplyList != null && groupReplyList.size() > 0) {
            pair.setListGroup(new ArrayList<String>());
            for (RGroupReply reply : groupReplyList) {
                pair.getListGroup().add(reply.getGroupreplyname());
            }
        }

        return pair;
    }
}
