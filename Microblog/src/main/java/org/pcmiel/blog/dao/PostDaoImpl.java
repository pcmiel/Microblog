package org.pcmiel.blog.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pcmiel.blog.entity.BlogUser;
import org.pcmiel.blog.entity.Post;
import org.springframework.stereotype.Repository;

@Repository("postDao")
public class PostDaoImpl implements PostDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public List<Post> getAllPosts() {
		Query query = getCurrentSession().createQuery("FROM  Post");
		List<Post> postList = (List<Post>) query.list();
		return postList;
	}

	public Post getPostById(Integer id) {
		Post postResult = null;
		postResult = (Post) getCurrentSession().get(Post.class, id);
		return postResult;
	}

	public void addNewPost(Post post) {
		getCurrentSession().save(post);
	}

	public void removePost(Post post) {
		getCurrentSession().delete(post);
	}

	public List<Post> getUserPosts(BlogUser user) {
		Query query = getCurrentSession().createQuery(
				"FROM  Post WHERE user= :user");
		query.setParameter("user", user);
		List<Post> postList = (List<Post>) query.list();
		return postList;
	}

	public List<Post> getPostsByUsersId(List usersId) {
		List<Post> postList = null;
		if (usersId.size() > 0) {
			Query query = getCurrentSession().createQuery(
					"FROM  Post WHERE user.userId IN (:usersId)");
			query.setParameterList("usersId", usersId);
			postList = (List<Post>) query.list();
		} else {
			postList = new ArrayList<Post>();
		}
		return postList;
	}

}
