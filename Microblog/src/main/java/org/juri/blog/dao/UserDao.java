package org.juri.blog.dao;

import java.util.List;
import java.util.Set;

import org.juri.blog.entity.Authority;
import org.juri.blog.entity.BlogUser;

public interface UserDao {
	BlogUser getUserByUserName(String userName);
	void addNewUser(BlogUser user);
	List<BlogUser> getAllUsers();
	void addNewAuthority(Authority authority);
	Authority getAuthority(String authorityName);
	void addFollowing(BlogUser user, Set<BlogUser> following);
}
