<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.member.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.member.memberDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//String userid = "abcd";
	request.setCharacterEncoding("utf-8");
	memberDAOImpl dao = memberDAOImpl.getInstace();
	String userid = request.getParameter("userid");
	dao.memberDel(userid);
	ArrayList<MemberVO> arr = dao.memberList();
	int count = dao.memberCount();
	JSONObject mainObject = new JSONObject();
	JSONArray jarr = new JSONArray();
	for(MemberVO vo :arr){//회원데이터
		String mode = vo.getAdmin()==0?"일반회원":"관리자";
		JSONObject obj = new JSONObject();
		obj.put("name", vo.getName());
		obj.put("userid", vo.getUserid());
		obj.put("email", vo.getEmail());
		obj.put("phone", vo.getPhone());
		obj.put("mode", mode);
		jarr.add(obj);
	}
	
	JSONObject countObj = new JSONObject();
	countObj.put("count", count);
	
	mainObject.put("jarr",jarr);
	mainObject.put("cntObj", countObj);
	
	out.println(mainObject.toString());

%>