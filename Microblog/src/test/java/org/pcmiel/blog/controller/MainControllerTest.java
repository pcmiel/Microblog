package org.pcmiel.blog.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcmiel.blog.controller.MainController;
import org.pcmiel.blog.dao.UserDao;
import org.pcmiel.blog.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.ui.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/spring-security.xml" })
public class MainControllerTest {

	@Autowired
	SessionFactory sessionFactory;
	
	//@Autowired
	//MainService service;
	
	UserDao user;
	

	@Before
	public void setUp() {
		user.addNewAuthority(new Authority("test"));
	}
	
	@Test
	public void shouldSessionFactoryBeInjected() throws Exception {
	     assertNotNull(sessionFactory);
	}
	
	
	
}
