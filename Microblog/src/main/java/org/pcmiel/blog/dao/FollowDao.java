package org.pcmiel.blog.dao;


import java.util.List;
import java.util.Set;

import org.pcmiel.blog.entity.BlogUser;

public interface FollowDao {
	void addFollowing(BlogUser user, Set<BlogUser> following);
	void removeFollowing(BlogUser user, Set<BlogUser> following);
	List getFollowingUsersId(BlogUser user);
}
