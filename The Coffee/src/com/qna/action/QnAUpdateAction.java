package com.qna.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qna.model.QnADAO;
import com.qna.model.QnADTO;

/**
 * Servlet implementation class QnAUpdateAction
 */
@WebServlet("/QnA/qnaUpdate.me")
public class QnAUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnAUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		long qnum = Long.parseLong(request.getParameter("qnum"));
		QnADAO dao = QnADAO.getInstance();
		QnADTO qna = dao.qnaView(qnum);
		request.setAttribute("qna", qna);
		RequestDispatcher rd = request.getRequestDispatcher("qnaUpdate.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		QnADTO qna = new QnADTO();
		qna.setQnum(Long.parseLong(request.getParameter("qnum")));
		qna.setQcontent(request.getParameter("qcontent"));
		qna.setQsubject(request.getParameter("qsubject"));
		QnADAO dao = QnADAO.getInstance();
		dao.qnaUpdate(qna);
		response.sendRedirect("qnaList.me");
	}

}
