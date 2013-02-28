package org.pcmiel.blog.dao;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pcmiel.blog.entity.BlogUser;
import org.springframework.stereotype.Repository;

@Repository("followDao")
public class FollowDaoImpl implements FollowDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void addFollowing(BlogUser user, Set<BlogUser> following) {
		Set<BlogUser> actualFollowing = user.getFollowing();
		actualFollowing.addAll(following);
		user.setFollowing(actualFollowing);
		getCurrentSession().save(user);
	}

	public void removeFollowing(BlogUser user, Set<BlogUser> following) {
		Set<BlogUser> actualFollowing = user.getFollowing();
		actualFollowing.removeAll(following);
		user.setFollowing(actualFollowing);
		getCurrentSession().save(user);
	}

	public List<Integer> getFollowingUsersId(BlogUser user) {
		Query query = getCurrentSession().createSQLQuery(
				"SELECT FOLLOWING_ID FROM FOLLOWING WHERE USER_ID = '"
						+ user.getUserId() + "'");
		List<Integer> usersId = (List<Integer>) query.list();
		return usersId;
	}

}
