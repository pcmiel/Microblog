package org.juri.blog.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.hibernate.validator.constraints.NotEmpty;





@Entity
@Table(name = "USERS")
public class BlogUser implements UserDetails {

	private static final long serialVersionUID = 133260785057988071L;

	// public Set<Authority> getAuthoritySet() {
	// return authoritySet;
	// }

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long userId;

	@NotEmpty(message = "Login is required!")
	@Size(min = 3, max = 20, message = "Login length must be between 3 and 40")
	@Column(name = "USERNAME")
	private String username;

	@NotEmpty(message = "Password is required!")
	@Size(min = 4, max = 40, message = "Password length must be between 4 and 40")
	@Column(name = "PASSWORD")
	private String password;

	private String confirmPassword;
	



	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Column(name = "ENABLED")
	private Boolean enabled;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "USER_AUTHORITIES", joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "AUTHORITY_ID") })
	private Set<Authority> authoritySet;
	// private transient Collection<GrantedAuthority> authorities;

	// Spring Security props
	private transient Collection<GrantedAuthority> authorities;

	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	public boolean isEnabled() {
		return enabled;
	}

	public Set<Authority> getAuthoritySet() {
		return authoritySet;
	}

	public void setAuthoritySet(Set<Authority> authoritySet) {
		this.authoritySet = authoritySet;
	}

	@Transient
	public void setUserAuthorities(List<String> authorities) {
		List<GrantedAuthority> listOfAuthorities = new ArrayList<GrantedAuthority>();
		for (String role : authorities) {
			listOfAuthorities.add(new GrantedAuthorityImpl(role));
		}
		this.authorities = (Collection<GrantedAuthority>) listOfAuthorities;

	}

	// @Transient
	// public void setUserAuthorities(List<String> authorities) {
	// Set<Authority> listOfAuthorities = new HashSet<Authority>();
	// for (String role : authorities) {
	// Authority auth = new Authority();
	// auth.setAuthority(role);
	// listOfAuthorities.add(auth);
	// }
	// this.authoritySet = listOfAuthorities;
	//
	// }

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
}
