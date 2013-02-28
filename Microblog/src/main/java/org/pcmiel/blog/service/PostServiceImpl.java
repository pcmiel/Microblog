package org.pcmiel.blog.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.pcmiel.blog.dao.FollowDao;
import org.pcmiel.blog.dao.PostDao;
import org.pcmiel.blog.entity.BlogUser;
import org.pcmiel.blog.entity.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("postService")
@Transactional
public class PostServiceImpl implements PostService {

	private static final Logger logger = LoggerFactory
			.getLogger(MainServiceImpl.class);

	@Resource
	private PostDao postDao;

	@Resource
	private FollowDao followDao;

	@Resource
	private UserService userService;

	public List<Post> getAllPosts() {
		List<Post> resultPosts = postDao.getAllPosts();
		return resultPosts;
	}

	public List<Post> getFollowingPosts() {
		BlogUser user = userService.getLoggedInUser();
		if (user == null) {
			return new ArrayList<Post>();
		}
		List followingUsersID = followDao.getFollowingUsersId(user);
		List<Post> followingPosts = postDao.getPostsByUsersId(followingUsersID);
		if (followingPosts == null) {
			followingPosts = getMyPosts();
		} else {
			followingPosts.addAll(getMyPosts());
		}
		Collections.sort(followingPosts);
		return followingPosts;
	}

	public List<Post> getMyPosts() {
		BlogUser user = userService.getLoggedInUser();
		return postDao.getUserPosts(user);
	}

	public void addNewPost(String news, BlogUser user) {
		if (user == null) {
			logger.warn("User not exist");
			return;
		}
		Date date = new Date();
		Post post = new Post(news, date, user);
		postDao.addNewPost(post);
	}

	public void removePost(Integer id) {
		Post post = postDao.getPostById(id);
		if (post != null) {
			postDao.removePost(post);
		}
	}
}
