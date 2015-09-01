package net.odtel.dzuser.api.web;

import net.odtel.dzuser.api.model.Nas;
import net.odtel.dzuser.api.model.NasType;

import java.io.Serializable;

public class SearchRequestModel implements Serializable {

    private NasType type;
    private String userName;
    private String userIP;
    private Nas nasid;
    private String groupName;

    public NasType getType () {
        return type;
    }

    public void setType (NasType type) {
        this.type = type;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public String getUserIP () {
        return userIP;
    }

    public void setUserIP (String userIP) {
        this.userIP = userIP;
    }

    public Nas getNasid () {
        return nasid;
    }

    public void setNasid (Nas nasid) {
        this.nasid = nasid;
    }

    public String getGroupName () {
        return groupName;
    }

    public void setGroupName (String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchRequestModel that = (SearchRequestModel) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userIP != null ? !userIP.equals(that.userIP) : that.userIP != null) return false;
        if (nasid != null ? !nasid.equals(that.nasid) : that.nasid != null) return false;
        return !(groupName != null ? !groupName.equals(that.groupName) : that.groupName != null);

    }

    @Override
    public int hashCode () {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userIP != null ? userIP.hashCode() : 0);
        result = 31 * result + (nasid != null ? nasid.hashCode() : 0);
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "SearchRequestModel{" +
                "type=" + type +
                ", userName='" + userName + '\'' +
                ", userIP='" + userIP + '\'' +
                ", nasid=" + nasid +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
