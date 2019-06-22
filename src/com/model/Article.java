package com.model;

import java.util.Date;

public class Article {
	private String userId;//作者ID
	private String articleId;//文章编号
	private String artTitle;//文章标题
	private String artContent;//文章内容
	private Date postDate;//发布日期
	private Integer transmit;//转发数量
	private Integer critique;//评论数量
	private Integer likeUp;//点赞数量
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getArtTitle() {
		return artTitle;
	}
	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}
	public String getArtContent() {
		return artContent;
	}
	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public Integer getTransmit() {
		return transmit;
	}
	public void setTransmit(Integer transmit) {
		this.transmit = transmit;
	}
	public Integer getCritique() {
		return critique;
	}
	public void setCritique(Integer critique) {
		this.critique = critique;
	}
	public Integer getLikeUp() {
		return likeUp;
	}
	public void setLikeUp(Integer likeUp) {
		this.likeUp = likeUp;
	}
	
	
}
