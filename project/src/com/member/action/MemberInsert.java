package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDTO;
import com.member.model.SMemberDAOImpl;

/**
 * Servlet implementation class MemberInsert
 */
@WebServlet("/member/insert.me")
public class MemberInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		SMemberDAOImpl dao = SMemberDAOImpl.getInstace();
		MemberDTO member = new MemberDTO();
		member.setAdmin(Integer.parseInt(request.getParameter("admin")));
		member.setEmail(request.getParameter("email"));
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		member.setPwd(request.getParameter("pwd"));	
		member.setUserid(request.getParameter("userid"));
		
		String userid = request.getParameter("userid");
		String flag = dao.idCheck(userid);
		member.setUserid(userid);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(flag);
//		
		
		dao.memberInsert(member);
		response.sendRedirect("login.me");
	}

}
