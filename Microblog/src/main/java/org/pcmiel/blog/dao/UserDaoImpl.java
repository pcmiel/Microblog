package org.pcmiel.blog.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.pcmiel.blog.entity.BlogUser;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public BlogUser getUserByUserName(String userName) {
		BlogUser userResult = null;
		Query query = getCurrentSession().createQuery(
				"FROM  BlogUser WHERE username= :userName");
		query.setParameter("userName", userName);
		userResult = (BlogUser) query.uniqueResult();
		return userResult;
	}

	public void addNewUser(BlogUser user) {
		getCurrentSession().save(user);
	}

	public List<BlogUser> getAllUsers() {
		Query query = getCurrentSession().createQuery(
				"FROM  BlogUser ORDER BY username");
		List<BlogUser> userList = (List<BlogUser>) query.list();
		return userList;
	}

	public List<BlogUser> getUsersByIds(List usersId) {
		if (usersId.size() < 1) {
			return new ArrayList<BlogUser>();
		}
		List usersList = getCurrentSession().createCriteria(BlogUser.class)
				.add(Restrictions.in("userId", usersId))
				.addOrder(Order.asc("username")).list();
		return usersList;
	}

	public List<BlogUser> getUsersWhoseNotInList(List usersId) {
		if (usersId.size() < 1) {
			return getAllUsers();
		}
		List usersList = getCurrentSession().createCriteria(BlogUser.class)
				.add(Restrictions.not(Restrictions.in("userId", usersId)))
				.addOrder(Order.asc("username")).list();
		return usersList;
	}
}
