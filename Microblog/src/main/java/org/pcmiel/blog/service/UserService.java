package org.pcmiel.blog.service;

import java.util.List;

import org.pcmiel.blog.entity.BlogUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
	void addNewUser(String userName, String password, List<String> authorities,
			Boolean isEnabled);

	List<BlogUser> getAllUsers();

	Boolean checkThatUserExist(String username);

	BlogUser getLoggedInUser();

	void addNewAuthority(String authority);

	UserDetails loadUserByUsername(String userName);
}
