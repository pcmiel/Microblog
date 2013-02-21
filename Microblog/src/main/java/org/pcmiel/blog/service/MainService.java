package org.pcmiel.blog.service;

import java.util.List;
import java.util.Set;

import org.pcmiel.blog.entity.Authority;
import org.pcmiel.blog.entity.BlogUser;
import org.pcmiel.blog.entity.Post;

public interface MainService {

	List<Post> getAllPosts();
	List<Post> getFollowingPosts();
	List<Post> getMyPosts();
	void addNewPost(String news, BlogUser user);
	void removePost(Integer id);
	void addNewUser(String userName, String password, List<String> authorities,
			Boolean isEnabled);	
	List<BlogUser> getAllUsers();
	Boolean checkThatUserExist(String username);
	BlogUser getLoggedInUser();
	void addNewAuthority(String authority);
	void InsertTestDatas();
	List<BlogUser> getFollowing(String username);
	List<BlogUser> getUnfollowing(String username);
	void addFollowing(String username, String followUsername);
	void removeFollowing(String username, String unfollowUsername) ;
}