package com.qna.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qna.model.CommentDTO;
import com.qna.model.QnADAO;

/**
 * Servlet implementation class CommentInsertAction
 */
@WebServlet("/QnA/commentInsert.me")
public class CommentInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String cwriter = (String) session.getAttribute("userid");
		String userid = request.getParameter("userid");		
		String msg = request.getParameter("msg");
		int qnum = Integer.parseInt(request.getParameter("qnum"));	
		
		CommentDTO comment = new CommentDTO();
		comment.setQnum(qnum);
		comment.setMsg(msg);
		comment.setUserid(userid);
		comment.setCwriter(cwriter);
		QnADAO dao = QnADAO.getInstance();
		dao.commentInsert(comment);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
