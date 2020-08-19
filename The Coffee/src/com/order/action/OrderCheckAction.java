package com.order.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartDAO;
import com.cart.model.CartDTO;
import com.order.model.OrderDAO;
import com.order.model.OrderDTO;

/**
 * Servlet implementation class OrderCheckAction
 */
@WebServlet("/order/orderCheck.me")
public class OrderCheckAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderCheckAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();		
		String userid=(String) session.getAttribute("userid");
		
		OrderDAO dao = OrderDAO.getInstance();
		
		OrderDTO order = dao.orderCheck(userid);
		order.setUserid(userid);		
		
		
		//주문자 정보 부르기
		request.setAttribute("order", order);
		
		//장바구니 리스트 부르기
		CartDAO cdao = CartDAO.getInstance();
		ArrayList<CartDTO> arr = cdao.cartList(userid);
		request.setAttribute("cartList", arr);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("orderCheck.jsp");
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
