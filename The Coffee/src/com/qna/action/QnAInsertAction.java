package com.qna.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qna.model.QnADAO;
import com.qna.model.QnADTO;

/**
 * Servlet implementation class QnAInsertAction
 */
@WebServlet("/QnA/qnaInsert.me")
public class QnAInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnAInsertAction() {
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
		QnADAO dao = QnADAO.getInstance();
		QnADTO qna = new QnADTO();
		
		//userid값 세션에서 받아오기
		HttpSession session = request.getSession();
		String userid =(String) session.getAttribute("userid");
				
		qna.setQcontent(request.getParameter("qcontent"));
		qna.setQsubject(request.getParameter("qsubject"));
		qna.setUserid(userid);
		
		dao.qnaInsert(qna);
		response.sendRedirect("qnaList.me");
	}

}
