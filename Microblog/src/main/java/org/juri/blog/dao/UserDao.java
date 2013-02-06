package org.juri.blog.dao;

import org.juri.blog.entity.BlogUser;

public interface UserDao {
	BlogUser getUserByUserName(String userName);
	void addNewUser(BlogUser user);
}
