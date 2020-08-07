package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.model.BoardDAO;
import com.board.model.BoardDAOImpl;
import com.board.model.CommentDTO;
import com.sun.net.httpserver.HttpsConfigurator;

/**
 * Servlet implementation class CommentInsertAction
 */
@WebServlet("/board/commentInsert")
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
		String msg = request.getParameter("msg");
		int bnum = Integer.parseInt(request.getParameter("num"));
		HttpSession session =request.getSession();
		String userid = (String) session.getAttribute("userid");
	//	if(userid==null) {//로그인 안됨
	//		response.setContentType("text/html;charset=utf-8");
	//		PrintWriter out = response.getWriter();
	//		out.print(1);
	//	}else { //로그인
			CommentDTO comment = new CommentDTO();
			comment.setBnum(bnum);
			comment.setMsg(msg);
			comment.setUserid("userid");
			BoardDAOImpl dao = BoardDAOImpl.getInstance();
			dao.commentInsert(comment);
		
	//	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
