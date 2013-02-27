package org.pcmiel.blog.service;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcmiel.blog.entity.BlogUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml",
		"/spring-security.xml" })
@Transactional
public class UserServiceTest {

	private static final String AUTHORITY_NAME = "ROLE_TEST";
	private static final String USER_NAME2 = "test1";
	private static final String USER_NAME1 = "test2";
	
	@Resource(name = "userService")
	private UserService userService;

	@Test
	public void testGetAllUser() throws Exception {
		// given
		given2User(USER_NAME2, USER_NAME1);

		// when
		List<BlogUser> users = userService.getAllUsers();

		// then
		assertThat(users).isNotNull();
	}

	@Test
	public void testCheckThatUserExist() throws Exception {
		// given
		given2User(USER_NAME2, USER_NAME1);

		// when
		boolean existUser1 = userService.checkThatUserExist(USER_NAME1);
		boolean existUser2 = userService.checkThatUserExist("notexistsuser");

		// then
		assertThat(existUser1).isIn(true);
		assertThat(existUser2).isIn(false);
	}

	@Test
	public void testLoggetInUser() throws Exception {
		// given
		given2User(USER_NAME1, USER_NAME2);

		// when
		BlogUser user = userService.getLoggedInUser();

		// then
		assertThat(user).isNull();
	}

	public void given2User(String userName1, String userName2) {
		givenNewUser(userName1);
		givenNewUser(userName2);
	}

	private void givenNewUser(String userName) {
		List<String> authorities = newArrayList(AUTHORITY_NAME);
		userService.addNewUser(userName, userName, authorities, true);
	}
}
