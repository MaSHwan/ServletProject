package com.travel.action.member;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travel.common.Action;
import com.travel.common.ActionForward;
import com.travel.common.LoginManager;
import com.travel.service.BoardService;
import com.travel.vo.MemberVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class ProfileImgUpdateAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        LoginManager lm = LoginManager.getInstance();
        String id = lm.getMemberId(request.getSession());

        BoardService service = new BoardService();
        MemberVo vo = new MemberVo();

        String saveDirectory = request.getServletContext().getRealPath("/resources/img");

        File saveDir = new File(saveDirectory);
        if (!saveDir.exists())
            saveDir.mkdirs();
        int maxPostSize = 1024 * 1024 * 10;
        String encoding = "UTF-8";

        FileRenamePolicy policy = new DefaultFileRenamePolicy();
        MultipartRequest mrequest
                = new MultipartRequest(request //MultipartRequest�� ����� ���� request
                , saveDirectory //���� ��ġ
                , maxPostSize //�ִ�ũ��
                , encoding //���ڵ� Ÿ��
                , policy); //���� ��å

        String originalFileName = mrequest.getOriginalFileName("imgFile"); //���� �̸�
        String filesystemName = mrequest.getFilesystemName("imgFile"); //����

        vo.setId(id);
        vo.setAddress(saveDirectory);
        vo.setFilename(originalFileName);
        vo.setNewFileName(filesystemName);

        if(!service.profileImgUpdate(vo)){
            out.println("<script>alert('ȸ������ ������ �����߽��ϴ�.');history.back();</script>");
            out.close();
            return null;
        }

        out.println("<script>" +
                "alert('ȸ������ ���� ����');" +
                "location.href='/profile.do?id="+id+"';" +
                "</script>");
        out.close();

        ActionForward forward = new ActionForward();
        forward.setPath("/profile.do?id=" + id);
        return forward;
    }
}