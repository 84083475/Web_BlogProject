package com.model;

import java.util.Date;

public class CInfo {
	private String userId;  //用户id
    private String cId;    //文章id
    private String cName;  //文章名字
    private String cText;  //文章内容
    private Date cDate;  //发布日期
    private int cReply;  //回复数量
    private int cPrise;  //点赞数量
    private int cTransmit;//转发数量
    private int cStatus; //点赞状态  0--该用户没有给该id点赞   1--该用户给该id点赞了
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcText() {
		return cText;
	}
	public void setcText(String cText) {
		this.cText = cText;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	public int getcReply() {
		return cReply;
	}
	public void setcReply(int cReply) {
		this.cReply = cReply;
	}
	public int getcPrise() {
		return cPrise;
	}
	public void setcPrise(int cPrise) {
		this.cPrise = cPrise;
	}
	public int getcTransmit() {
		return cTransmit;
	}
	public void setcTransmit(int cTransmit) {
		this.cTransmit = cTransmit;
	}
	public int getcStatus() {
		return cStatus;
	}
	public void setcStatus(int cStatus) {
		this.cStatus = cStatus;
	}
    
}
