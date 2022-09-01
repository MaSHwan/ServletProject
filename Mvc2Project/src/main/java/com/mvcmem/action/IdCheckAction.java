package com.mvcmem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvcmem.control.ActionForward;
import com.mvcmem.model.StudentDAO;

public class IdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// DB����
		StudentDAO dao = StudentDAO.getInstance();
		String id = request.getParameter("id");
    	boolean check = dao.idcheck(id);
    	
    	request.setAttribute("id", id);
    	request.setAttribute("check", check);
		
		return new ActionForward("/mvcmem/idCheck.jsp",false);
	}

}
