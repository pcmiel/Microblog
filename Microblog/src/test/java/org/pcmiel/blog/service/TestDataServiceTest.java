package org.pcmiel.blog.service;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcmiel.blog.dao.PostDao;
import org.pcmiel.blog.dao.UserDao;
import org.pcmiel.blog.entity.BlogUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml",
		"/spring-security.xml" })
@Transactional
public class TestDataServiceTest {

	@Resource
	private UserService userService;

	@Resource
	private TestDataService testDataService;

	@Resource
	private PostDao postDao;

	@Resource
	private UserDao userDao;

	@Test
	public void testInsertTestData() throws Exception {
		// given
		testDataService.InsertTestDatas();

		// when
		List<BlogUser> users = userService.getAllUsers();

		// then
		assertThat(users).isNotNull().hasSize(4);
	}

}
