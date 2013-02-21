package org.pcmiel.blog.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcmiel.blog.dao.UserDao;
import org.pcmiel.blog.entity.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/spring-security.xml" })
public class MainControllerTest {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	UserDao user;	

	@Before
	public void setUp() {
		//user.addNewAuthority(new Authority("test"));
	}
	
	@Test
	public void shouldUserBeNotNull() throws Exception{
		
		
		assertNotNull(user);
	}
	
	@Test
	public void shouldSessionFactoryBeInjected() throws Exception {
	     assertNotNull(sessionFactory);
	}	
}
