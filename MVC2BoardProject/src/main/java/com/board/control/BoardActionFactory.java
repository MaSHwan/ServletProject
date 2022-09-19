package com.board.control;

import com.board.action.BoardAction;
import com.board.action.BoardListAction;
import com.board.action.IndexAction;



public class BoardActionFactory {
private static BoardActionFactory factory;
	
	private BoardActionFactory() {}
	
	public static synchronized BoardActionFactory getInstance() {
		if(factory == null) {
			factory = new BoardActionFactory();
		}
		return factory;
	}
	
	public BoardAction getAction(String cmd) { // Mvc2Project/mvc/test.do?cmd=index
		BoardAction action = null;
		
		switch (cmd) {
		case "index":
			action = new IndexAction();
			break;
			
		case "boardlist":
			action = new BoardListAction();
			break;
			
		

		default: new IndexAction();
			break;
		}
		
		return action;
	}
}
