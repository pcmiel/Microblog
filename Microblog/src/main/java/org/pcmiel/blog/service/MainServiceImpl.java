package org.pcmiel.blog.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.pcmiel.blog.dao.AuthorityDao;
import org.pcmiel.blog.dao.FollowDao;
import org.pcmiel.blog.dao.PostDao;
import org.pcmiel.blog.dao.UserDao;
import org.pcmiel.blog.entity.Authority;
import org.pcmiel.blog.entity.BlogUser;
import org.pcmiel.blog.entity.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mainService")
@Transactional
public class MainServiceImpl implements MainService, UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(MainServiceImpl.class);
	
	@Resource
	private PostDao postDao;

	@Resource
	private UserDao userDao;

	@Resource
	private AuthorityDao authorityDao;

	@Resource
	private FollowDao followDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Post> getAllPosts() {
		List<Post> resultPosts = postDao.getAllPosts();
		// Collections.sort(resultPosts);
		return resultPosts;
	}

	public List<Post> getFollowingPosts() {
		BlogUser user = getLoggedInUser();
		List followingUsersID = followDao.getFollowingUsersId(user);
		List<Post> followingPosts = postDao.getPostsByUsersId(followingUsersID);
		if (followingPosts == null) {
			followingPosts = getMyPosts();
		} else {
			followingPosts.addAll(getMyPosts());
		}
		Collections.sort(followingPosts);
		return followingPosts;
	}

	public List<Post> getMyPosts() {
		BlogUser user = getLoggedInUser();
		return postDao.getUserPosts(user);
	}

	public void addNewPost(String news, BlogUser user) {
		if (user == null) {
			logger.warn("User not exist");
			return;
		}
		Date date = new Date();
		Post post = new Post(news, date, user);
		postDao.addNewPost(post);
	}

	public void removePost(Integer id) {
		Post post = postDao.getPostById(id);
		if (post != null) {
			postDao.removePost(post);
		}
	}

	public void addNewUser(String userName, String password,
			List<String> authorities, Boolean isEnabled) {

		if (checkThatUserExist(userName)) {
			logger.warn(userName + " actual exist");
			return;
		}
		password = passwordEncoder.encodePassword(password, null);
		Set<Authority> authoritySet = new HashSet<Authority>();
		for (String role : authorities) {
			Authority authority = authorityDao.getAuthority(role);
			if (authority == null) {
				addNewAuthority(role);
				authority = authorityDao.getAuthority(role);
			}
			if (authority != null) {
				authoritySet.add(authority);
			}
		}
		BlogUser user = new BlogUser(userName, password, authoritySet,
				isEnabled);
		userDao.addNewUser(user);
	}

	public void addNewAuthority(String authorityName) {
		Authority authority = new Authority(authorityName);
		authorityDao.addNewAuthority(authority);
	}

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		BlogUser user = userDao.getUserByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User for username " + userName
					+ "was not found.");
		}
		Collection<Authority> authorityList = user.getAuthoritySet();
		Set<GrantedAuthorityImpl> authorities = new HashSet<GrantedAuthorityImpl>();
		for (Authority auth : authorityList) {
			if (auth.getAuthority() != null)
				authorities.add(new GrantedAuthorityImpl(auth.getAuthority()));
		}
		return new User(user.getUsername(), user.getPassword(), true, true,
				true, true, authorities);
	}

	public List<BlogUser> getAllUsers() {
		List<BlogUser> result = userDao.getAllUsers();
		Collections.sort(result);
		return result;
	}

	public Boolean checkThatUserExist(String username) {
		BlogUser user = userDao.getUserByUserName(username);
		return user != null;
	}

	public BlogUser getLoggedInUser() {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return userDao.getUserByUserName(user.getUsername());
	}

	public void InsertTestDatas() {
		int userNumber = 4;

		// authorties
		List<String> authorities = new ArrayList<String>();
		authorities.add("ROLE_USER");

		// users
		Set<BlogUser> users = new HashSet<BlogUser>();
		for (int i = 0; i < userNumber; i++) {
			String name = "test" + i;
			addNewUser(name, name, authorities, true);
			postDao.addNewPost(new Post("testtestest " + name, new Date(),
					userDao.getUserByUserName(name)));
		}
	}

	public List<BlogUser> getFollowing(String username) {
		BlogUser user = userDao.getUserByUserName(username);
		List followingUsersId = followDao.getFollowingUsersId(user);
		List<BlogUser> followingUsers = userDao.getUsersById(followingUsersId);
		if (followingUsers == null || followingUsers.size() == 0) {
			return new ArrayList<BlogUser>();
		}
		followingUsers.remove(user);
		Collections.sort(followingUsers);
		return followingUsers;
	}

	public List<BlogUser> getUnfollowing(String username) {
		BlogUser user = userDao.getUserByUserName(username);
		List followingUsersId = followDao.getFollowingUsersId(user);
		List<BlogUser> unfollowingUsers = userDao
				.getUsersWhoseNotInList(followingUsersId);
		if (unfollowingUsers == null || unfollowingUsers.size() == 0) {
			return new ArrayList<BlogUser>();
		}
		unfollowingUsers.remove(user);
		Collections.sort(unfollowingUsers);
		return unfollowingUsers;
	}

	public void addFollowing(String username, String followUsername) {
		if (username.equals(followUsername)) {
			logger.warn("User can't follow himself");
			return;
		}
		Set<BlogUser> follow = new HashSet<BlogUser>();
		follow.add(userDao.getUserByUserName(followUsername));
		BlogUser user = userDao.getUserByUserName(username);
		followDao.addFollowing(user, follow);
	}

	public void removeFollowing(String username, String unfollowUsername) {
		Set<BlogUser> unfollow = new HashSet<BlogUser>();
		unfollow.add(userDao.getUserByUserName(unfollowUsername));
		followDao
				.removeFollowing(userDao.getUserByUserName(username), unfollow);
	}
}
