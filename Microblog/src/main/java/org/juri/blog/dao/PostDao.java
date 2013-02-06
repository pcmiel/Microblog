package org.juri.blog.dao;

import java.util.List;

import org.juri.blog.entity.Post;

public interface PostDao {
	List<Post> getAllPosts();
	void addNewPost(Post post);
}
