package com.travel.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.common.Action;
import com.travel.common.ActionForward;
import com.travel.common.LoginManager;
import com.travel.service.BoardService;

public class MemberDeleteAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        LoginManager lm = LoginManager.getInstance();
        String id = lm.getMemberId(request.getSession());

        //로그인 안돼있을때
        if (id == null) {
            out.println("<script>alert('비정상 접근입니다.');location.href='/login.do';</script>");
            out.close();
            return null;
        }

        if (id != null) {
            lm.removeSession(id); //세션뺏음,로그아웃 처리
        }

        BoardService service = new BoardService();
        if (!service.deleteMember(id)) {
            out.println("<script>alert('회원탈퇴에 실패했습니다.');history.back();</script>");
            out.close();
            return null;
        }

        ActionForward forward = new ActionForward();
        forward.setPath("/index.jsp");
        forward.setRedirect(true);
        return forward;
    }
}