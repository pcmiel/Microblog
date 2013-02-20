package org.pcmiel.blog.dao;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		Query query = getCurrentSession().createQuery("FROM  BlogUser WHERE username= :userName");
		query.setParameter("userName", userName);
		userResult = (BlogUser) query.uniqueResult();		
		return userResult;
	}

	public void addNewUser(BlogUser user) {
		getCurrentSession().save(user);
	}

	public List<BlogUser> getAllUsers() {
		Query query = getCurrentSession().createQuery("FROM  BlogUser");
		List<BlogUser> userList = (List<BlogUser>) query.list();
		return userList;
	}
}
