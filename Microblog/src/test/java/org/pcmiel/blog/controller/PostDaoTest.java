package org.pcmiel.blog.controller;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcmiel.blog.dao.PostDao;
import org.pcmiel.blog.dao.UserDao;
import org.pcmiel.blog.entity.Authority;
import org.pcmiel.blog.entity.BlogUser;
import org.pcmiel.blog.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml",
		"/spring-security.xml" })
@Transactional
public class PostDaoTest {
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;
	
	BlogUser user1;
	BlogUser user2;
	Post post1;
	Post post2;

	@Before
	public void setUp() throws Exception {
		user1 = newUser("name1", "pass1");
		user2 = newUser("name2", "pass2");
		userDao.addNewUser(user1);
		userDao.addNewUser(user2);
		post1 = newPost("text1", user1);
		post2 = newPost("text2", user2);
		postDao.addNewPost(post1);
		postDao.addNewPost(post2);
	}
	
	@Test
	public void shouldPostDaoBeNotNull() throws Exception {
		assertNotNull(postDao);
	}
	
	@Test
	public void testGetAllPosts() throws Exception{
		List<Post> postList = postDao.getAllPosts();
		assertNotNull(postList);
		assertEquals(postList.size(), 2);
	}
	
	@Test
	public void testGetUserPosts() throws Exception{
		List<Post> testPost = postDao.getUserPosts(user1);
		assertNotNull(testPost);
		assertEquals(testPost.size(), 1);
		
	}
	
	@Test
	public void testAddNewPost() throws Exception{
		Post post = newPost("text", user1);
		postDao.addNewPost(post);
		List<Post> testPost = postDao.getUserPosts(user1);
		assertEquals(testPost.size(), 2);		
	}
	
	@Test
	public void testGetPostById() throws Exception{
		Post post = postDao.getUserPosts(user1).get(0);
		Post testPost = postDao.getPostById(post.getId());
		assertNotNull(testPost);
		assertEquals(testPost, post);
	}
	
	@Test
	public void testRemovePost() throws Exception{
		postDao.removePost(post1);
		List<Post> postList = postDao.getAllPosts();
		assertEquals(postList.size(), 1);
	}
	
	@Test
	public void testGetPostsByUsersId() throws Exception{
		List usersId = new ArrayList();
		List<Post> postList = postDao.getPostsByUsersId(usersId);
		assertNull(postList);
		usersId.add(user1.getUserId());
		postList = postDao.getPostsByUsersId(usersId);
		assertEquals(postList.size(), 1);
	}
	
	private BlogUser newUser(String name, String password) {
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(new Authority("ROLE_USER"));
		BlogUser user = new BlogUser(name, password, authorities, true);
		return user;
	}
		
	private Post newPost(String news, BlogUser user){
		Post post = new Post(news, new Date(), user);
		return post;
	}

}
