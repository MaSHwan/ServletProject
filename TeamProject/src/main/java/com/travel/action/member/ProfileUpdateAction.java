package com.travel.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.common.Action;
import com.travel.common.ActionForward;
import com.travel.common.LoginManager;
import com.travel.service.BoardService;
import com.travel.vo.MemberVo;

public class ProfileUpdateAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        LoginManager lm = LoginManager.getInstance();
        String id = lm.getMemberId(request.getSession());

        String nick = request.getParameter("nick");

        BoardService service = new BoardService();
        //vo에 담음
        MemberVo vo = new MemberVo();
        vo.setNickname(nick);
        vo.setId(id);

        if(!service.profileUpdate(vo)){
            out.println("<script>alert('회원정보 수정에 실패했습니다.');history.back();</script>");
            out.close();
            return null;
        }

        out.println("<script>" +
                "alert('회원정보 수정 성공');" +
                "location.href='/profile.do?id="+id+"';" +
                "</script>");
        out.close();
        ActionForward forward = new ActionForward();
        forward.setPath("/profile.do?id="+id);
        return forward;
    }
}
