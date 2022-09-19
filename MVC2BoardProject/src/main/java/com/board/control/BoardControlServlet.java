package com.board.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.BoardAction;
import com.board.control.BoardActionFactory;
import com.board.control.ActionForward;



public class BoardControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String cmd = request.getParameter("cmd");
		
		if(cmd != null) {
			BoardActionFactory factory = BoardActionFactory.getInstance();
			BoardAction action = factory.getAction(cmd);
			
			ActionForward af = action.execute(request, response);
			
			if(af.isRedirect()) {
				response.sendRedirect(af.getUrl());
			}else {
				RequestDispatcher rd = request.getRequestDispatcher(af.getUrl());
				rd.forward(request, response);
			}
		}else {
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head><title>Error</title></head>");
			
			out.println("<body>");
			out.println("<h4>올바른 요청이 아닙니다.</h4>");
			out.println("<h4>http://localhost:8080/Mvc2Project/mvcmem/member.mdo?cmd=요청키워드</h4>");
			
			out.println("</body>");
			out.println("</html>");
		}
	}
	}


