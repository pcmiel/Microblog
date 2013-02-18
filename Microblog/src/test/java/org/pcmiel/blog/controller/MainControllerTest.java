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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.ui.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class MainControllerTest {

	private MainController mainController;
	//private Map<String, Object> model;
	private Model model;
	
	@Before
	public void setup() {
		mainController = new MainController();
		//model = new Model();
		//model = new HashMap<String, Object>();
	}
	
	@Test
	public void readPageNumberOne() {
		assertEquals("allMessages", mainController.getMessage(model));
	}
	
	
	
}
