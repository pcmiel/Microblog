package org.juri.blog.service;

import java.util.List;

import org.juri.blog.entity.BlogUser;
import org.juri.blog.entity.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface MainService {
	List<Post> getAllPosts();

	void addNewPost(String news, BlogUser user);

	void addNewUser(String userName, String password, List<String> authorities,
			Boolean isEnabled);
	
	Boolean checkIfUsernameExist(String username);

	BlogUser getLoggedInUser();
}
