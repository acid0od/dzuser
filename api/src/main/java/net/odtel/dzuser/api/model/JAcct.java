package net.odtel.dzuser.api.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "jacct_")
public class JAcct implements Serializable {

    private static final long serialVersionUID = 1825478216570229788L;

    @Id
    @GenericGenerator(name = "acct_seq", strategy = "sequence", parameters = {@Parameter(name = "sequence", value = "acct_seq")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acct_seq")
    @Column(name = "acctid", nullable = false)
    private Long id;

    @Column(name = "acctuser", columnDefinition = "character varying(30) DEFAULT ''::character varying", nullable = false)
    private String acctuser;

    @Column(name = "acctphone", columnDefinition = "character varying(30) DEFAULT ''::character varying")
    private String acctphone;

    @Column(name = "acctip", columnDefinition = "character varying(15) DEFAULT ''::character varying", nullable = false)
    private String acctip;

    @Column(name = "nasid", nullable = false)
    private Integer nasid;

    @Column(name = "acctport", columnDefinition = "character varying(10) DEFAULT ''::character varying")
    private String acctport;

    @Column(name = "acctstart", nullable = false)
    private Long acctstart;

    @Column(name = "acctduration", nullable = false)
    private Integer acctduration;

    @Column(name = "acctin")
    private Long acctin = 0L;

    @Column(name = "acctout")
    private Long acctout = 0L;

    public JAcct () {

    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getAcctuser () {
        return acctuser;
    }

    public void setAcctuser (String acctuser) {
        this.acctuser = acctuser;
    }

    public String getAcctphone () {
        return acctphone;
    }

    public void setAcctphone (String acctphone) {
        this.acctphone = acctphone;
    }

    public String getAcctip () {
        return acctip;
    }

    public void setAcctip (String acctip) {
        this.acctip = acctip;
    }

    public Integer getNasid () {
        return nasid;
    }

    public void setNasid (Integer nasid) {
        this.nasid = nasid;
    }

    public String getAcctport () {
        return acctport;
    }

    public void setAcctport (String acctport) {
        this.acctport = acctport;
    }

    public Long getAcctstart () {
        return acctstart;
    }

    public void setAcctstart (Long acctstart) {
        this.acctstart = acctstart;
    }

    public Integer getAcctduration () {
        return acctduration;
    }

    public void setAcctduration (Integer acctduration) {
        this.acctduration = acctduration;
    }

    public Integer getAcctdurationHours () {
        return Math.round(acctduration / 3600);
    }

    public Long getAcctin () {
        return acctin;
    }

    public void setAcctin (Long acctin) {
        this.acctin = acctin;
    }

    public Long getAcctout () {
        return acctout;
    }

    public void setAcctout (Long acctout) {
        this.acctout = acctout;
    }

    public Integer getAcctoutMb () {
        return Math.round(acctout / (1024 * 1024));
    }

    @Override
    public boolean equals (Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JAcct jAcct = (JAcct) o;

        if (id != null ? !id.equals(jAcct.id) : jAcct.id != null) return false;
        if (acctuser != null ? !acctuser.equals(jAcct.acctuser) : jAcct.acctuser != null) return false;
        if (acctphone != null ? !acctphone.equals(jAcct.acctphone) : jAcct.acctphone != null) return false;
        if (acctip != null ? !acctip.equals(jAcct.acctip) : jAcct.acctip != null) return false;
        if (nasid != null ? !nasid.equals(jAcct.nasid) : jAcct.nasid != null) return false;
        if (acctport != null ? !acctport.equals(jAcct.acctport) : jAcct.acctport != null) return false;
        if (acctstart != null ? !acctstart.equals(jAcct.acctstart) : jAcct.acctstart != null) return false;
        if (acctduration != null ? !acctduration.equals(jAcct.acctduration) : jAcct.acctduration != null) return false;
        if (acctin != null ? !acctin.equals(jAcct.acctin) : jAcct.acctin != null) return false;
        if (acctout != null ? !acctout.equals(jAcct.acctout) : jAcct.acctout != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (acctuser != null ? acctuser.hashCode() : 0);
        result = 31 * result + (acctphone != null ? acctphone.hashCode() : 0);
        result = 31 * result + (acctip != null ? acctip.hashCode() : 0);
        result = 31 * result + (nasid != null ? nasid.hashCode() : 0);
        result = 31 * result + (acctport != null ? acctport.hashCode() : 0);
        result = 31 * result + (acctstart != null ? acctstart.hashCode() : 0);
        result = 31 * result + (acctduration != null ? acctduration.hashCode() : 0);
        result = 31 * result + (acctin != null ? acctin.hashCode() : 0);
        result = 31 * result + (acctout != null ? acctout.hashCode() : 0);
        return result;
    }

    @Override
    public String toString () {
        return "JAcct{" +
                "id=" + id +
                ", acctuser='" + acctuser + '\'' +
                ", acctphone='" + acctphone + '\'' +
                ", acctip='" + acctip + '\'' +
                ", nasid=" + nasid +
                ", acctport='" + acctport + '\'' +
                ", acctstart=" + acctstart +
                ", acctduration=" + acctduration +
                ", acctin=" + acctin +
                ", acctout=" + acctout +
                '}';
    }

}
