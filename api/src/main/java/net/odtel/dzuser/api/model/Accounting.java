package net.odtel.dzuser.api.model;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "jacct_")
public class Accounting implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8950062012703204094L;

    @Id
    @GenericGenerator(name = "acct_seq", strategy = "sequence", parameters = {@Parameter(name = "sequence", value = "acct_seq")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acct_seq")
    @Column(name = "acctid", columnDefinition = "bigint", nullable = false)
    private Long Id;

    @Column(name = "acctuser", columnDefinition = "character varying(30)", nullable = false)
    private String user;

    @Column(name = "acctphone", columnDefinition = "character varying(30)")
    private String phone;

    @Column(name = "acctip", columnDefinition = "character varying(15)", nullable = false)
    private String ip;

    @OneToOne
    @JoinColumn(name = "nasid", nullable = false)
    private Nas nasid;

    @Column(name = "acctport", columnDefinition = "character varying(10)")
    private String port;

    @Column(name = "acctstart", columnDefinition = "bigint", nullable = false)
    private Long timeStart;

    @Column(name = "acctduration", nullable = false)
    private Integer timeDuration;

    @Column(name = "acctin")
    private Long byteIn;

    @Column(name = "acctout")
    private Long byteOut;

    /**
     *
     */
    public Accounting() {
        super();
        this.user = null;
        this.phone = null;
        this.ip = null;
        this.nasid = null;
        this.port = null;
        this.timeStart = 0L;
        this.timeDuration = 0;
        this.byteIn = 0L;
        this.byteOut = 0L;
    }

    /**
     * @param user
     * @param phone
     * @param ip
     * @param nasid
     * @param port
     * @param timeStart
     * @param timeDuration
     * @param byteIn
     * @param byteOut
     */
    public Accounting(String user, String phone, String ip, Nas nasid,
                      String port, Long timeStart, Integer timeDuration, Long byteIn,
                      Long byteOut) {
        super();

        this.user = user;
        this.phone = phone;
        this.ip = ip;
        this.nasid = nasid;
        this.port = port;
        this.timeStart = timeStart;
        this.timeDuration = timeDuration;
        this.byteIn = byteIn;
        this.byteOut = byteOut;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return Id;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @return the nasid
     */
    public Nas getNasid() {
        return nasid;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @return the timeStart
     */
    public Long getTimeStart() {
        return timeStart;
    }

    /**
     * @return the Formatted timeStart String
     */
    public String getTimeStartString() {
        Date date = new Date(timeStart * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss, MMMM dd");
        return (sdf.format(date));
    }

    /**
     * @return the timeDuration
     */
    public Integer getTimeDuration() {
        return timeDuration;
    }

    public String getTimeDurationString() {

        StringBuilder duration;
        duration = new StringBuilder();
        Integer seconds = timeDuration;

        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds);

        long tempSec = seconds - (TimeUnit.HOURS.toSeconds(hours));

        long minute = TimeUnit.SECONDS.toMinutes(tempSec);

        //System.out.println("after hours calculation "+ tempSec);
        if (tempSec > TimeUnit.MINUTES.toSeconds(minute)) {
            tempSec = tempSec - (TimeUnit.MINUTES.toSeconds(minute));
        } else {
            tempSec = TimeUnit.MINUTES.toSeconds(minute) - tempSec;
        }

        long second = TimeUnit.SECONDS.toSeconds(tempSec);

        if (day > 0) {
            duration.append(day).append("days ");
        }

        duration.append(StringUtils.leftPad(String.valueOf(hours), 2, "0")).append(":");
        duration.append(StringUtils.leftPad(String.valueOf(minute), 2, "0")).append(":");
        duration.append(StringUtils.leftPad(String.valueOf(second), 2, "0"));

        return duration.toString();
    }

    /**
     * @return the byteIn
     */
    public Long getByteIn() {
        return byteIn;
    }

    /**
     * @return the byteOut
     */
    public Long getByteOut() {
        return byteOut;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        Id = id;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @param nasid the nasid to set
     */
    public void setNasid(Nas nasid) {
        this.nasid = nasid;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @param timeStart the timeStart to set
     */
    public void setTimeStart(Long timeStart) {
        this.timeStart = timeStart;
    }

    /**
     * @param timeDuration the timeDuration to set
     */
    public void setTimeDuration(Integer timeDuration) {
        this.timeDuration = timeDuration;
    }

    /**
     * @param byteIn the byteIn to set
     */
    public void setByteIn(Long byteIn) {
        this.byteIn = byteIn;
    }

    /**
     * @param byteOut the byteOut to set
     */
    public void setByteOut(Long byteOut) {
        this.byteOut = byteOut;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accounting that = (Accounting) o;

        if (Id != null ? !Id.equals(that.Id) : that.Id != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (nasid != null ? !nasid.equals(that.nasid) : that.nasid != null) return false;
        if (port != null ? !port.equals(that.port) : that.port != null) return false;
        if (timeStart != null ? !timeStart.equals(that.timeStart) : that.timeStart != null) return false;
        if (timeDuration != null ? !timeDuration.equals(that.timeDuration) : that.timeDuration != null) return false;
        if (byteIn != null ? !byteIn.equals(that.byteIn) : that.byteIn != null) return false;
        return !(byteOut != null ? !byteOut.equals(that.byteOut) : that.byteOut != null);

    }

    @Override
    public int hashCode () {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (nasid != null ? nasid.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (timeStart != null ? timeStart.hashCode() : 0);
        result = 31 * result + (timeDuration != null ? timeDuration.hashCode() : 0);
        result = 31 * result + (byteIn != null ? byteIn.hashCode() : 0);
        result = 31 * result + (byteOut != null ? byteOut.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "Accounting{" +
                "Id=" + Id +
                ", user='" + user + '\'' +
                ", phone='" + phone + '\'' +
                ", ip='" + ip + '\'' +
                ", nasid=" + nasid +
                ", port='" + port + '\'' +
                ", timeStart=" + timeStart +
                ", timeDuration=" + timeDuration +
                ", byteIn=" + byteIn +
                ", byteOut=" + byteOut +
                '}';
    }
}
