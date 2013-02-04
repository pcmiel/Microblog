package org.juri.blog.dao;

import org.juri.blog.entity.User;

public interface UserDao {
	User getUserByUserName(String userName);
}
