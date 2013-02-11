package org.juri.blog.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.juri.blog.entity.Authority;
import org.juri.blog.entity.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
	protected static Logger logger = Logger.getLogger("service");

	// @Autowired
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	// @Transactional("hibernateTransactionManager")
	public BlogUser getUserByUserName(String userName) {

		logger.debug("inside dao getUserByUserName()");
		BlogUser userResult = null;
		List<BlogUser> usersList;
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("FROM  BlogUser WHERE username= '"
				+ userName + "'");
		usersList = (List<BlogUser>) query.list();
		if (usersList.size() > 0) {
			userResult = usersList.get(0);
		}
		//InsertTestData();
		return userResult;
	}
	
	public void InsertTestData(){
		Session session = sessionFactory.getCurrentSession();
		Set<Authority> authority = new HashSet<Authority>();
		authority.add(new Authority("test1"));
		authority.add(new Authority("test2"));
		for (Authority auth : authority) {
			session.save(auth);
		}	
		
		List<BlogUser> user = new ArrayList<BlogUser>();
		user.add(new BlogUser("test1", "test1", authority, true));
		user.add(new BlogUser("test2", "test2", authority, true));
		user.add(new BlogUser("test3", "test2", authority, true));
		user.add(new BlogUser("test4", "test2", authority, true));
		for (BlogUser blogUser : user) {
			session.save(blogUser);
		}
	}	

	public void addNewUser(BlogUser user) {
		logger.debug("inside dao addNewUser()");

		Session session = sessionFactory.getCurrentSession();
		
		//addOrUpdateAuthority(user);
		session.save(user);
	}

	

}
