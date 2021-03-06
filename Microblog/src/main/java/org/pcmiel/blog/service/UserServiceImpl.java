package org.pcmiel.blog.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.pcmiel.blog.dao.AuthorityDao;
import org.pcmiel.blog.dao.UserDao;
import org.pcmiel.blog.entity.Authority;
import org.pcmiel.blog.entity.BlogUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Resource
	private UserDao userDao;

	@Resource
	private AuthorityDao authorityDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

	public List<BlogUser> getAllUsers() {
		List<BlogUser> result = userDao.getAllUsers();
		return result;
	}

	public Boolean checkThatUserExist(String username) {
		BlogUser user = userDao.getUserByUserName(username);
		return user != null;
	}

	public BlogUser getLoggedInUser() {
		Authentication authentication = getLoggedInAuthentication();
		if (authentication == null) {
			return null;
		}
		User user = (User) authentication.getPrincipal();
		return userDao.getUserByUserName(user.getUsername());
	}

	private Authentication getLoggedInAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
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
}
