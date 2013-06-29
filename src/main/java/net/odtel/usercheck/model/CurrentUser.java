package net.odtel.usercheck.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jcurrent")
public class CurrentUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3398051641137405616L;

	
	@Column(name = "nasid", nullable = false)
	private Integer nasid;

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

	@Column(name = "currentstart", columnDefinition = "bigint DEFAULT date_part('epoch'::text, now())", nullable = false)
	private Long dateStart;

	public CurrentUser() {

	}

	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	public Integer getNasid() {
		return nasid;
	}

	public void setNasid(Integer nasid) {
		this.nasid = nasid;
	}

	public String getUserPort() {
		return userPort;
	}

	public void setUserPort(String userPort) {
		this.userPort = userPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Long getDateDuration() {
		return dateDuration;
	}

	public void setDateDuration(Long dateDuration) {
		this.dateDuration = dateDuration;
	}

	public Long getDateStart() {
		return dateStart;
	}

	public String getDateStartString() {
		
		
		Date date = new Date(dateStart * 1000);
		//SimpleDateFormat sdf = new SimpleDateFormat("EEEE,MMMM d,yyyy h:mm,a");
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss, MMMM dd");
		return (sdf.format(date));
	}

	
	public void setDateStart(Long dateStart) {
		this.dateStart = dateStart;
	}

}
