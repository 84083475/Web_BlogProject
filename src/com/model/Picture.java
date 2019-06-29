package com.model;

public class Picture {
	private int pId;   //  --主键
    private String picturePath;   //  --图片地址
    private Integer articleId;   //  --文章Id
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer id) {
		this.articleId = id;
	}
    
}
