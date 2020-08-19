package com.cart.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartDAO;
import com.cart.model.CartDTO;
import com.product.model.ProductDAO;
import com.product.model.ProductDTO;

/**
 * Servlet implementation class CartAddAction
 */
@WebServlet("/cart/cartAdd.me")
public class CartAddAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartAddAction() {
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
		CartDAO dao = CartDAO.getInstance();
		CartDTO cart = new CartDTO();
		
		//아이디값 세션에서 받아오기
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		session.setAttribute("userid", userid);

		//데이터에 저장	
		cart.setProduct(request.getParameter("product"));
		cart.setCount(Integer.parseInt(request.getParameter("count")));
		cart.setPtype(request.getParameter("ptype"));
		cart.setUserid(userid);		
		dao.cartAdd(cart);
		
		RequestDispatcher rd = request.getRequestDispatcher("cartList.me");
		rd.forward(request, response);
	}

}
