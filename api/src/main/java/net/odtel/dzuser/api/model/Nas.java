package net.odtel.dzuser.api.model;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "rnas")
@NamedQueries({
        @NamedQuery(name = "Nas.findByCheckWithCisco", query = "select n from Nas n where n.check = 1 and n.type = 3"),
        @NamedQuery(name = "Nas.findByCheckWithMyFilters", query = "select n from Nas n where n.check = 1") })
public class Nas implements Serializable {

    private static final long serialVersionUID = -4265477239069958138L;

    @Id
    @GenericGenerator(name = "rnas_nasid_seq", strategy = "sequence", parameters = { @Parameter(name = "sequence", value = "rnas_nasid_seq") })
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rnas_nasid_seq")
    @Column(name = "nasid", nullable = false)
    private Integer id;

    @Column(name = "nasip", columnDefinition = "character varying(15)", nullable = false)
    @NotNull(message = "{model.nas.ip.error}")
    @Pattern(regexp = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$", message = "{model.nas.ip.pattern.error}")
    private String ip;

    @Column(name = "nascommunity", columnDefinition = "character varying(50)")
    @Size(min = 3, max = 20, message = "{model.nas.community.error}")
    private String community;

    @Column(name = "nasdescription", columnDefinition = "character varying(200)")
    @NotNull
    @Size(min = 3, max = 20, message = "{model.nas.description.error}")
    private String description;

    @OneToOne
    @JoinColumn(name = "nastype")
    @NotNull(message = "{model.nas.type.error}")
    private NasType type;

    @Column(name = "nascheck", columnDefinition = "smallint DEFAULT 1", nullable = false)
    private Integer check;

    @OneToOne
    @JoinColumn(name = "locationid")
    @NotNull(message = "{model.nas.location.error}")
    private Location location;

    @Column(name = "nasdropoid", columnDefinition = "character varying")
    private String dropOid;

    @Column(name = "nasactiveoid", columnDefinition = "character varying")
    private String activeOid;

    public Nas() {
        super();
        this.id = null;
        this.ip = null;
        this.community = null;
        this.description = null;
        this.type = null;
        this.check = 0;
        this.location = null;
        this.dropOid = null;
        this.activeOid = null;

    }

    public Nas(Integer id, String ip, String community, String description,
               NasType type, Integer check, Location location, String dropOid,
               String activeOid) {
        super();
        this.id = id;
        this.ip = ip;
        this.community = community;
        this.description = description;
        this.type = type;
        this.check = check;
        this.location = location;
        this.dropOid = dropOid;
        this.activeOid = activeOid;
    }

    public static long getSerialversionuid () {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NasType getType() {
        return type;
    }

    public void setType(NasType type) {
        this.type = type;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public boolean isCheck() {
        if (this.check == null) {
            return false;
        }
        if (this.check == 1) {
            return true;
        }
        return false;
    }

    public void setCheck (boolean check) {
        if (check)
            this.check = 1;
        else
            this.check = 0;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDropOid() {
        return dropOid;
    }

    public void setDropOid(String dropOid) {
        this.dropOid = dropOid;
    }

    public String getActiveOid() {
        return activeOid;
    }

    public void setActiveOid(String activeOid) {
        this.activeOid = activeOid;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nas nas = (Nas) o;

        if (id != null ? !id.equals(nas.id) : nas.id != null) return false;
        if (ip != null ? !ip.equals(nas.ip) : nas.ip != null) return false;
        if (community != null ? !community.equals(nas.community) : nas.community != null) return false;
        if (description != null ? !description.equals(nas.description) : nas.description != null) return false;
        if (type != null ? !type.equals(nas.type) : nas.type != null) return false;
        if (check != null ? !check.equals(nas.check) : nas.check != null) return false;
        if (location != null ? !location.equals(nas.location) : nas.location != null) return false;
        if (dropOid != null ? !dropOid.equals(nas.dropOid) : nas.dropOid != null) return false;
        return !(activeOid != null ? !activeOid.equals(nas.activeOid) : nas.activeOid != null);

    }

    @Override
    public int hashCode () {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (community != null ? community.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (check != null ? check.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (dropOid != null ? dropOid.hashCode() : 0);
        result = 31 * result + (activeOid != null ? activeOid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "Nas{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", community='" + community + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", check=" + check +
                ", location=" + location +
                ", dropOid='" + dropOid + '\'' +
                ", activeOid='" + activeOid + '\'' +
                '}';
    }
}