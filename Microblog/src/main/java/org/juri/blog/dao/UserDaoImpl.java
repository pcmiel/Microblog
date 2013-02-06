package org.juri.blog.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.juri.blog.entity.UserDS;
import org.springframework.beans.factory.annotation.Autowired;
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
	public UserDS getUserByUserName(String userName) {

		UserDS userResult = null;
		List<UserDS> usersList;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM  UserDS WHERE username= '"
				+ userName + "'");
		usersList = (List<UserDS>) query.list();
		if (usersList.size() > 0) {
			userResult = usersList.get(0);
		}
		return userResult;
	}
	
	public void addNewUser(UserDS user)
	{
		logger.debug("Adding new user to database");

		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

}
