package com.yechan.daoto;

import java.sql.Timestamp;

public class BoardDTO {

	private int bNum;
	private String bTitle;
	private String bId;
	private Timestamp bDate;
	private String bContent;
	private int bAvailable;
	
	public BoardDTO() {}
	
	public BoardDTO(int bNum, String bTitle, String bId, Timestamp bDate, String bContent, int bAvailable) {
		
		this.bNum = bNum;
		this.bTitle = bTitle;
		this.bId = bId;
		this.bDate = bDate;
		this.bContent = bContent;
		this.bAvailable = bAvailable;
	}


	
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public Timestamp getbDate() {
		return bDate;
	}
	public void setbDate(Timestamp bDate) {
		this.bDate = bDate;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public int getbAvailable() {
		return bAvailable;
	}
	public void setbAvailable(int bAvailable) {
		this.bAvailable = bAvailable;
	}
	
	
	
	
}
