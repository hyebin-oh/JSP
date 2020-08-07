package com.board.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAOImpl;
import com.board.model.BoardDTO;
import com.board.model.PageUtil;

/**
 * Servlet implementation class BoardListAction
 */
@WebServlet("/board/list")
public class BoardListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BoardDAOImpl dao = BoardDAOImpl.getInstace();
		
		String pageNum = request.getParameter("pageNum")==null?"1":request.getParameter("pageNum");
		String field = request.getParameter("field")==null?"":request.getParameter("field");
		String word = request.getParameter("word")==null?"":request.getParameter("word");
		
		//페이지 셋팅
		int currentPage = Integer.parseInt(pageNum);//현재페이지
		int pageSize=5;//화면에 표시될 페이지 수
		int startRow = (currentPage-1)*pageSize+1;//1,6,11...5단위로 페이지 시작 넘버
		int endRow = currentPage*pageSize;//5,10...5단위로 페이지 끝 넘버
		
		//전체 게시글 수
		int count=dao.boardCount(field, word);
		
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
		
		ArrayList<BoardDTO> arr = dao.boardList(field, word, startRow, endRow);		
		int rowNo = count-((currentPage-1)*pageSize);
		
		request.setAttribute("rowNo", rowNo);
		request.setAttribute("count", count);
		request.setAttribute("arr", arr);
		request.setAttribute("pu", pu);
		RequestDispatcher rd = request.getRequestDispatcher("listResult.jsp");
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
