package org.pcmiel.blog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.pcmiel.blog.dao.PostDao;
import org.pcmiel.blog.dao.UserDao;
import org.pcmiel.blog.entity.BlogUser;
import org.pcmiel.blog.entity.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("testDataService")
@Transactional
public class TestDataServiceImpl implements TestDataService {
	private static final Logger logger = LoggerFactory
			.getLogger(TestDataServiceImpl.class);

	@Resource
	private UserService userService;

	@Resource
	private PostDao postDao;

	@Resource
	private UserDao userDao;

	public void InsertTestDatas() {
		int userNumber = 4;
		List<String> authorities = new ArrayList<String>();
		authorities.add("ROLE_USER");
		Set<BlogUser> users = new HashSet<BlogUser>();
		for (int i = 0; i < userNumber; i++) {
			String name = "test" + i;
			userService.addNewUser(name, name, authorities, true);
			postDao.addNewPost(new Post("It's " + name + " test post",
					new Date(), userDao.getUserByUserName(name)));
		}
	}

}
