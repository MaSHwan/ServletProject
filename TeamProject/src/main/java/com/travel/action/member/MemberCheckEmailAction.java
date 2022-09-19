package com.travel.action.member;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.common.Action;
import com.travel.common.ActionForward;
import com.travel.service.BoardService;

public class MemberCheckEmailAction implements Action {
    @Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("email"); //join.jsp에서 email받아옴

        BoardService service = new BoardService();
        //0이면 미중복 1이면 중복
        request.setAttribute("count", service.emailCheck(email));

        //checkId.jsp재사용
        ActionForward forward = new ActionForward();
        forward.setPath("/views/Ajax/AjaxCheckId.jsp");
        return forward;
    }
}
