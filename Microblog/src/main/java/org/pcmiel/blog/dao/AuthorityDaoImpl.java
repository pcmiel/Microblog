package org.pcmiel.blog.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pcmiel.blog.entity.Authority;
import org.springframework.stereotype.Repository;

@Repository("authorityDao")
public class AuthorityDaoImpl implements AuthorityDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void addNewAuthority(Authority authority) {
		getCurrentSession().save(authority);
	}

	public Authority getAuthority(String authorityName) {
		Authority authority = null;
		Query query = getCurrentSession()
				.createQuery(
						"FROM  Authority WHERE authority= :authorityName ORDER BY authority DESC");
		query.setParameter("authorityName", authorityName);
		authority = (Authority) query.uniqueResult();
		return authority;
	}

}
