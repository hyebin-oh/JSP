package com.member.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberDAOImpl;
import com.member.model.MemberDTO;

/**
 * Servlet implementation class MemberUpdateAction
 */
@WebServlet("/member/userUpdate.me")
public class UserUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberDTO member = new MemberDTO();
		member.setPwd(request.getParameter("pwd"));
		member.setName(request.getParameter("name"));
		member.setBirth(request.getParameter("birth"));
		member.setAddr(request.getParameter("addr"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));		
		member.setUserid(request.getParameter("userid"));
		MemberDAOImpl  dao = MemberDAOImpl.getInstance();
		dao.memberUpdate(member);
		
		response.sendRedirect("userView.me");
	
	}

}
