package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.board.control.ActionForward;

public interface BoardAction  {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
