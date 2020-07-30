package org.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PersonServlet implements Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String name=req.getParameter("name");
		String id=req.getParameter("id");
		String password=req.getParameter("password");
		String gender=req.getParameter("gender");
		String[] notice=req.getParameterValues("notice");
		String job=req.getParameter("job");
		String mail="";
		for(String value : notice) {
			mail+=value+" ";
		}
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();	
		out.println("<html>");
		out.println("<head><title>���</title></head>");
		out.println("<body>");
		out.println("�̸�: "+name+"<br>");
		out.println("���̵�: "+id+"<br>");
		out.println("��й�ȣ: "+password+"<br>");
		out.println("����: "+gender+"<br>");
		out.println("���ϼ���: "+mail+"<br>");
		out.println("����: "+job+"<br>");
		out.println("</body>");
		
	}

}