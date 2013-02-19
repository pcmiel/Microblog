package org.pcmiel.blog.entity;

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
	private int authorityId;

	@Column(name = "AUTHORITY")
	private String authority;

	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}