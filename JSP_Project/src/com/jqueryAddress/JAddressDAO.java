package com.jqueryAddress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JAddressDAO {
	private static JAddressDAO instance = new JAddressDAO();
	public static JAddressDAO getInstance() {
		return instance;
	}
	//db연결
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");//톰캣의 env라는 가상의 폴더
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");		
		return ds.getConnection();
	}
	
	//추가
	public void addrInsert(Address ad) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql = "insert into address values (address_seq.nextval,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
		
	}
	
	//전체보기 검색 아님
	public ArrayList<Address> addrList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Address> arr = new ArrayList<Address>();
		String sql="";
		try {
				sql="select * from address";
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Address ad = new Address();
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
			closeStatement(con, st, rs);
		}
		return arr;		
	}
	
	//전체보기 검색
	public ArrayList<Address> addrList(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Address> arr = new ArrayList<Address>();
		String sql="";
		try {
				sql="select * from address where "+field +" like '%" + word+"%'";
				//sql="select * from address where tel like '%010%'";
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				Address ad = new Address();
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
			closeStatement(con, st, rs);
		}
		return arr;		
	}
	
	//갯수
	public int getCount(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql="";
		int count=0;
		
		try {
			con=getConnection();
			
				sql = "select count(*) from address where "+field +" like '%" + word+"%'";
			
			st =con.createStatement();
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeStatement(con, st, rs);
		}
		return count ;
	}
	
	
	public int getCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con = getConnection();
			String	sql = "select count(*) from address";
		    st = con.createStatement();
		    rs = st.executeQuery(sql);
		    if(rs.next()) {
		    	count = rs.getInt(1);
		    }
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			closeStatement(con, st, rs);
		}
		return count;
	}
	
	//상세보기
	public Address addrDetail(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Address ad = null;
		
		try {
			con=getConnection();
			String sql = "select * from address where num="+num;
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) {
				ad=new Address();
				ad.setAddr(rs.getString("addr"));
				ad.setName(rs.getString("name"));
				ad.setNum(rs.getLong("num"));
				ad.setTel(rs.getString("tel"));
				ad.setZipcode(rs.getString("zipcode"));
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeStatement(con, st, rs);
		}
		return ad;
	}
	
	
	//수정
	public void addrUpdate(Address ad) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql = "update address set name=?, zipcode=?, addr=?, tel=? where num=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, ad.getName());
			ps.setString(2, ad.getZipcode());
			ps.setString(3, ad.getAddr());
			ps.setString(4, ad.getTel());
			ps.setLong(5, ad.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}
	}
	
	
	//삭제
	public void addrDelete(int num) {
		Connection con = null;
		Statement st = null;
		
		try {
			con=getConnection();
			String sql = "delete from address where num="+num;
			st=con.createStatement();
			st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeStatement(con, st, null);
		}
	}
	
	
	//검색
	public ArrayList<ZipcodeBean> zipcodeRead(String dong) {
		Connection con = null;
		Statement st = null;
		ResultSet rs=null;
		ArrayList<ZipcodeBean> zipArr = new ArrayList<>();
		
		try {
			con=getConnection();
			String sql="select * from zipcode where dong like '%"+dong+"%'" ;
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				ZipcodeBean zip = new ZipcodeBean();
				zip.setBunji(rs.getString("bunji"));
				zip.setDong(rs.getString("dong"));
				zip.setGugun(rs.getString("gugun"));
				zip.setSeq(rs.getInt("seq"));
				zip.setSido(rs.getString("sido"));
				zip.setZipcode(rs.getString("zipcode"));
				zipArr.add(zip);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeStatement(con, st, rs);
		}	
		return zipArr;
	}
	
	
	//닫기
	private void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if(con!=null) con.close();
			if(ps!=null) ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void closeStatement(Connection con, Statement st, ResultSet rs) {
		try {
			if(con!=null) con.close();
			if(st!=null) st.close();
			if(rs!=null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
