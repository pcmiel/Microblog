package org.pcmiel.blog.dao;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
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

	@Test
	public void shouldUserDaoBeNotNull() throws Exception {
		assertThat(userDao).isNotNull();
	}

	@Test
	public void TestGetUserByUserName() throws Exception {
		// given
		String namePassword1 = "test1";
		String namePassword2 = "test2";
		List<BlogUser> users = given2Users(namePassword1, namePassword2);

		// when
		BlogUser user1 = userDao.getUserByUserName(namePassword1);
		BlogUser user2 = userDao.getUserByUserName(namePassword2);

		// then
		assertThat(user1).isNotNull().isIn(users).isNotEqualTo(user2);
	}

	@Test
	public void TestGetUsersWhoseNotInList() throws Exception {
		// given
		String namePasswoard1 = "test1";
		String namePassword2 = "test2";
		String namePassword3 = "test3";
		String namePassword4 = "test4";
		List<BlogUser> users = given2Users(namePasswoard1, namePassword2);
		BlogUser user3 = givenNewUser(namePassword3, namePassword3);
		BlogUser user4 = givenNewUser(namePassword4, namePassword4);
		List<Integer> usersIds = newArrayList(user3.getUserId(),
				user4.getUserId());

		// when
		List<BlogUser> allUsers = userDao.getAllUsers();
		List<BlogUser> testUsers = userDao.getUsersWhoseNotInList(usersIds);
		List<BlogUser> emptyList = userDao
				.getUsersWhoseNotInList(new ArrayList<Integer>());

		// then
		assertThat(testUsers).isNotNull().containsAll(users);
		assertThat(emptyList).isNotNull().hasSize(allUsers.size());
	}

	@Test
	public void TestGetAllUsers() throws Exception {
		// given
		String namePassword1 = "test1";
		String namePassword2 = "test2";
		List<BlogUser> users = given2Users(namePassword1, namePassword2);

		// when
		List<BlogUser> allUsers = userDao.getAllUsers();

		// then
		assertThat(allUsers).isNotNull().containsAll(users);
	}

	@Test
	public void TestGetUsersById() throws Exception {
		// given
		String namePassword1 = "test1";
		String namePassword2 = "test2";
		given2Users(namePassword1, namePassword2);

		// when
		List<BlogUser> allUsers = userDao.getAllUsers();
		List<Integer> usersIds = new ArrayList<Integer>();
		usersIds.add(allUsers.get(0).getUserId());
		usersIds.add(allUsers.get(1).getUserId());
		List<BlogUser> users = userDao.getUsersByIds(usersIds);
		List<BlogUser> emptyList = userDao
				.getUsersByIds(new ArrayList<Integer>());
		// then
		assertThat(users).isNotNull().isIn(allUsers);
		assertThat(emptyList).isNotNull().isEmpty();
	}

	private List<BlogUser> given2Users(String namePassword1,
			String namePassword2) {
		List<BlogUser> users = new ArrayList<BlogUser>();
		users.add(givenNewUser(namePassword1, namePassword1));
		users.add(givenNewUser(namePassword2, namePassword2));
		return users;
	}

	private BlogUser givenNewUser(String name, String password) {
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(new Authority("ROLE_USER"));
		BlogUser user = new BlogUser(name, password, authorities, true);
		userDao.addNewUser(user);
		return user;
	}
}
