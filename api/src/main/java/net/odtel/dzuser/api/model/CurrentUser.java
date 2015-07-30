package net.odtel.dzuser.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "jcurrent")
public class CurrentUser implements Serializable {

    private static final long serialVersionUID = 3398051641137405616L;

    @OneToOne
    @JoinColumn(name = "nasid")
    private Nas nasid;

    @Column(name = "currentport", columnDefinition = "character varying(10)", nullable = false)
    private String userPort;

    @Id
    @Column(name = "currentuser", columnDefinition = "character varying(30)", nullable = false)
    private String userName;

    @Column(name = "currentphone", columnDefinition = "character varying(30)")
    private String userPhone;

    @Column(name = "currentip", columnDefinition = "character varying(15)", nullable = false)
    private String userIP;

    @Column(name = "currentid", columnDefinition = "character varying(32) DEFAULT ''::character varying", nullable = false)
    private String userID;

    @Column(name = "currentlimit", columnDefinition = "bigint", nullable = false)
    private Long dateDuration;

    @Column(name = "currentstart", columnDefinition = "bigint", nullable = false)
    private Long dateStart;

    public CurrentUser () {

    }

    public String getUserPhone () {
        return userPhone;
    }

    public void setUserPhone (String userPhone) {
        this.userPhone = userPhone;
    }

    public Nas getNasid () {
        return nasid;
    }

    public void setNasid (Nas nasid) {
        this.nasid = nasid;
    }

    public String getUserPort () {
        return userPort;
    }

    public void setUserPort (String userPort) {
        this.userPort = userPort;
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

    public String getUserID () {
        return userID;
    }

    public void setUserID (String userID) {
        this.userID = userID;
    }

    public Long getDateDuration () {
        return dateDuration;
    }

    public void setDateDuration (Long dateDuration) {
        this.dateDuration = dateDuration;
    }

    public String getDateDurationString () {

        Date date = new Date(dateDuration * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss, MMMM dd");
        return (sdf.format(date));
    }

    public Long getDateStart () {
        return dateStart;
    }

    public void setDateStart (Long dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateStartString () {

        Date date = new Date(dateStart * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss, MMMM dd");
        return (sdf.format(date));
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrentUser that = (CurrentUser) o;

        if (nasid != null ? !nasid.equals(that.nasid) : that.nasid != null) return false;
        if (userPort != null ? !userPort.equals(that.userPort) : that.userPort != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userPhone != null ? !userPhone.equals(that.userPhone) : that.userPhone != null) return false;
        if (userIP != null ? !userIP.equals(that.userIP) : that.userIP != null) return false;
        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        if (dateDuration != null ? !dateDuration.equals(that.dateDuration) : that.dateDuration != null) return false;
        return !(dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null);

    }

    @Override
    public int hashCode () {
        int result = nasid != null ? nasid.hashCode() : 0;
        result = 31 * result + (userPort != null ? userPort.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userPhone != null ? userPhone.hashCode() : 0);
        result = 31 * result + (userIP != null ? userIP.hashCode() : 0);
        result = 31 * result + (userID != null ? userID.hashCode() : 0);
        result = 31 * result + (dateDuration != null ? dateDuration.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "CurrentUser [nasid=" + nasid + ", userPort=" + userPort
                + ", userName=" + userName + ", userPhone=" + userPhone
                + ", userIP=" + userIP + ", userID=" + userID
                + ", dateDuration=" + dateDuration + ", dateStart=" + dateStart
                + "]";
    }

}
