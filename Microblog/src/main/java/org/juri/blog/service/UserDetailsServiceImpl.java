package org.juri.blog.service;

import org.apache.log4j.Logger;
import org.juri.blog.dao.UserDao;
import org.juri.blog.entity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsServiceImpl")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource
	private UserDao userDao;

	public UserDetailsServiceImpl() {
		logger.info("construct. 0");
	}

	public UserDetailsServiceImpl(UserDao userDao) {
		logger.info("construct. 1");
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {

		UserDS user = userDao.getUserByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("User for username " + userName
					+ "was not found.");
		}
		List<String> permissions;
		Set<GrantedAuthorityImpl> authorities = new HashSet<GrantedAuthorityImpl>();
		authorities.add(new GrantedAuthorityImpl("ROLE_MEMBER"));
		return new User(user.getUsername(), user.getPassword(), true, true,
				true, true, authorities);
	}
	
	public void addNewUser(String name, String password, List<String> authorities, Boolean isEnabled)
	{
		UserDS user = new UserDS();
		user.setUsername(name);
		user.setPassword(password);
		user.setUserAuthorities(authorities);
		user.setEnabled(isEnabled);
		userDao.addNewUser(user);
	}

}
