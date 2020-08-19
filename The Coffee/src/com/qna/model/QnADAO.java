package com.qna.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class QnADAO {
	//디비연결
	private static QnADAO instance = new  QnADAO();
	public static  QnADAO  getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/project");
		return ds.getConnection();
	}
	
	//qna 등록
	public void qnaInsert(QnADTO qna) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql = "insert into qna values(qna_seq.nextval,?,?,sysdate,?,0)";
			ps=con.prepareStatement(sql);
			ps.setString(1, qna.getQsubject());
			ps.setString(2, qna.getQsubject());
			ps.setString(3, qna.getUserid());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
	}
	
	//qna 게시판 전체보기
	public ArrayList<QnADTO> qnaList(String field, String query, int page){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<QnADTO> arr = new ArrayList<QnADTO>();
		String sql = "";
		
		try {
			con=getConnection();
			
			if(query.equals("")) {//검색안함
				sql="select * from (" + 
						"select rownum num, n.* " + 
						"from (select * from qna order by qnum desc) n)" +
						"where num between ? and ?";
				
			}else {//검색
				sql="select * from (" + 
						"select rownum num, n.* " + 
						"from (select * from qna where "+field+" like '%"+ query+"%'"
						+" order by qnum) n)" + 
						"where num between ? and ?";
			}
			ps=con.prepareStatement(sql);
			ps.setInt(1, 1+(page-1)*10);
			ps.setInt(2, page*10);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				QnADTO qna = new QnADTO();
				qna.setQcontent(rs.getString("qcontent"));
				qna.setQdate(rs.getString("qdate"));
				qna.setQnum(rs.getLong("qnum"));
				qna.setQsubject(rs.getString("qsubject"));
				qna.setQview(rs.getInt("qview"));
				qna.setUserid(rs.getString("userid"));
				arr.add(qna);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, ps, rs);
		}		
		return arr;	
	}
	
	//qna 사용자 전체보기
	public ArrayList<QnADTO> qnaUserList(String userid, String field, String query, int page){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<QnADTO> arr = new ArrayList<QnADTO>();
		String sql = "";
	
		try {
			con=getConnection();
			
			if(query.equals("")) {//검색안함
				sql="select * from ("
						+ "select rownum num, n.* "
						+ "from (select * from qna where userid='"+userid+"'"
						+ "order by qnum desc) n) where num between ? and ?";
			}else {
				sql="select * from ("
						+ "select rownum num, n.* "
						+ "from (select * from qna where userid='"+userid+"' and "
						+field+" like '%"+ query+"%' order by qnum) n)" 
						+" where num between ? and ?";
			}
			
			ps=con.prepareStatement(sql);
			ps.setInt(1, 1+(page-1)*10);
			ps.setInt(2, page*10);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				QnADTO qna = new QnADTO();
				qna.setQcontent(rs.getString("qcontent"));
				qna.setQdate(rs.getString("qdate"));
				qna.setQnum(rs.getLong("qnum"));
				qna.setQsubject(rs.getString("qsubject"));
				qna.setQview(rs.getInt("qview"));
				qna.setUserid(rs.getString("userid"));
				arr.add(qna);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, ps, rs);
		}
		return arr;
	}
	
	//user qna 수
	public int qnaUserCount(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con=getConnection();
			String sql = "select count(*) from qna where userid='"+userid+"'";
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
	
	//전체 qna 수
	public int qnaCount(String field, String query) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count=0;	
		String sql ="";
		
		try {
			con=getConnection();
			st=con.createStatement();
			
			if(query.equals("")) {
				sql="select count(*) from qna";
			}else {
				sql="select count(*) from qna where "+field+" like '%"+query+"%'";
			}			
			rs=st.executeQuery(sql);
			
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
	
	
	//qna 상세보기
	public QnADTO qnaView(long qnum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		QnADTO qna = null;
		
		try {
			con=getConnection();
			st=con.createStatement();
			st.execute("update qna set qview=qview+1 where qnum="+qnum);
			String sql = "select * from qna where qnum="+qnum;
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				qna=new QnADTO();
				qna.setQcontent(rs.getString("qcontent"));
				qna.setQdate(rs.getString("qdate"));
				qna.setQnum(rs.getLong("qnum"));
				qna.setQsubject(rs.getString("qsubject"));
				qna.setQview(rs.getInt("qview"));
				qna.setUserid(rs.getString("userid"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return qna;
	}
	
	//qna 수정하기
	public void qnaUpdate(QnADTO qna) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql = "update qna set qsubject=?, qcontent=? where qnum=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, qna.getQsubject());
			ps.setString(2, qna.getQcontent());
			ps.setLong(3, qna.getQnum());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		
	}
	
	
	//qna 삭제하기
	public void qnaDelete(Long qnum) {
		Connection con = null;
		Statement st = null;
		
		try {
			con=getConnection();
			String sql = "delete from qna where qnum="+qnum;
			st=con.createStatement();
			st.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, st, null);
		}	
	}
	
	//코멘트 추가하기
	public void commentInsert(CommentDTO comment) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con=getConnection();
			String sql = "insert into reply values(reply_seq.nextval,?,?,sysdate,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, comment.getUserid());
			ps.setString(2, comment.getMsg());
			ps.setInt(3, comment.getQnum());
			ps.setString(4, comment.getCwriter());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(con, ps);
		}		
	}
	
	//코멘트 리스트
	public ArrayList<CommentDTO> commentList(int qnum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<CommentDTO> arr= new ArrayList<CommentDTO>();
		
		try {
			con=getConnection();
			String sql = "select * from reply where qnum="+qnum;
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				CommentDTO comment = new CommentDTO();
				comment.setCdate(rs.getString("cdate"));
				comment.setCnum(rs.getInt("cnum"));
				comment.setMsg(rs.getString("msg"));
				comment.setQnum(rs.getInt("qnum"));
				comment.setUserid(rs.getString("userid"));
				comment.setCwriter(rs.getString("cwriter"));
				arr.add(comment);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}
	
	
	//닫기
	private void closeConnection(Connection con, PreparedStatement ps) {
		try {
		if(ps!=null) ps.close();
		if(con!=null) con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(con!=null) con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}		
	
	
	
}
