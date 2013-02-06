package org.juri.blog.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.juri.blog.dao.PostDao;
import org.juri.blog.dao.UserDao;
import org.juri.blog.entity.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mainService")
@Transactional
public class MainServiceImpl implements MainService {

	protected static Logger logger = Logger.getLogger("service");

	@Resource
	private PostDao postDao;

	public List<Post> getAllPosts() {
		logger.debug("inside service getAllPosts()");
		List<Post> resultPosts = postDao.getAllPosts();
		return resultPosts;
	}

	public void addNewPost(String title, String news, Integer authorId) {
		logger.debug("inside service addNewPost()");

		Post post = new Post();
		post.setTitle(title);
		post.setNews(news);
		post.setAuthorId(authorId);
		post.setDate("11/11/2011");

		postDao.addNewPost(post);
	}
}
