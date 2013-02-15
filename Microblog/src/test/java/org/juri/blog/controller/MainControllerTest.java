package org.juri.blog.controller;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.ui.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class MainControllerTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Test
	public void shouldSessionFactoryBeInjected() throws Exception {
		// given
		// when
		// then
		//assertNotNull(sessionFactory);
	}

}
