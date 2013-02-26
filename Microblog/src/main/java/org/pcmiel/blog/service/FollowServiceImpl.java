package org.pcmiel.blog.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.pcmiel.blog.dao.FollowDao;
import org.pcmiel.blog.dao.PostDao;
import org.pcmiel.blog.dao.UserDao;
import org.pcmiel.blog.entity.BlogUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("followService")
@Transactional
public class FollowServiceImpl implements FollowService {

	private static final Logger logger = LoggerFactory
			.getLogger(MainServiceImpl.class);

	@Resource
	private FollowDao followDao;

	@Resource
	private UserDao userDao;

	public List<BlogUser> getFollowing(String username) {
		BlogUser user = userDao.getUserByUserName(username);
		List followingUsersId = followDao.getFollowingUsersId(user);
		List<BlogUser> followingUsers = userDao.getUsersByIds(followingUsersId);
		if (followingUsers == null || followingUsers.size() == 0) {
			return new ArrayList<BlogUser>();
		}
		followingUsers.remove(user);
		Collections.sort(followingUsers);
		return followingUsers;
	}

	public List<BlogUser> getUnfollowing(String username) {
		BlogUser user = userDao.getUserByUserName(username);
		List followingUsersId = followDao.getFollowingUsersId(user);
		List<BlogUser> unfollowingUsers = userDao
				.getUsersWhoseNotInList(followingUsersId);
		if (unfollowingUsers == null || unfollowingUsers.size() == 0) {
			return new ArrayList<BlogUser>();
		}
		unfollowingUsers.remove(user);
		Collections.sort(unfollowingUsers);
		return unfollowingUsers;
	}

	public void addFollowing(String username, String followUsername) {
		if (username.equals(followUsername)) {
			logger.warn("User can't follow himself");
			return;
		}
		Set<BlogUser> follow = new HashSet<BlogUser>();
		follow.add(userDao.getUserByUserName(followUsername));
		BlogUser user = userDao.getUserByUserName(username);
		followDao.addFollowing(user, follow);
	}

	public void removeFollowing(String username, String unfollowUsername) {
		Set<BlogUser> unfollow = new HashSet<BlogUser>();
		unfollow.add(userDao.getUserByUserName(unfollowUsername));
		followDao
				.removeFollowing(userDao.getUserByUserName(username), unfollow);
	}

}
