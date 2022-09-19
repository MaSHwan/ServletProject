package com.travel.service;

import static com.travel.common.JdbcUtil.close;
import static com.travel.common.JdbcUtil.commit;
import static com.travel.common.JdbcUtil.getConnection;
import static com.travel.common.JdbcUtil.rollback;

import java.sql.Connection;

import com.travel.dao.BoardDAO;
import com.travel.vo.MemberVo;

public class BoardService {

	//ȸ������ �޼ҵ�
    public boolean joinMember(MemberVo memberVo) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess = false;

        int result = dao.insertMember(memberVo); //daoȣ��
        if (result == 1) {
            commit(con);
            isSucess=true;
        }else{
            rollback(con);
        }
        close(con);
        return isSucess;
    }
    
  //�Է��� ���̵� �ش��ϴ� ��������� �������� �޼ҵ�
    public MemberVo getMember(String id){
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        MemberVo vo = dao.getMember(id); //daoȣ��
        close(con);
        return vo;
    }
    
  //�α��� �޼ҵ�
    public boolean loginMember(MemberVo memberVo){
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess=false;

        int result = dao.updateLoginState(memberVo); //daoȣ��
        if(result==1){
            commit(con);
            isSucess=true;
        }else{
            rollback(con);
        }
        close(con);
        return isSucess;
    }
    
  //�α׾ƿ� �޼ҵ�
    public boolean logoutMember(MemberVo memberVo){
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess=false;

        int result=dao.updateLoginState(memberVo);
        if(result>0){
            commit(con);
            isSucess=true;
        }else{
            rollback(con);
        }
        close(con);
        return isSucess;
    }
    
    
  //���̵� �ߺ��˻� �޼ҵ�
    public int idCheck(String id){
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count=dao.checkId(id); //daoȣ��
        close(con);
        return count;
    }
    // email �ߺ��˻� �޼ҵ�
    public int emailCheck(String email){
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count=dao.checkEmail(email); //daoȣ��
        close(con);
        return count;
    }
    // �������
    public boolean deleteMember(String id) {
        //����
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess = false;

        int count = dao.deleteMember(id);
        if (count > 0) {    //����
            commit(con);
            isSucess = true;
        } else {          //����
            rollback(con);
        }
        close(con);
        return isSucess;
    }
    
    // ������ ������Ʈ
    public boolean profileUpdate(MemberVo vo) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        //�׳� count�Ѱܵ� ������ boolean���� ��
        boolean isSucess = false;

        int count = dao.profileUpdate(vo);
        if (count > 0) {    //����
            commit(con);
            isSucess = true;

        } else {          //����
            rollback(con);
        }
        close(con);
        return isSucess;
    }
    
    public boolean profileImgUpdate(MemberVo vo){
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        //�׳� count�Ѱܵ� ������ boolean���� ��
        boolean isSucess = false;

        int count = dao.profileImgUpdate(vo);
        if (count > 0) {    //����
            commit(con);
            isSucess = true;

        } else {          //����
            rollback(con);
        }
        close(con);
        return isSucess;
    }
    
}
