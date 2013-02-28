package org.pcmiel.blog.service;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcmiel.blog.dao.UserDao;
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
public class PostServiceTest {
	private static final String NEWS_1 = "news1";
	private static final String NEWS_2 = "news2";

	private static final String AUTHORITY_NAME = "ROLE_TEST";
	private static final String USER_NAME2 = "test1";
	private static final String USER_NAME1 = "test2";

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "postService")
	private PostService postService;

	@Autowired
	private UserDao userDao;

	@Test
	public void testGetAllPosts() throws Exception {
		// given
		given2UsersWith4Posts();

		// when
		List<Post> posts = postService.getAllPosts();

		// then
		assertThat(posts).isNotNull().contains(posts.get(0));
	}

	@Test
	public void testGetLoggedInUserPosts() throws Exception {
		// given
		given2UsersWith4Posts();

		// when
		List<Post> loggedInUserPosts = postService.getMyPosts();

		// then
		assertThat(loggedInUserPosts).isNotNull().isEmpty();
	}

	@Test
	public void testRemovePost() throws Exception {
		// given
		given2UsersWith4Posts();

		// when
		List<Post> posts = postService.getAllPosts();
		int postNumberBefore = posts.size();
		postService.removePost(posts.get(0).getId());
		postService.removePost(Integer.MAX_VALUE);
		int postNumberAfter = postService.getAllPosts().size();

		// then
		assertThat(postNumberAfter).isEqualTo(postNumberBefore - 1);
	}

	@Test
	public void testAddUserIfHoNotExist() throws Exception {
		// given
		given2UsersWith4Posts();

		// when
		int postNumberBefore = postService.getAllPosts().size();
		givenNewPost(NEWS_1, null);
		int postNumberAfter = postService.getAllPosts().size();

		// then
		assertThat(postNumberAfter).isEqualTo(postNumberBefore);
	}

	@Test
	public void testGetUserFollowingPosts() throws Exception {
		// given
		given2UsersWith4Posts();

		// when
		List<Post> userPosts = postService.getFollowingPosts();

		// then
		assertThat(userPosts).isNotNull().isEmpty();
	}

	private void given2UsersWith4Posts() {
		givenNewUserWith2Post(USER_NAME1, NEWS_1, NEWS_2);
		givenNewUserWith2Post(USER_NAME2, NEWS_1, NEWS_2);
	}

	private void givenNewUserWith2Post(String userNamePassword, String news1,
			String news2) {
		givenNewUser(userNamePassword);
		BlogUser user = userDao.getUserByUserName(userNamePassword);
		givenNewPost(news1, user);
		givenNewPost(news2, user);
	}

	private void givenNewPost(String news, BlogUser user) {
		postService.addNewPost(news, user);
	}

	private void givenNewUser(String userName) {
		List<String> authorities = newArrayList(AUTHORITY_NAME);
		userService.addNewUser(userName, userName, authorities, true);
	}
}
