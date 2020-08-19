package com.qna.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qna.model.QnADAO;
import com.qna.model.QnADTO;

/**
 * Servlet implementation class QnAViewAction
 */
@WebServlet("/QnA/qnaView.me")
public class QnAViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnAViewAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		long qnum = Long.parseLong(request.getParameter("qnum"));
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		request.setAttribute("userid", userid);
		
		QnADAO dao = QnADAO.getInstance();
		QnADTO qna = dao.qnaView(qnum);
		request.setAttribute("qna", qna);
		
		RequestDispatcher rd = request.getRequestDispatcher("qnaView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
