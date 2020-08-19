package com.notice.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.notice.model.NoticeDAO;
import com.notice.model.NoticeDTO;

/**
 * Servlet implementation class NoticeUpdateAction
 */
@WebServlet("/notice/noticeUpdate.me")
public class NoticeUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		long nnum=Long.parseLong(request.getParameter("nnum"));
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeDTO notice = dao.noticeView(nnum);
		request.setAttribute("notice", notice);
		
		RequestDispatcher rd = request.getRequestDispatcher("noticeUpdate.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		NoticeDTO notice = new NoticeDTO();
		notice.setNnum(Long.parseLong(request.getParameter("nnum")));
		notice.setNcontent(request.getParameter("ncontent"));
		notice.setNsubject(request.getParameter("nsubject"));
		
		NoticeDAO dao = NoticeDAO.getInstance();
		dao.noticeUpdate(notice);
		
		response.sendRedirect("noticeList.me");
		
		
	}

}
