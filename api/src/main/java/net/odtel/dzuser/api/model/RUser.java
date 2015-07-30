package net.odtel.dzuser.api.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "ruser")
public class RUser implements Serializable {

    private static final long serialVersionUID = -5175871531364877855L;

    @Id
    @GenericGenerator(name = "ruser_userid_seq", strategy = "sequence", parameters = {@Parameter(name = "sequence", value = "ruser_userid_seq")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ruser_userid_seq")
    @Column(name = "userid", nullable = false)
    private Long id;

    @Column(name = "username", columnDefinition = "character varying(30) DEFAULT ''::character varying", nullable = false)
    @NotNull
    @Size(min = 3, max = 20, message = "The user name must be at least 3 characters long.")
    private String username;

    @Column(name = "userattr", columnDefinition = "character varying(64) DEFAULT ''::character varying", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RadiusAttribute userattr;

    @Column(name = "userop", columnDefinition = "character(2) DEFAULT '=='::bpchar", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private RadiusOperation userop;

    @Column(name = "userval", columnDefinition = "character varying(250) DEFAULT ''::character varying", nullable = false)
    @NotNull
    @Size(min = 3, max = 20, message = "The user name must be at least 3 characters long.")
    private String userval;

    @Transient
    private String tableName;

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

    public RadiusAttribute getUserattr () {
        return userattr;
    }

    public void setUserattr (RadiusAttribute userattr) {
        this.userattr = userattr;
    }

    public RadiusOperation getUserop () {
        return userop;
    }

    public void setUserop (RadiusOperation userop) {
        this.userop = userop;
    }

    public String getUserval () {
        return userval;
    }

    public void setUserval (String userval) {
        this.userval = userval;
    }

    public String getTableName () {
        return tableName;
    }

    public void setTableName (String tableName) {
        this.tableName = tableName;
    }

    public RUser () {
        this.userop = RadiusOperation.EQUALS_REGEXP;
        this.userattr = RadiusAttribute.FRAMED_MTU;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RUser rUser = (RUser) o;

        if (id != null ? !id.equals(rUser.id) : rUser.id != null) return false;
        if (username != null ? !username.equals(rUser.username) : rUser.username != null) return false;
        if (userattr != rUser.userattr) return false;
        if (userop != rUser.userop) return false;
        if (userval != null ? !userval.equals(rUser.userval) : rUser.userval != null) return false;
        return !(tableName != null ? !tableName.equals(rUser.tableName) : rUser.tableName != null);

    }

    @Override
    public int hashCode () {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (userattr != null ? userattr.hashCode() : 0);
        result = 31 * result + (userop != null ? userop.hashCode() : 0);
        result = 31 * result + (userval != null ? userval.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "RUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userattr=" + userattr +
                ", userop=" + userop +
                ", userval='" + userval + '\'' +
                ", tableName='" + tableName + '\'' +
                '}';
    }
}
