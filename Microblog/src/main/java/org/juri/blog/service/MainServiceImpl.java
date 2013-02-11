package org.juri.blog.service;

import java.util.ArrayList;
import java.util.Collection;
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
		logger.debug("inside service getAllPosts()");
		List<Post> resultPosts = postDao.getAllPosts();
		return resultPosts;
	}

	public void addNewPost(String news, BlogUser user) {
		logger.debug("inside service addNewPost()");

		Post post = new Post();
		post.setNews(news);
		post.setUser(user);
		Date date = new Date();
		post.setDate(date);

		postDao.addNewPost(post);
	}

	public void addNewUser(String userName, String password,
			List<String> authorities, Boolean isEnabled) {

		logger.debug("inside service addNewUser()");

		BlogUser user = new BlogUser();
		user.setUsername(userName);
		user.setPassword(password);

		Set<Authority> authoritySet = new HashSet<Authority>();
		for (String role : authorities) {
			authoritySet.add(new Authority(role));
		}
		Authority a = new Authority();
		user.setAuthoritySet(authoritySet);
		// user.setUserAuthorities(authorities);
		user.setEnabled(isEnabled);
		userDao.addNewUser(user);
	}

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {

		logger.debug("inside service loadUserByUsername()");
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

}
