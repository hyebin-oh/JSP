package com.order.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.OrderDAO;
import com.order.model.OrderDTO;

/**
 * Servlet implementation class OrderUpdateAction
 */
@WebServlet("/order/orderUpdate.me")
public class OrderUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderUpdateAction() {
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
		OrderDTO order= new OrderDTO();
		order.setAddr(request.getParameter("addr"));
		order.setName(request.getParameter("name"));
		order.setOrderState(request.getParameter("orderstate"));
		order.setPhone(request.getParameter("phone"));
		order.setOrdernum(Long.parseLong(request.getParameter("ordernum")));
		OrderDAO dao = OrderDAO.getInstance();
		dao.orderUpdate(order);
		response.sendRedirect("adminOrderList.me");
	}

}
