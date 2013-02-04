package org.juri.blog.service;

import org.juri.blog.entity.Message;

import java.util.List;

import javax.annotation.Resource;

//import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("messageService")
@Transactional
public class MessageService {
	//protected static Logger logger = Logger.getLogger("service");

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public List<Message> getAll() {
		//logger.debug("Retrieving all messages");

		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("FROM  Message");

		return query.list();
	}

	public void add(String title, String news, Integer authorId) {
		//logger.debug("Adding new person");

		Session session = sessionFactory.getCurrentSession();

		Message message = new Message();
		message.setTitle(title);
		message.setNews(news);
		message.setAuthorId(authorId);
		message.setDate("11/11/2011");

		session.save(message);
	}
}