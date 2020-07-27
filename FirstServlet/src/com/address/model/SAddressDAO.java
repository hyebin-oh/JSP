package com.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SAddressDAO {
	//db연결
	private static SAddressDAO instance = new SAddressDAO();
	public static SAddressDAO getInstacne() {
		return instance;
	}
	
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
		return ds.getConnection();
	}
	
	//추가
	public void insertAddress(SAddressDTO ad) {
		Connection con =  null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql="insert into address values(address_seq.nextval,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.executeUpdate(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}		
	}
	
	//전체보기
	public ArrayList<SAddressDTO> addressList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<SAddressDTO> arr = new ArrayList<SAddressDTO>();

		try {
			con = getConnection();
			String sql = "select * from address";			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				SAddressDTO ad = new SAddressDTO();
				ad.setNum(rs.getLong("num"));
				ad.setName(rs.getString("name"));
				ad.setZipcode(rs.getString("zipcode"));
				ad.setAddr(rs.getString("addr"));
				ad.setTel(rs.getString("tel"));
				arr.add(ad);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;		
	}
	
	
	public void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if(con!=null) con.close();
			if(ps!=null) ps.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection(Connection con, Statement st, ResultSet rs ) {
		try {
			if(con!=null) con.close();
			if(st!=null) st.close();
			if(rs!=null) rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
