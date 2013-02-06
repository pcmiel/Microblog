package org.juri.blog.dao;

import org.juri.blog.entity.UserDS;

public interface UserDao {
	UserDS getUserByUserName(String userName);
	void addNewUser(UserDS user);
}
