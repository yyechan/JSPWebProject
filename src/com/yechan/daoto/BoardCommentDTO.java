package com.yechan.daoto;

import java.sql.Timestamp;

public class BoardCommentDTO {
	
	
	private int cNum;
	private String cId;
	private String cContent;
	private Timestamp cDate;
	private int cAvailable;
	
	
	public BoardCommentDTO() {};
	
	public BoardCommentDTO(int cNum, String cId, String cContent, Timestamp cDate, int cAvailable) {
		this.cNum = cNum;
		this.cId = cId;
		this.cContent = cContent;
		this.cDate = cDate;
		this.cAvailable = cAvailable;
	}
	
	
	public int getcNum() {
		return cNum;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public Timestamp getcDate() {
		return cDate;
	}
	public void setcDate(Timestamp cDate) {
		this.cDate = cDate;
	}
	public int getcAvailable() {
		return cAvailable;
	}
	public void setcAvailable(int cAvailable) {
		this.cAvailable = cAvailable;
	}
	

}
