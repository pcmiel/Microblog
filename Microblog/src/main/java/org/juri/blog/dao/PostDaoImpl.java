package org.juri.blog.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.juri.blog.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("postDao")
@Transactional
public class PostDaoImpl implements PostDao {

	protected static Logger logger = Logger.getLogger("service");

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	public List<Post> getAllPosts() {
		logger.debug("inside dao getAllPosts()");
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM  Post");
		return (List<Post>) query.list();
	}

	public void addNewPost(Post post) {
		logger.debug("inside dao addNewPost()");
		Session session = sessionFactory.getCurrentSession();
		session.save(post);
	}

}
