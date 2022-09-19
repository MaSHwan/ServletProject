package com.travel.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.travel.common.Action;
import com.travel.common.ActionForward;
import com.travel.service.BoardService;
import com.travel.vo.MemberVo;

public class MemberJoinProcAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// view���� �� �޾ƿ���
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String pwd_confirm = request.getParameter("pwd_confirm");
		String email = request.getParameter("email");
		String nick = request.getParameter("nick");
		
		MemberVo memberVo = new MemberVo();
        memberVo.setId(id);
        memberVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
        memberVo.setEmail(email);
        memberVo.setNickname(nick);
		
      //service ȣ��
        BoardService service = new BoardService();
        if (!service.joinMember(memberVo)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('ȸ�� ���Կ� �����Ͽ����ϴ�.');location.href='/';</script>");
            out.close();
            return null;
        }
//        else{
//            memberVo.setLgn_fl(true);
//            LoginManager lm = LoginManager.getInstance();
//            lm.setSession(request.getSession(),memberVo.getId());
//        }

        //������������ �̵�
        ActionForward forward = new ActionForward();
        forward.setPath("/");
        forward.setRedirect(true);
        return forward;
		
		
	}

}
