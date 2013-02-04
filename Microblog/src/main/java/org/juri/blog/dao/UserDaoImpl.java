package org.juri.blog.dao;

import javax.annotation.Resource;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.juri.blog.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Resource
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional("hibernateTransactionManager")
	public User getUserByUserName(String userName) {
		Query queryResult;
		queryResult = getCurrentSession().createQuery(
				"from USER where USERNAME=:userName");
		queryResult.setParameter("userName", new String(userName));
		return (User) queryResult.list().get(0);
	}
}
