package net.odtel.dzuser.api.model;

public class RadiusUserGroup {
    private Long id;
    private String username;
    private String groupName;

    public RadiusUserGroup () {
    }

    public RadiusUserGroup (String username, String groupName) {
        this.username = username;
        this.groupName = groupName;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getGroupName () {
        return groupName;
    }

    public void setGroupName (String groupName) {
        this.groupName = groupName;
    }

}
