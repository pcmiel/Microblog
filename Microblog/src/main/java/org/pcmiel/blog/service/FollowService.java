package org.pcmiel.blog.service;

import java.util.List;

import org.pcmiel.blog.entity.BlogUser;

public interface FollowService {
	List<BlogUser> getFollowing(String username);

	List<BlogUser> getUnfollowing(String username);

	void addFollowing(String username, String followUsername);

	void removeFollowing(String username, String unfollowUsername);
}
