package org.juri.blog.dao;

import java.util.List;

import org.juri.blog.entity.Post;

public interface PostDao {
	List<Post> getAllPosts();
	Post getPostById(int id);
	void addNewPost(Post post);
	void removePost(Post post);
}
