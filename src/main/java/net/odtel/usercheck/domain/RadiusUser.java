package net.odtel.usercheck.domain;

import javax.persistence.Entity;
import java.util.List;
import java.util.Set;

@Entity
public class RadiusUser {

    private Long id;
    private String username;
    private String password;
    private RadiusAttribute passwordType;
    private RadiusOperation passwordOperation;
    private Set<RadiusUserValue> radiusUserValue;
    private List<String> radiusUserGroup;

    public RadiusUser() {
        passwordOperation = RadiusOperation.ASSIGN;
    }

    public RadiusUser(String username, String password) {
        this.username = username;
        this.password = password;
        passwordOperation = RadiusOperation.ASSIGN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RadiusAttribute getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(RadiusAttribute passwordType) {
        this.passwordType = passwordType;
    }

    public List<String> getRadiusUserGroup() {
        return radiusUserGroup;
    }

    public void setRadiusUserGroup(List<String> radiusUserGroup) {
        this.radiusUserGroup = radiusUserGroup;
    }

    public Set<RadiusUserValue> getUserValue() {
        return radiusUserValue;
    }

    public void setRadiusUserValue(Set<RadiusUserValue> radiusUserValue) {
        this.radiusUserValue = radiusUserValue;
    }

    public RadiusOperation getPasswordOperation() {
        return passwordOperation;
    }

}
