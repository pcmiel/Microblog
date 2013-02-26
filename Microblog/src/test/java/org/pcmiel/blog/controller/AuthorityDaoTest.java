package org.pcmiel.blog.controller;

import static org.fest.assertions.api.Assertions.assertThat;

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

	@Test
	public void testAuthority() throws Exception {
		// given
		String role = "ROLE_TEST";
		givenNewAuthority(role);

		// when
		Authority authority = authorityDao.getAuthority(role);

		// then
		assertThat(authority).isNotNull();
		assertThat(role).isEqualTo(authority.getAuthority());
	}

	private Authority givenNewAuthority(String name) {
		Authority authority = new Authority(name);
		authorityDao.addNewAuthority(authority);
		return authority;
	}
}
