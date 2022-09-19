package com.travel.action.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.common.Action;
import com.travel.common.ActionForward;
import com.travel.common.RegExp;
import com.travel.service.BoardService;
import com.travel.vo.MemberVo;

import static com.travel.common.RegExp.MEMBER_ID;

public class ProfileAction implements Action {
    @Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response) throws IOException {
        ActionForward forward = new ActionForward();

        String id =request.getParameter("id");

        //값 비어있는지,형식(정규식) 검사
        if (id == null || id.equals("") || !RegExp.checkString(MEMBER_ID, id)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다');location.href='/';</script>");
            out.close();
            return null;
        }

        BoardService service = new BoardService();
        MemberVo memberVo = service.getMember(id);

        if(memberVo.getNewFileName()==null){
            memberVo.setNewFileName("basic.jpg");
        }

        request.setAttribute("vo",memberVo);
        forward.setPath("/views/profile-info.jsp");
        return forward;
    }
}
