package com.travel.action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import com.travel.common.Action;
import com.travel.common.ActionForward;
import com.travel.common.LoginManager;
import com.travel.service.BoardService;
import com.travel.vo.MemberVo;

import static com.travel.common.RegExp.MEMBER_ID;
import static com.travel.common.RegExp.MEMBER_PWD;

public class MemberLogoutAction implements Action {
    @Override
    public ActionForward execute
            (HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        LoginManager lm = LoginManager.getInstance();
        String id = lm.getMemberId(session);

        if (id != null) {
            lm.removeSession(id); //���ǻ���,�α׾ƿ� ó��
        }
        
        //������������ �̵�
        ActionForward forward = new ActionForward();
        forward.setPath("/");
        forward.setRedirect(true);
        return forward;
    }

    public void logoutProc(String id){
        MemberVo memberVo = new MemberVo();
        memberVo.setId(id);     //vo�� �α׾ƿ��� id����
        memberVo.setLgn_fl(false);  //vo�� �α׾ƿ� ���·� ����

        BoardService service = new BoardService();
        if(!service.logoutMember(memberVo)){
            System.out.println(id + "ȸ���� �α׾ƿ� ó���� �����Ͽ����ϴ�.");
        }
    }
}