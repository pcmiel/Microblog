package org.pcmiel.blog.dao;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Test
	public void shouldPostDaoBeNotNull() throws Exception {
		assertThat(postDao).isNotNull();
	}

	@Test
	public void testGetAllPosts() throws Exception {
		// given
		givenUserWith2Post("test1", "news1", "news2");

		// when
		List<Post> postList = postDao.getAllPosts();

		// then
		assertThat(postList).isNotNull().hasSize(2);
	}

	@Test
	public void testGetUserPosts() throws Exception {
		// given
		BlogUser user = givenUserWith2Post("test1", "news1", "news2");

		// when
		List<Post> userPosts = postDao.getUserPosts(user);
		List<Post> allPosts = postDao.getAllPosts();

		// then
		assertThat(userPosts).isNotNull().hasSize(2).isIn(allPosts);
	}

	@Test
	public void testGetPostById() throws Exception {
		// given
		BlogUser user = givenUserWith2Post("test", "news1", "news2");
		Post post = givenNewPost("news3", user);

		// when
		Post testPost = postDao.getPostById(post.getId());

		// then
		assertThat(testPost).isNotNull().isEqualTo(post);
	}

	@Test
	public void testRemovePost() throws Exception {
		// given
		BlogUser user = givenUserWith2Post("test", "news1", "news2");
		Post post = givenNewPost("news3", user);

		// when
		List<Post> postList = postDao.getAllPosts();
		int size = postList.size();
		postDao.removePost(post);
		postList = postDao.getAllPosts();

		// then
		assertThat(postList).isNotNull().hasSize(size - 1);
	}

	@Test
	public void testGetPostsByUsersId() throws Exception {

		// given
		BlogUser user1 = givenUserWith2Post("test", "news1", "news2");
		BlogUser user2 = givenNewUser("test1", "test1");

		// when
		List<Integer> usersId = new ArrayList<Integer>();
		usersId.add(user1.getUserId());
		usersId.add(user2.getUserId());

		List<Post> usersPosts = postDao.getPostsByUsersId(usersId);
		List<Post> allPosts = postDao.getAllPosts();
		List<Post> zeroPosts = postDao.getPostsByUsersId(new ArrayList<Post>());

		// then
		assertThat(usersPosts).isNotNull().isIn(allPosts);
		assertThat(zeroPosts).isNotNull().isEmpty();
	}

	private BlogUser givenUserWith2Post(String namePassword, String news1,
			String news2) {
		BlogUser user = givenNewUser(namePassword, namePassword);
		givenNewPost(news1, user);
		givenNewPost(news2, user);
		return user;
	}

	private BlogUser givenNewUser(String name, String password) {
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(new Authority("ROLE_USER"));
		BlogUser user = new BlogUser(name, password, authorities, true);
		userDao.addNewUser(user);
		return user;
	}

	private Post givenNewPost(String news, BlogUser user) {
		Post post = new Post(news, new Date(), user);
		postDao.addNewPost(post);
		return post;
	}

}
