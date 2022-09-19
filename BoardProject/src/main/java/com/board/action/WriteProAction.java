package com.board.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;

public class WriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		// ������ �� ó��
		
		// ��ü ���� ( jsp ������ property)
		BoardVO article = new BoardVO();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));
		
		article.setSubject(request.getParameter("subject"));
		
		article.setRegdate(new Date(System.currentTimeMillis()));
		article.setRef(Integer.parseInt(request.getParameter("ref")));
		
		article.setContent(request.getParameter("content"));
		
		
		BoardDAO dbPro = BoardDAO.getInstance();
		dbPro.insertArticle(article);
		
		return "/board/writeProc.jsp";
	}

}
