package org.pcmiel.blog.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcmiel.blog.dao.AuthorityDao;
import org.pcmiel.blog.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml",
		"/spring-security.xml" })
@Transactional
public class AuthorityDaoTest {
	
	@Autowired
	private AuthorityDao authorityDao;

	@Before
	public void setUp() throws Exception {
		authorityDao.addNewAuthority(newAuthority("ROLE_TEST"));
	}
	
	@Test
	public void testGetAuthority() throws Exception{
		String role = "ROLE_TEST";
		Authority authority = authorityDao.getAuthority(role);
		assertNotNull(authority);
		assertEquals(authority.getAuthority(), role);
	}

	@Test
	public void testAddNewAuthority() throws Exception{
		String role = "ROLE_TEST2";
		authorityDao.addNewAuthority(newAuthority(role));
		Authority authority = authorityDao.getAuthority(role);
		assertNotNull(authority);
		assertEquals(authority.getAuthority(), role);
	}
	
	private Authority newAuthority(String name){
		return new Authority(name);
	}
}
