package org.pcmiel.blog.dao;

import java.util.List;

import org.pcmiel.blog.entity.BlogUser;
import org.pcmiel.blog.entity.Post;

public interface PostDao {
	List<Post> getAllPosts();
	Post getPostById(int id);
	void addNewPost(Post post);
	void removePost(Post post);
	List<Post> getUserPosts(BlogUser user);
	List<Post> getPostsByUsersId(List usersId);
}
