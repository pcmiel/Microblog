package org.pcmiel.blog.dao;

import org.pcmiel.blog.entity.Authority;

public interface AuthorityDao {
	void addNewAuthority(Authority authority);
	Authority getAuthority(String authorityName);
}
