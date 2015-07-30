package net.odtel.dzuser.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "jfailreject")
public class FailReject {
    @Id
    @Column(name = "id")
    private Long Id;

    @Column(name = "timereject")
    private Long timeReject;

    @Column(name = "username")
    private String userName;

    @Column(name = "nas")
    private String nasIp;

    @Column(name = "message")
    private String message;

    public FailReject () {
        super();
    }

    /**
     * @param timeReject
     * @param userName
     * @param nasIp
     * @param message
     */
    public FailReject (Long timeReject, String userName, String nasIp,
                       String message) {
        super();
        this.timeReject = timeReject;
        this.userName = userName;
        this.nasIp = nasIp;
        this.message = message;
    }

    /**
     * @return the id
     */
    public Long getId () {
        return Id;
    }

    /**
     * @param id the id to set
     */
    public void setId (Long id) {
        Id = id;
    }

    /**
     * @return the timeReject
     */
    public Long getTimeReject () {
        return timeReject;
    }

    /**
     * @param timeReject the timeReject to set
     */
    public void setTimeReject (Long timeReject) {
        this.timeReject = timeReject;
    }

    public String getTimeRejectString () {
        Date date = new Date(timeReject * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss, MMMM dd");
        return (sdf.format(date));
    }

    /**
     * @return the userName
     */
    public String getUserName () {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName (String userName) {
        this.userName = userName;
    }

    /**
     * @return the nasIp
     */
    public String getNasIp () {
        return nasIp;
    }

    /**
     * @param nasIp the nasIp to set
     */
    public void setNasIp (String nasIp) {
        this.nasIp = nasIp;
    }

    /**
     * @return the message
     */
    public String getMessage () {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage (String message) {
        this.message = message;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FailReject that = (FailReject) o;

        if (Id != null ? !Id.equals(that.Id) : that.Id != null) return false;
        if (timeReject != null ? !timeReject.equals(that.timeReject) : that.timeReject != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (nasIp != null ? !nasIp.equals(that.nasIp) : that.nasIp != null) return false;
        return !(message != null ? !message.equals(that.message) : that.message != null);

    }

    @Override
    public int hashCode () {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + (timeReject != null ? timeReject.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (nasIp != null ? nasIp.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "FailReject{" +
                "Id=" + Id +
                ", timeReject=" + timeReject +
                ", userName='" + userName + '\'' +
                ", nasIp='" + nasIp + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
