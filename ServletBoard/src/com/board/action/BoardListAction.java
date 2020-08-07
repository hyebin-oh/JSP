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
		
		//������ ����
		int currentPage = Integer.parseInt(pageNum);//����������
		int pageSize=5;//ȭ�鿡 ǥ�õ� ������ ��
		int startRow = (currentPage-1)*pageSize+1;//1,6,11...5������ ������ ���� �ѹ�
		int endRow = currentPage*pageSize;//5,10...5������ ������ �� �ѹ�
		
		//��ü �Խñ� ��
		int count=dao.boardCount(field, word);
		
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
