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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "rgroup")
public class RGroup implements Serializable {

    private static final long serialVersionUID = -3505619469522281627L;

    @Id
    @GenericGenerator(name = "rgroup_groupid_seq", strategy = "sequence", parameters = {@Parameter(name = "sequence", value = "rgroup_groupid_seq")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rgroup_groupid_seq")
    @Column(name = "groupid", nullable = false)
    private Long id;

    @Column(name = "groupname", columnDefinition = "character varying(30) DEFAULT ''::character varying", nullable = false)
    @NotNull
    @Size(min = 3, max = 20, message = "The group name must be at least 3 characters long.")
    private String groupname;

    @Column(name = "groupattr", columnDefinition = "character varying(64) DEFAULT ''::character varying", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RadiusAttribute groupattr;

    @Column(name = "groupop", columnDefinition = "character(2) DEFAULT '=='::bpchar", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private RadiusOperation groupop;

    @Column(name = "groupval", columnDefinition = "character varying(250) DEFAULT ''::character varying", nullable = false)
    @NotNull
    @Size(min = 3, max = 20, message = "The group name must be at least 3 characters long.")
    private String groupval;

    public RGroup () {
        super();
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getGroupname () {
        return groupname;
    }

    public void setGroupname (String groupname) {
        this.groupname = groupname;
    }

    public RadiusAttribute getGroupattr () {
        return groupattr;
    }

    public void setGroupattr (RadiusAttribute groupattr) {
        this.groupattr = groupattr;
    }

    public RadiusOperation getGroupop () {
        return groupop;
    }

    public void setGroupop (RadiusOperation groupop) {
        this.groupop = groupop;
    }

    public String getGroupval () {
        return groupval;
    }

    public void setGroupval (String groupval) {
        this.groupval = groupval;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RGroup rGroup = (RGroup) o;

        if (id != null ? !id.equals(rGroup.id) : rGroup.id != null) return false;
        if (groupname != null ? !groupname.equals(rGroup.groupname) : rGroup.groupname != null) return false;
        if (groupattr != rGroup.groupattr) return false;
        if (groupop != rGroup.groupop) return false;
        return !(groupval != null ? !groupval.equals(rGroup.groupval) : rGroup.groupval != null);

    }

    @Override
    public int hashCode () {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (groupname != null ? groupname.hashCode() : 0);
        result = 31 * result + (groupattr != null ? groupattr.hashCode() : 0);
        result = 31 * result + (groupop != null ? groupop.hashCode() : 0);
        result = 31 * result + (groupval != null ? groupval.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "RGroup{" +
                "id=" + id +
                ", groupname='" + groupname + '\'' +
                ", groupattr=" + groupattr +
                ", groupop=" + groupop +
                ", groupval='" + groupval + '\'' +
                '}';
    }
}
