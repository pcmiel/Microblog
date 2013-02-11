package org.juri.blog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POST")
public class Post implements Serializable{
	
	private static final long serialVersionUID = -5527566248002296042L;
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="NEWS")
	private String news;
	
	@Column(name="DATE")
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}