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

        //�α��� �ȵ�������
        if (id == null) {
            out.println("<script>alert('������ �����Դϴ�.');location.href='/login.do';</script>");
            out.close();
            return null;
        }

        if (id != null) {
            lm.removeSession(id); //���ǻ���,�α׾ƿ� ó��
        }

        BoardService service = new BoardService();
        if (!service.deleteMember(id)) {
            out.println("<script>alert('ȸ��Ż�� �����߽��ϴ�.');history.back();</script>");
            out.close();
            return null;
        }

        ActionForward forward = new ActionForward();
        forward.setPath("/index.jsp");
        forward.setRedirect(true);
        return forward;
    }
}