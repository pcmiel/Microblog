package org.pcmiel.blog.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		return (List<Post>) query.list();
	}

	public Post getPostById(int id) {
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

}
