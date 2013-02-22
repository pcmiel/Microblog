package org.pcmiel.blog.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcmiel.blog.dao.FollowDao;
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
public class FollowDaoTest {

	@Autowired
	UserDao userDao;
	
	@Autowired
	PostDao postDao;
	
	@Autowired
	FollowDao followDao;
	
	BlogUser user1;
	BlogUser user2;
	BlogUser user3;
	
	@Before
	public void setUp() throws Exception {
		user1 = newUser("test1", "pass");
		user2 = newUser("test2", "pass");
		user3 = newUser("test3", "pass");	
		userDao.addNewUser(user1);
		userDao.addNewUser(user2);
		userDao.addNewUser(user3);
		Set<BlogUser> following = new HashSet<BlogUser>();
		following.add(user2);
		following.add(user3);
		followDao.addFollowing(user1, following);
	}

	@Test
	public void shouldFollowDaoBeNotNull() throws Exception {
		assertNotNull(followDao);
	}	
	
	@Test
	public void testGetFollowingUsersId() throws Exception{
		List usersIdList = followDao.getFollowingUsersId(userDao.getUserByUserName("test1"));
		assertNotNull(usersIdList);
	}
	
	@Test
	public void testRemoveFollowing() throws Exception{
		Set<BlogUser> usersSet = new HashSet<BlogUser>();
		usersSet.add(user2);
		followDao.removeFollowing(user1, usersSet);
		List usersIdList = followDao.getFollowingUsersId(user1);
		assertEquals(usersIdList.size(), 0);
	}
	
	@Test
	public void testAddFollowing() throws Exception{
		Set<BlogUser> usersSet = new HashSet<BlogUser>();
		usersSet.add(user3);
		followDao.removeFollowing(user1, usersSet);
		List usersIdList = followDao.getFollowingUsersId(user1);
		assertEquals(usersIdList.size(), 2);
	}
	
	private BlogUser newUser(String name, String password) {
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(new Authority("ROLE_USER"));
		BlogUser user = new BlogUser(name, password, authorities, true);
		return user;
	}

}
