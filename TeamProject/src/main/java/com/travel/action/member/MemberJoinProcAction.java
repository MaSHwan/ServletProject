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

		// view에서 값 받아오기
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
		
      //service 호출
        BoardService service = new BoardService();
        if (!service.joinMember(memberVo)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원 가입에 실패하였습니다.');location.href='/';</script>");
            out.close();
            return null;
        }
//        else{
//            memberVo.setLgn_fl(true);
//            LoginManager lm = LoginManager.getInstance();
//            lm.setSession(request.getSession(),memberVo.getId());
//        }

        //메인페이지로 이동
        ActionForward forward = new ActionForward();
        forward.setPath("/");
        forward.setRedirect(true);
        return forward;
		
		
	}

}
