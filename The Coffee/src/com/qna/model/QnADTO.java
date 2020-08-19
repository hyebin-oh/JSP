package com.qna.model;

public class QnADTO {
	private long qnum;
	private String qsubject;
	private String qcontent;
	private String userid;
	private String qdate;
	private int qview;
	
	public long getQnum() {
		return qnum;
	}
	public void setQnum(long qnum) {
		this.qnum = qnum;
	}
	public String getQsubject() {
		return qsubject;
	}
	public void setQsubject(String qsubject) {
		this.qsubject = qsubject;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getQdate() {
		return qdate;
	}
	public void setQdate(String qdate) {
		this.qdate = qdate;
	}
	public int getQview() {
		return qview;
	}
	public void setQview(int qview) {
		this.qview = qview;
	}
	
}
