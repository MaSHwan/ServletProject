package com.board.action;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.board.control.ActionForward;
import com.board.model.BoardDao;
import com.board.model.BoardVO;

public class BoardListAction implements BoardAction {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");
    	BoardDao dao = new BoardDao();
    	List<BoardVO> ls = dao.selectAll();
    	
    	request.setAttribute("ls", ls);
    	System.out.println(ls.get(0).getContent());
		return new ActionForward("/board/list.jsp", false);
	}

}
