package com.notice.action;

import java.io.IOException;
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		NoticeDTO notice = new NoticeDTO();
		notice.setNcontent(request.getParameter("ncontent"));
		notice.setNdate(request.getParameter("ndate"));
		notice.setNsubject(request.getParameter("nsubject"));
		notice.setNview(request.getParameter("nview"));
		
		NoticeDAO dao = NoticeDAO.getInstance();
		dao.noticeUpdate(notice);
		
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("noticeView.me");
		
		
	}

}
