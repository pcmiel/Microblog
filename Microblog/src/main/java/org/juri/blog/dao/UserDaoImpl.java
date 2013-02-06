package org.juri.blog.dao;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.juri.blog.entity.Authority;
import org.juri.blog.entity.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
		return userResult;
	}

	public void addNewUser(BlogUser user) {
		logger.debug("inside dao addNewUser()");

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("FROM  Authority");
		List<Authority> allAuthorities = (List<Authority>) query.list();
		
		//addOrUpdateAuthority(user.getAuthorities());
		session.save(user);
	}

	private void addOrUpdateAuthority(Collection<GrantedAuthority> authorities) {
		logger.debug("inside dao addOrUpdateAuthority()");
		Session session = sessionFactory.getCurrentSession();
		for (GrantedAuthority grantedAuthority : authorities) {
			Authority authority  = new Authority();
			authority.setAuthority(grantedAuthority.getAuthority());
			session.save(authority);
		}
	}

}
