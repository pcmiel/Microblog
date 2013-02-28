package org.pcmiel.blog.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.pcmiel.blog.dao.PostDao;
import org.pcmiel.blog.dao.UserDao;
import org.pcmiel.blog.entity.Authority;
import org.pcmiel.blog.entity.BlogUser;
import org.pcmiel.blog.entity.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("mainService")
@Transactional
public class MainServiceImpl implements MainService, UserDetailsService {
	private static final Logger logger = LoggerFactory
			.getLogger(MainServiceImpl.class);

	@Resource
	private UserService userService;

	@Resource
	private PostDao postDao;

	@Resource
	private UserDao userDao;

	public void InsertTestDatas() {
		int userNumber = 4;
		List<String> authorities = new ArrayList<String>();
		authorities.add("ROLE_USER");
		Set<BlogUser> users = new HashSet<BlogUser>();
		for (int i = 0; i < userNumber; i++) {
			String name = "test" + i;
			userService.addNewUser(name, name, authorities, true);
			postDao.addNewPost(new Post("testtestest " + name, new Date(),
					userDao.getUserByUserName(name)));
		}
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
}
