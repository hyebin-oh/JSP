package com.order.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.OrderDAO;
import com.order.model.OrderDTO;

/**
 * Servlet implementation class OrderView
 */
@WebServlet("/order/adminOrderView.me")
public class adminOrderViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminOrderViewAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		long ordernum=Long.parseLong(request.getParameter("ordernum"));
		//String orderState = request.getParameter("orderstate");
		
		//주문 리스트 불러오기
		OrderDAO dao = OrderDAO.getInstance();
		ArrayList<OrderDTO> arr = dao.orderView(ordernum);
		request.setAttribute("order", arr);
		
		//주문자 정보 불러오기
		OrderDTO orderInfo = dao.orderViewInfo(ordernum);
		request.setAttribute("orderInfo", orderInfo);
		
		RequestDispatcher rd = request.getRequestDispatcher("adminOrderView.jsp");
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
