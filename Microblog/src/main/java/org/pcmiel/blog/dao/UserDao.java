package org.pcmiel.blog.dao;

import java.util.List;
import org.pcmiel.blog.entity.BlogUser;

public interface UserDao {
	BlogUser getUserByUserName(String userName);
	void addNewUser(BlogUser user);
	List<BlogUser> getAllUsers();	
}
