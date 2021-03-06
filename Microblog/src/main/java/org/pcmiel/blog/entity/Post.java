package org.pcmiel.blog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "POST")
public class Post implements Serializable, Comparable<Post> {

	public Post() {
	}

	public Post(String news, Date date, BlogUser user) {
		this.setNews(news);
		this.setDate(date);
		this.setUser(user);
	}

	private static final long serialVersionUID = -5527566248002296042L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NEWS")
	@Size(min = 3, max = 140, message = "Message length must be between 2 and 140")
	private String news;

	@Column(name = "DATE")
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	private BlogUser user;

	public Integer getId() {
		return id;
	}

	public BlogUser getUser() {
		return user;
	}

	public void setUser(BlogUser user) {
		this.user = user;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

	public int compareTo(Post post) {
		return post.getDate().compareTo(this.getDate());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (id == null) {
			return -1;
		}
		result = (prime * result + id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id != other.id)
			return false;
		return true;
	}
}