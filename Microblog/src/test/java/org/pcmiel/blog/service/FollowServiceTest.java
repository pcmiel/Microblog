package org.pcmiel.blog.service;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.*;
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
public class FollowServiceTest {
	private static final String AUTHORITY_NAME = "ROLE_TEST";
	private static final String USER_NAME2 = "test1";
	private static final String USER_NAME1 = "test2";
	
	@Resource(name = "followService")
	private FollowService followService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Test
	public void testGetFollowingUsers() throws Exception{
		//given
		given2User(USER_NAME1, USER_NAME2);
		givenNewFollowing(USER_NAME2, USER_NAME1);
		
		//when
		List<BlogUser> nullUserFollowing = followService.getFollowing(null);
		List<BlogUser> userWithoutFollowing = followService.getFollowing(USER_NAME1);
		List<BlogUser> userWithFollowing = followService.getFollowing(USER_NAME2);
		
		//then
		assertThat(nullUserFollowing).isNotNull().isEmpty();
		assertThat(userWithoutFollowing).isNotNull().isEmpty();
	}
	
	@Test
	public void testGetUnollowingUsers() throws Exception{
		//given
		given2User(USER_NAME1, USER_NAME2);
		givenNewFollowing(USER_NAME2, USER_NAME1);
		givenNewFollowing(USER_NAME1, USER_NAME1);
		//when
		List<BlogUser> nullUserFollowing = followService.getUnfollowing(null);
		List<BlogUser> userWithoutFollowing = followService.getUnfollowing(USER_NAME1);
		List<BlogUser> userWithFollowing = followService.getUnfollowing(USER_NAME2);
		
		//then
		assertThat(nullUserFollowing).isNotNull().isEmpty();
		assertThat(userWithoutFollowing).isNotNull().isNotEmpty();
	}
	
	@Test
	public void testRemoveFollowingUsers() throws Exception{
		//given
		given2User(USER_NAME1, USER_NAME2);
		givenNewFollowing(USER_NAME2, USER_NAME1);

		//when
		followService.removeFollowing(USER_NAME2, USER_NAME1);
		List<BlogUser> followingUsers = followService.getFollowing(USER_NAME2);
		
		//then
		assertThat(followingUsers).isNotNull().isEmpty();
	}
 

	public void given2User(String userName1, String userName2) {
		givenNewUser(userName1);
		givenNewUser(userName2);
	}
	
	private void givenNewUser(String userName) {
		List<String> authorities = newArrayList(AUTHORITY_NAME);
		userService.addNewUser(userName, userName, authorities, true);
	}

	private void givenNewFollowing(String userName, String followUserName) {
		followService.addFollowing(userName, followUserName);
	}
}
