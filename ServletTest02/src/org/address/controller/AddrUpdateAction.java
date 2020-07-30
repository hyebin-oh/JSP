package org.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.address.model.SAddressDAO;
import org.address.model.SAddressDTO;

public class AddrUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		SAddressDTO dto = new SAddressDTO();
		dto.setNum(Integer.parseInt(req.getParameter("num")));
		dto.setName(req.getParameter("name"));
		dto.setAddr(req.getParameter("addr"));
		dto.setTel(req.getParameter("tel"));
		dto.setZipcode(req.getParameter("zipcoe"));
		
		SAddressDAO dao = SAddressDAO.getInstance();
		dao.addressUpdate(dto);
		resp.sendRedirect("list.ad");
	}

}
