package com.order.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartDAO;
import com.order.model.OrderDAO;
import com.order.model.OrderDTO;

/**
 * Servlet implementation class OrderInsertAction
 */
@WebServlet("/order/orderInsert.me")
public class OrderInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderInsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//userid값 세션에서 받아오기
		HttpSession session = request.getSession();
		String userid =(String) session.getAttribute("userid");
		String product[] = request.getParameterValues("product");
		String count[] = request.getParameterValues("count");
		OrderDAO dao = OrderDAO.getInstance();
		// 최대값
		int ordernum = dao.ordernumCheck();
		
		for(int i = 0; i <product.length;i++ ) {
			OrderDTO order = new OrderDTO();			
			order.setAddr(request.getParameter("addr"));
			order.setProduct(product[i]);
			order.setCount(Integer.parseInt(count[i]));
			order.setName(request.getParameter("name"));
			order.setPhone(request.getParameter("phone"));
			order.setUserid(userid);
			order.setOrdernum(ordernum);
			dao.orderInsert(order);
		}
		
		CartDAO cdao = CartDAO.getInstance();
		cdao.orderToDelete(userid);	
		
		
		response.sendRedirect("userOrderList.me");
	}

}
