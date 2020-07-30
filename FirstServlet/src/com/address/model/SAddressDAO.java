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
	
	//상세보기
	public SAddressDTO addressDetail(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		SAddressDTO dto =null;
		try {
			con=getConnection();
			String sql = "select * from address where num="+num;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			dto  = new SAddressDTO();
			if(rs.next()) {
				dto.setAddr(rs.getString("addr"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getLong("num"));
				dto.setTel(rs.getString("tel"));
				dto.setZipcode(rs.getString("zipcode"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return dto;
		
	}
	
	//삭제
	public void addressDelete(int num) {
		
		Connection con = null;
		Statement st = null;
		
		try {
			con=getConnection();
			String sql = "delete from address where num="+num;
			st=con.createStatement();
			st.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}
	
	//수정하기
	public void addressUpdate(SAddressDTO dto) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql = "update address set name=?, zipcode=?, addr=?, tel=? where num=?";
			ps=con.prepareStatement(sql);
			ps=con.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getZipcode());
			ps.setString(3, dto.getAddr());
			ps.setString(4, dto.getTel());
			ps.setLong(5, dto.getNum());
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
	}
	
	//갯수
	public int addressCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con=getConnection();
			String sql = "select count(*) from address";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}		
		return count;		
	}
	
	
	//우편번호
	public ArrayList<ZipcodeDTO> zipSearch(String dong){
		Connection con = null;
		Statement st = null;
		ResultSet rs=null;
		ArrayList<ZipcodeDTO> zipArr = new ArrayList<>();
		
		try {
			con=getConnection();
			String sql="select * from zipcode" ;
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				ZipcodeDTO zip = new ZipcodeDTO();
				zip.setBunji(rs.getString("bunji"));
				zip.setDong(rs.getString("dong"));
				zip.setGugun(rs.getString("gugun"));
				zip.setSido(rs.getString("sido"));
				zip.setZipcode(rs.getString("zipcode"));
				zipArr.add(zip);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, st, rs);
		}	
		return zipArr;		
	}
		
	
	//닫기
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
