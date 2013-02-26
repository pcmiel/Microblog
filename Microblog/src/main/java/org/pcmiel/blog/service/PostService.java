package org.pcmiel.blog.service;

import java.util.List;

import org.pcmiel.blog.entity.BlogUser;
import org.pcmiel.blog.entity.Post;

public interface PostService {
	List<Post> getAllPosts();

	List<Post> getFollowingPosts();

	List<Post> getMyPosts();

	void addNewPost(String news, BlogUser user);

	void removePost(Integer id);
}
