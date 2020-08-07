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
		
		//페이지 셋팅
		String pageNum = request.getParameter("pageNum");		
		int currentPage = Integer.parseInt(pageNum);//현재페이지
		int pageSize=5;//화면에 표시될 페이지 수
		int startRow = (currentPage-1)*pageSize+1;//1,6,11...5단위로 페이지 시작 넘버
		int endRow = currentPage*pageSize;//5,10...5단위로 페이지 끝 넘버		
		
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
		//총 페이지 수
				int totPage = (count/pageSize)+(count%pageSize==0?0:1);
				int pageBlock = 5; //화면에 표시될 게시글 수
				int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
				int endPage = startPage+pageBlock-1;
				if(endPage>totPage) endPage=totPage;
		//페이지 부착
				PageUtil pu = new PageUtil() ;
				pu.setCurrentPage(currentPage);
				pu.setEndPage(endPage);
				pu.setPageBlock(pageBlock);
				pu.setStartPage(startPage);
				pu.setTotPage(totPage);
				
				//검색
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
