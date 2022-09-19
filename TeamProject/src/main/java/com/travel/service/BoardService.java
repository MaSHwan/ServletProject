package com.travel.service;

import static com.travel.common.JdbcUtil.close;
import static com.travel.common.JdbcUtil.commit;
import static com.travel.common.JdbcUtil.getConnection;
import static com.travel.common.JdbcUtil.rollback;

import java.sql.Connection;

import com.travel.dao.BoardDAO;
import com.travel.vo.MemberVo;

public class BoardService {

	//회원가입 메소드
    public boolean joinMember(MemberVo memberVo) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess = false;

        int result = dao.insertMember(memberVo); //dao호출
        if (result == 1) {
            commit(con);
            isSucess=true;
        }else{
            rollback(con);
        }
        close(con);
        return isSucess;
    }
    
  //입력한 아이디에 해당하는 멤버데이터 가져오는 메소드
    public MemberVo getMember(String id){
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        MemberVo vo = dao.getMember(id); //dao호출
        close(con);
        return vo;
    }
    
  //로그인 메소드
    public boolean loginMember(MemberVo memberVo){
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess=false;

        int result = dao.updateLoginState(memberVo); //dao호출
        if(result==1){
            commit(con);
            isSucess=true;
        }else{
            rollback(con);
        }
        close(con);
        return isSucess;
    }
    
  //로그아웃 메소드
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
    
    
  //아이디 중복검사 메소드
    public int idCheck(String id){
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count=dao.checkId(id); //dao호출
        close(con);
        return count;
    }
    // email 중복검사 메소드
    public int emailCheck(String email){
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        int count=dao.checkEmail(email); //dao호출
        close(con);
        return count;
    }
    // 멤버삭제
    public boolean deleteMember(String id) {
        //세팅
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        boolean isSucess = false;

        int count = dao.deleteMember(id);
        if (count > 0) {    //성공
            commit(con);
            isSucess = true;
        } else {          //실패
            rollback(con);
        }
        close(con);
        return isSucess;
    }
    
    // 프로필 업데이트
    public boolean profileUpdate(MemberVo vo) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        //그냥 count넘겨도 되지만 boolean으로 함
        boolean isSucess = false;

        int count = dao.profileUpdate(vo);
        if (count > 0) {    //성공
            commit(con);
            isSucess = true;

        } else {          //실패
            rollback(con);
        }
        close(con);
        return isSucess;
    }
    
    public boolean profileImgUpdate(MemberVo vo){
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        //그냥 count넘겨도 되지만 boolean으로 함
        boolean isSucess = false;

        int count = dao.profileImgUpdate(vo);
        if (count > 0) {    //성공
            commit(con);
            isSucess = true;

        } else {          //실패
            rollback(con);
        }
        close(con);
        return isSucess;
    }
    
}
