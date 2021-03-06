package net.odtel.dzuser.api.model;

import java.util.List;

public class RadiusUser {

    private Long id;
    private String username;
    private String password;
    private RadiusAttribute passwordType;
    private RadiusOperation passwordOperation;
    private List<RadiusUserValue> radiusUserValues;
    private List<String> radiusUserGroup;

    public RadiusUser () {
        passwordOperation = RadiusOperation.ASSIGN;
    }

    public RadiusUser (String username, String password) {
        this.username = username;
        this.password = password;
        passwordOperation = RadiusOperation.ASSIGN;
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

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public RadiusAttribute getPasswordType () {
        return passwordType;
    }

    public void setPasswordType (RadiusAttribute passwordType) {
        this.passwordType = passwordType;
    }

    public List<String> getRadiusUserGroup () {
        return radiusUserGroup;
    }

    public void setRadiusUserGroup (List<String> radiusUserGroup) {
        this.radiusUserGroup = radiusUserGroup;
    }

    public List<RadiusUserValue> getRadiusUserValues () {
        return radiusUserValues;
    }

    public void setRadiusUserValues (List<RadiusUserValue> radiusUserValue) {
        this.radiusUserValues = radiusUserValue;
    }

    public RadiusOperation getPasswordOperation () {
        return passwordOperation;
    }

    @Override
    public String toString () {
        return this.getClass().getSimpleName() +
                "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordType=" + passwordType +
                ", passwordOperation=" + passwordOperation +
                ", radiusUserValues=" + radiusUserValues +
                ", radiusUserGroup=" + radiusUserGroup +
                '}';
    }
}
