package com.product.model;

public class ProductDTO {
	private long pnum;
	private String product;
	private String pdetail;
	private String fileName;
	private String ptype;
	
	public long getPnum() {
		return pnum;
	}
	public void setPnum(long pnum) {
		this.pnum = pnum;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getPdetail() {
		return pdetail;
	}
	public void setPdetail(String pdetail) {
		this.pdetail = pdetail;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	
}
