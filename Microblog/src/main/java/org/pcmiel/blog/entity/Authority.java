package org.pcmiel.blog.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITIES")
public class Authority {
	
	public Authority()
	{
	}
	
	public Authority(String authority)
	{
		this.authority = authority;
	}	

	@Id
	@GeneratedValue
	@Column(name = "AUTHORITY_ID")
	private Integer authorityId;

	@Column(name = "AUTHORITY")
	private String authority;

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (authorityId == null) {
			return -1;
		}
		result =  (prime * result + authorityId);
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
		Authority other = (Authority) obj;
		if (authorityId != other.authorityId)
			return false;
		return true;
	}
}