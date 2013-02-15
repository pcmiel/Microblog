package org.juri.blog.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.juri.blog.dao.PostDao;
import org.juri.blog.dao.UserDao;
import org.juri.blog.entity.Authority;
import org.juri.blog.entity.Post;
import org.juri.blog.entity.BlogUser;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

	protected static Logger logger = Logger.getLogger("service");

	@Resource
	private PostDao postDao;

	@Resource
	private UserDao userDao;

	public List<Post> getAllPosts() {
		List<Post> resultPosts = postDao.getAllPosts();
		Collections.sort(resultPosts);
		return resultPosts;
	}

	public List<Post> getFollowingPosts() {
		BlogUser user = getLoggedInUser();
		List<Post> allPosts = getAllPosts();
		Set<BlogUser> following = user.getFollowing();
		List<Post> resultPosts = new ArrayList<Post>();
		for (Post post : allPosts) {
			if (following.contains(post.getUser())) {
				resultPosts.add(post);
			}
		}
		return resultPosts;
	}

	public List<Post> getMyPosts() {
		BlogUser user = getLoggedInUser();
		List<Post> allPosts = getAllPosts();
		List<Post> resultPosts = new ArrayList<Post>();
		for (Post post : allPosts) {
			if ((post.getUser().getUsername()).equals(user.getUsername())) {
				resultPosts.add(post);
			}
		}
		return resultPosts;
	}

	public void addNewPost(String news, BlogUser user) {

		Post post = new Post();
		post.setNews(news);
		post.setUser(user);
		Date date = new Date();
		post.setDate(date);

		postDao.addNewPost(post);
	}

	public void removePost(int id) {
		Post post = postDao.getPostById(id);
		if(post != null){
			postDao.removePost(post);
		}
	}

	public void addNewUser(String userName, String password,
			List<String> authorities, Boolean isEnabled) {
		Set<Authority> authoritySet = new HashSet<Authority>();
		for (String role : authorities) {
			Authority authority = userDao.getAuthority(role);
			if (authority == null) {
				userDao.addNewAuthority(new Authority(role));
				authority = userDao.getAuthority(role);
			}
			if (authority != null) {
				authoritySet.add(authority);
			}
		}
		BlogUser user = new BlogUser(userName, password, authoritySet,
				isEnabled);
		userDao.addNewUser(user);
		addFollowing(user.getUsername(), user.getUsername());
	}

	public void addNewAuthority(String authority) {
		userDao.addNewAuthority(new Authority(authority));
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

	public Boolean checkIfUsernameExist(String username) {
		BlogUser user = userDao.getUserByUserName(username);
		if (user == null) {
			return false;
		}
		return true;
	}

	public BlogUser getLoggedInUser() {
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return userDao.getUserByUserName(user.getUsername());
	}

	public void InsertTestDatas() {
		int userNumber = 4;

		// authorties
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(new Authority("ROLE_USER"));
		authorities.add(new Authority("ROLE_ADMIN"));

		// users
		Set<BlogUser> users = new HashSet<BlogUser>();
		for (int i = 0; i < userNumber; i++) {
			String name = "test" + i;
			users.add(new BlogUser(name, name, authorities, true));
		}
		for (BlogUser blogUser : users) {
			userDao.addNewUser(blogUser);
			postDao.addNewPost(new Post(
					"testtestest " + blogUser.getUsername(), new Date(),
					blogUser));
			postDao.addNewPost(new Post("t " + blogUser.getUsername(),
					new Date(), blogUser));
			userDao.addFollowing(blogUser, users);
		}
	}

	public List<BlogUser> getFollowing(String username) {
		BlogUser user = userDao.getUserByUserName(username);
		Set<BlogUser> setUser = user.getFollowing();
		List<BlogUser> users = new ArrayList<BlogUser>();		
		for (BlogUser blogUser : setUser) {
			users.add(blogUser);
		}
		users.remove(user);
		Collections.sort(users);
		return users;
	}

	public List<BlogUser> getUnfollowing(String username) {
		BlogUser user = userDao.getUserByUserName(username);
		Set<BlogUser> setUser = user.getFollowing();
		List<BlogUser> unfollowing = getAllUsers();
		List<BlogUser> listUsers = new ArrayList<BlogUser>();
		for (BlogUser blogUser : setUser) {
			listUsers.add(blogUser);
		}
		unfollowing.removeAll(listUsers);
		Collections.sort(unfollowing);
		return unfollowing;
	}

	public void addFollowing(String username, String followUsername) {
		Set<BlogUser> follow = new HashSet<BlogUser>();
		follow.add(userDao.getUserByUserName(followUsername));
		BlogUser user = userDao.getUserByUserName(username);
		userDao.addFollowing(user, follow);
	}

	public void removeFollowing(String username, String unfollowUsername) {
		Set<BlogUser> unfollow = new HashSet<BlogUser>();
		unfollow.add(userDao.getUserByUserName(unfollowUsername));

		userDao.removeFollowing(userDao.getUserByUserName(username), unfollow);
	}
}
