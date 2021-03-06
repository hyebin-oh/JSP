package com.exam.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PersonAction
 */
@WebServlet("/exam/PersonAction")
public class PersonAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
req.setCharacterEncoding("utf-8");
		
		String name=req.getParameter("name");
		String id=req.getParameter("id");
		String password=req.getParameter("password");
		String gender=req.getParameter("gender");
		String[] notice=req.getParameterValues("notice");
		String job=req.getParameter("job");
		
		Person person= new Person();
		person.setGender(gender);
		person.setId(id);
		person.setJob(job);
		person.setName(name);
		person.setNotice(notice);
		person.setPassword(password);
		
		req.setAttribute("p", person);
		req.setAttribute("msg", "@WebServlet���");
		RequestDispatcher rd = req.getRequestDispatcher("personResult.jsp");
		rd.forward(req, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
