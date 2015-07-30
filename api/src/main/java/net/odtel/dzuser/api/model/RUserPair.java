package net.odtel.dzuser.api.model;

import java.io.Serializable;
import java.util.List;

public class RUserPair implements Serializable {
    private static final long serialVersionUID = 2621081839937128092L;

    private Long id;
    private String name;
    private String password;
    private RadiusAttribute passwordType;
    private RadiusOperation passwordOperation;
    private List<RUser> listUser;
    private List<String> listGroup;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
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

    public RadiusOperation getPasswordOperation () {
        return passwordOperation;
    }

    public void setPasswordOperation (RadiusOperation passwordOperation) {
        this.passwordOperation = passwordOperation;
    }

    public List<RUser> getListUser () {
        return listUser;
    }

    public void setListUser (List<RUser> listUser) {
        this.listUser = listUser;
    }

    public List<String> getListGroup () {
        return listGroup;
    }

    public void setListGroup (List<String> listGroup) {
        this.listGroup = listGroup;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RUserPair pair = (RUserPair) o;

        if (id != null ? !id.equals(pair.id) : pair.id != null) return false;
        if (name != null ? !name.equals(pair.name) : pair.name != null) return false;
        if (password != null ? !password.equals(pair.password) : pair.password != null) return false;
        if (passwordType != pair.passwordType) return false;
        if (passwordOperation != pair.passwordOperation) return false;
        if (listUser != null ? !listUser.equals(pair.listUser) : pair.listUser != null) return false;
        return !(listGroup != null ? !listGroup.equals(pair.listGroup) : pair.listGroup != null);

    }

    @Override
    public int hashCode () {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (passwordType != null ? passwordType.hashCode() : 0);
        result = 31 * result + (passwordOperation != null ? passwordOperation.hashCode() : 0);
        result = 31 * result + (listUser != null ? listUser.hashCode() : 0);
        result = 31 * result + (listGroup != null ? listGroup.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "RUserPair{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", passwordType=" + passwordType +
                ", passwordOperation=" + passwordOperation +
                ", listUser=" + listUser +
                ", listGroup=" + listGroup +
                '}';
    }
}
