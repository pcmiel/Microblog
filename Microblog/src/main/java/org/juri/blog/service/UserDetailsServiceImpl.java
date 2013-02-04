package org.juri.blog.service;

import org.juri.blog.dao.UserDao;
import org.juri.blog.entity.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;


@Repository("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Resource
	private UserDao userDao;

	public UserDetailsServiceImpl() {
	}

	public UserDetailsServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		User user;
		List<String> authorityList = new ArrayList<String>();
		String authority;
		try {
			user = userDao.getUserByUserName(userName);
		} catch (Exception e) {
			throw new UsernameNotFoundException(
					"getUserByUserName returned null.");
		}
		Set<Authority> authoritySet = user.getAuthoritySet();
		Iterator<Authority> authoritySetIterator = authoritySet.iterator();
		while (authoritySetIterator.hasNext()) {
			authority = authoritySetIterator.next().getAuthority();
			authorityList.add(authority);
		}
		user.setUserAuthorities(authorityList);
		return (UserDetails) user;
	}
}
