package com.board.action;

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

import com.board.model.BoardDAOImpl;
import com.board.model.BoardDTO;
import com.board.model.PageUtil;

/**
 * Servlet implementation class BoardSearchAction
 */
@WebServlet("/board/search")
public class BoardSearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String field = request.getParameter("field");
		String word = request.getParameter("word");	
		
		//������ ����
		String pageNum = request.getParameter("pageNum");		
		int currentPage = Integer.parseInt(pageNum);//����������
		int pageSize=5;//ȭ�鿡 ǥ�õ� ������ ��
		int startRow = (currentPage-1)*pageSize+1;//1,6,11...5������ ������ ���� �ѹ�
		int endRow = currentPage*pageSize;//5,10...5������ ������ �� �ѹ�		
		
		BoardDAOImpl dao = BoardDAOImpl.getInstace();
		ArrayList<BoardDTO> arr = dao.boardList(field, word, startRow, endRow);
		int count = dao.boardCount(field, word);
		
		JSONObject mainObj = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		for(BoardDTO dto:arr) {
			JSONObject jobj = new JSONObject();
			jobj.put("num", dto.getNum());
			jobj.put("writer", dto.getWriter());
			jobj.put("subject", dto.getSubject());
			jobj.put("content", dto.getContent());
			jobj.put("reg_date", dto.getReg_date());
			jobj.put("readcount", dto.getReadcount());
			jarr.add(jobj);
		}
		//�� ������ ��
				int totPage = (count/pageSize)+(count%pageSize==0?0:1);
				int pageBlock = 5; //ȭ�鿡 ǥ�õ� �Խñ� ��
				int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
				int endPage = startPage+pageBlock-1;
				if(endPage>totPage) endPage=totPage;
		//������ ����
				PageUtil pu = new PageUtil() ;
				pu.setCurrentPage(currentPage);
				pu.setEndPage(endPage);
				pu.setPageBlock(pageBlock);
				pu.setStartPage(startPage);
				pu.setTotPage(totPage);
				
				//�˻�
				pu.setField(field);
				pu.setWord(word);		
				
				
		JSONObject jcount = new JSONObject();
		jcount.put("scount", count);
		
		JSONObject puCount = new JSONObject();
		jcount.put("puCount", puCount);
		
		mainObj.put("searchArr", jarr);
		mainObj.put("searchCount", jcount);
		mainObj.put("puCount", count);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(mainObj.toString());
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
