package com.notice.model;

public class NoticeDTO {
	private long nnum;
	private String nsubject;
	private String ncontent;
	private String ndate;
	private String nview;
	
	public long getNnum() {
		return nnum;
	}
	public void setNnum(long nnum) {
		this.nnum = nnum;
	}
	public String getNsubject() {
		return nsubject;
	}
	public void setNsubject(String nsubject) {
		this.nsubject = nsubject;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNdate() {
		return ndate;
	}
	public void setNdate(String ndate) {
		this.ndate = ndate;
	}
	public String getNview() {
		return nview;
	}
	public void setNview(String nview) {
		this.nview = nview;
	}
	
}
