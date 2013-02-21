package org.pcmiel.blog.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USERS", uniqueConstraints=@UniqueConstraint(columnNames="username"))
public class BlogUser implements UserDetails , Comparable<BlogUser>{

	public BlogUser() {
	}

	public BlogUser(String username, String password,
			Set<Authority> authorities, Boolean isEnabled) {
		this.setUsername(username);
		this.setPassword(password);
		this.setAuthoritySet(authorities);
		this.setEnabled(isEnabled);
	}
	
	private static final long serialVersionUID = 133260785057988071L;

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")	
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

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

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "USER_AUTHORITIES", joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "AUTHORITY_ID") })
	private Set<Authority> authoritySet;

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
	
	@ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="FOLLOWING", 
                joinColumns={@JoinColumn(name="USER_ID")}, 
                inverseJoinColumns={@JoinColumn(name="FOLLOWING_ID")})
    private Set<BlogUser> following = new HashSet<BlogUser>();
	
	public Set<BlogUser> getFollowing() {
		return following;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (userId == null) {
			return -1;
		}
		result =  (prime * result + userId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogUser other = (BlogUser) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

	public void setFollowing(Set<BlogUser> following) {
		this.following = following;
	}

	public int compareTo(BlogUser user) {
		return this.getUsername().compareTo(user.getUsername());
	}	
}
