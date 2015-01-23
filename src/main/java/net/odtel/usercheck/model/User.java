package net.odtel.usercheck.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class User implements UserDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6881274655103401285L;
	
	private String  username;
	private String  password;
	
	private Collection<GrantedAuthority> authorities;
	
	public User(String username, String password, String roles) {
		super();
		this.username = username;
		this.password = password;
		this.setRoles(roles);
	}

	public void setRoles(String roles) {
		this.authorities = new HashSet<GrantedAuthority>();
		for (final String role : roles.split(",")) {
			if (role != null && !"".equals(role.trim())) {
				GrantedAuthority grandAuthority = new GrantedAuthority() {
					private static final long serialVersionUID = 3958183417696804555L;

					public String getAuthority() {
						return role.trim();
					}
				};
				this.authorities.add(grandAuthority);
			}
		}
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
