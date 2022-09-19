package com.travel.action.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.travel.common.Action;
import com.travel.common.ActionForward;
import com.travel.common.LoginManager;
import com.travel.common.RegExp;
import com.travel.service.BoardService;
import com.travel.vo.MemberVo;

import static com.travel.common.RegExp.*;

public class MemberLoginProcAction implements Action {
    @Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //view���� �� �޾ƿ���
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        //�� ����ִ���,����(���Խ�) �˻�
        if (id == null || id.equals("") || !RegExp.checkString(MEMBER_ID, id)
                || pwd == null || pwd.equals("") || !RegExp.checkString(MEMBER_PWD, pwd)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('�߸��� �����Դϴ�');location.href='/';</script>");
            out.close();
            return null;
        }
        
        BoardService service = new BoardService();
        
        //memberVo�� �� set
        MemberVo memberVo = service.getMember(id); //service���� sq,id,pwd��������

        //��� �� �� ����ġ�� ��
        if(memberVo==null||!BCrypt.checkpw(pwd,memberVo.getPwd())){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('�α��� ������ Ȯ���� �ּ���.');history.back();</script>");
            out.close();
            return null;
        }

        memberVo.setLgn_fl(true);   //�α��� ���·� ����

        if(!service.loginMember(memberVo)){ //service���� �α���ó�� ȣ��(isSucess�޾ƿ�)
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('�α��� ó���� �����Ͽ����ϴ�.');location.href='/';</script>");
            out.close();
            return null;
        }

        LoginManager lm = LoginManager.getInstance();
        lm.setSession(request.getSession(),memberVo.getId()); //���� id�̸����� ���ǵ��,valueBound()ȣ��

        //������������ �̵�
        ActionForward forward = new ActionForward();
        forward.setPath("/views/login.jsp");
        forward.setRedirect(true);
        return forward;
    }
}
