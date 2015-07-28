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
@Table(name = "rgroupreply")
public class RGroupReply implements Serializable {

    private static final long serialVersionUID = 6572611120160218859L;

    @Id
    @GenericGenerator(name = "rgroupreply_groupid_seq", strategy = "sequence", parameters = {@Parameter(name = "sequence", value = "rgroupreply_groupid_seq")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rgroupreply_groupid_seq")
    @Column(name = "groupreplyid", nullable = false)
    private Long id;

    @Column(name = "groupreplyname", columnDefinition = "character varying(30) DEFAULT ''::character varying", nullable = false)
    @NotNull
    @Size(min = 3, max = 20, message = "The group name must be at least 3 characters long.")
    private String groupreplyname;

    @Column(name = "groupreplyattr", columnDefinition = "character varying(64) DEFAULT ''::character varying", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RadiusAttribute groupreplyattr;

    @Column(name = "groupreplyop", columnDefinition = "character(2) DEFAULT '=='::bpchar", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private RadiusOperation groupreplyop;

    @Column(name = "groupreplyval", columnDefinition = "character varying(250) DEFAULT ''::character varying", nullable = false)
    @NotNull
    @Size(min = 3, max = 20, message = "The group name must be at least 3 characters long.")
    private String groupreplyval;

    public RGroupReply () {
        this.groupreplyop = RadiusOperation.EQUALS_REGEXP;
        this.groupreplyattr = RadiusAttribute.CLEARTEXT_PASSWORD;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getGroupreplyname () {
        return groupreplyname;
    }

    public void setGroupreplyname (String groupreplyname) {
        this.groupreplyname = groupreplyname;
    }

    public RadiusAttribute getGroupreplyattr () {
        return groupreplyattr;
    }

    public void setGroupreplyattr (RadiusAttribute groupreplyattr) {
        this.groupreplyattr = groupreplyattr;
    }

    public RadiusOperation getGroupreplyop () {
        return groupreplyop;
    }

    public void setGroupreplyop (RadiusOperation groupreplyop) {
        this.groupreplyop = groupreplyop;
    }

    public String getGroupreplyval () {
        return groupreplyval;
    }

    public void setGroupreplyval (String groupreplyval) {
        this.groupreplyval = groupreplyval;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RGroupReply that = (RGroupReply) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (groupreplyname != null ? !groupreplyname.equals(that.groupreplyname) : that.groupreplyname != null)
            return false;
        if (groupreplyattr != that.groupreplyattr) return false;
        if (groupreplyop != that.groupreplyop) return false;
        return !(groupreplyval != null ? !groupreplyval.equals(that.groupreplyval) : that.groupreplyval != null);

    }

    @Override
    public int hashCode () {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (groupreplyname != null ? groupreplyname.hashCode() : 0);
        result = 31 * result + (groupreplyattr != null ? groupreplyattr.hashCode() : 0);
        result = 31 * result + (groupreplyop != null ? groupreplyop.hashCode() : 0);
        result = 31 * result + (groupreplyval != null ? groupreplyval.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "RGroupReply{" +
                "id=" + id +
                ", groupreplyname='" + groupreplyname + '\'' +
                ", groupreplyattr=" + groupreplyattr +
                ", groupreplyop=" + groupreplyop +
                ", groupreplyval='" + groupreplyval + '\'' +
                '}';
    }
}
