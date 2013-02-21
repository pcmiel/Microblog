package org.pcmiel.blog.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcmiel.blog.dao.UserDao;
import org.pcmiel.blog.entity.Authority;
import org.pcmiel.blog.entity.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml",
		"/spring-security.xml" })
@Transactional
public class UserDaoTest {

	@Autowired
	UserDao userDao;

	@Before
	public void setUp() throws Exception {
		userDao.addNewUser(newUser("name1", "pass1"));
		userDao.addNewUser(newUser("name2", "pass2"));
	}

	@Test
	public void shouldUserDaoBeNotNull() throws Exception {
		assertNotNull(userDao);
	}

	@Test
	public void TestAddNewUser() throws Exception {
		BlogUser user = newUser("name", "test");
		userDao.addNewUser(user);
		BlogUser user2 = userDao.getUserByUserName("name");
		assertNotNull(user2);
	}
	
	@Test
	public void TestGetUserByUserName() throws Exception{
		BlogUser user = userDao.getUserByUserName("name1");
		assertEquals(user.getUsername(), "name1");
		BlogUser user2 = userDao.getUserByUserName("name3");
		assertNull(user2);
	}
	
	@Test
	public void TestGetAllUsers() throws Exception{
		List<BlogUser> usersList = userDao.getAllUsers();
		assertEquals(usersList.size(), 2);
	}
	
	@Test
	public void TestGetUsersById() throws Exception{
		BlogUser user = userDao.getUserByUserName("name1");
		BlogUser user2 = userDao.getUserByUserName("name2");
		List Ids = new ArrayList<Long>();
		Ids.add(user.getUserId());
		Ids.add(user2.getUserId());
		List<BlogUser> usersList = userDao.getUsersById(Ids);		
		assertNotNull(usersList);
		assertEquals(usersList.size(), 2);
		assertNull(userDao.getUsersById(null));
		assertNull(userDao.getUsersById(new ArrayList()));
	}
	
	@Test
	public void TestGetUsersWhoseNotInList() throws Exception{
		BlogUser user = userDao.getUserByUserName("name1");
		List Ids = new ArrayList<Long>();
		Ids.add(user.getUserId());
		List<BlogUser> usersList = userDao.getUsersWhoseNotInList(Ids);		
		assertNotNull(usersList);
		assertEquals(usersList.size(), 1);
		assertNotSame(user.getUsername(), usersList.get(0).getUsername());
		assertEquals(userDao.getUsersWhoseNotInList(null).size(), 2);
		assertEquals(userDao.getUsersWhoseNotInList(new ArrayList()).size(), 2);
	}

	private BlogUser newUser(String name, String password) {
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(new Authority("ROLE_USER"));
		BlogUser user = new BlogUser(name, password, authorities, true);
		return user;
	}
}
