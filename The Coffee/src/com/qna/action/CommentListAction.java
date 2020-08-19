package com.qna.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.qna.model.CommentDTO;
import com.qna.model.QnADAO;
import com.qna.model.QnADTO;

/**
 * Servlet implementation class CommentListAction
 */
@WebServlet("/QnA/commentList.me")
public class CommentListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int qnum = Integer.parseInt(request.getParameter("qnum"));
		QnADAO dao = QnADAO.getInstance();
		ArrayList<CommentDTO> arr = dao.commentList(qnum);
		
		JSONObject mainObject = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(CommentDTO comment:arr) {
			JSONObject obj = new JSONObject();
			obj.put("cnum", comment.getCnum());
			obj.put("cdate", comment.getCdate());
			obj.put("msg", comment.getMsg());
			obj.put("qnum", comment.getQnum());
			obj.put("userid", comment.getUserid());
			obj.put("cwriter", comment.getCwriter());
			jarr.add(obj);
		}
		mainObject.put("commentList", jarr);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(mainObject.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
