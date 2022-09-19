package com.board.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardVO;

// �� ����� ó���ϴ� Ŭ����
public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		 int pageSize = 3;
		    

		    
		    //������ num�� �����´�
		    String pageNum = request.getParameter("pageNum");
		    
		    if(pageNum == null) {
		    	pageNum = "1";
		    }

		    //���� ������ 
		    int currentPage =Integer.parseInt(pageNum);
		    
		    int startRow = (currentPage-1) * pageSize + 1; 
		    int endRow = currentPage * pageSize;
		    
		    
		    
		    
		    int count = 0;
		      int number =0;
		      List<BoardVO> articleList = null;
		      BoardDAO dbPro = BoardDAO.getInstance(); // db����
		      count = dbPro.getArticleCount();
		   
		       if(count > 0){  // ���� �������� �ش��ϴ� �۸��
		       //�ϳ��� ���� �ϸ� ����Ʈ�� ����ض�
		       articleList = dbPro.getArticles(startRow, endRow);
		    }
		       
		 else { // �˻� �� ���
		   
		      //�ϳ��� ���� �ϸ� ����Ʈ�� ����ض�
		      articleList = Collections.emptyList();
		   
		      
		}
		   
		  // �� ��Ͽ� ǥ���� �� ��ȣ
		    number = count - (currentPage - 1) * pageSize;
		
		    // �ش� �信�� ����� �Ӽ� ����
		    request.setAttribute("currentPage",currentPage);
		    request.setAttribute("startRow", startRow);
		    request.setAttribute("endRow", endRow);
		    request.setAttribute("count", count);
		    request.setAttribute("pageSize", pageSize);
		    request.setAttribute("number", number);
		    request.setAttribute("articleList", articleList);
		    
		    // �ش� ��� ��ȯ����
		
		return "/board/list.jsp";
	}

}
