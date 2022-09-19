package com.travel.dao;

import java.sql.Connection;

import com.travel.vo.*;
import com.travel.common.*;


import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import static com.travel.common.JdbcUtil.close;

public class BoardDAO {

	private Connection con;
	
	// �̱��� ���� �⺻�����ڸ�  private�� ���� �Ұ����ϰ���
	private BoardDAO() {
		
	}
	
	//getInstancs�� ���ؼ��� ���� �����ϰ� ��
	public static BoardDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	//LazyHolder.INSTANCE�� �����ϴ� ���� class�� �ε��Ǹ� �ʱ�ȭ ����
	private static class LazyHolder{
		private static final BoardDAO INSTANCE = new BoardDAO();
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	// ȸ������
	public int insertMember(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("insert into member(id,pwd,nickname,email) value (?, ?, ?, ?");
			pstmt.setString(1,vo.getId());
            pstmt.setString(2,vo.getPwd());
            pstmt.setString(3,vo.getNickname());
            pstmt.setString(4,vo.getEmail());
            count = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return count;
	}

	 public MemberVo getMember(String id){
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        MemberVo vo = null;

	        try{
	            pstmt=con.prepareStatement("select sq,id,pwd,nickname,image from member where binary(id)=?");
	            pstmt.setString(1,id);
	            rs=pstmt.executeQuery();
	            while (rs.next()){
	                vo=new MemberVo();
	                vo.setMem_sq(rs.getInt("sq"));
	                vo.setId(rs.getString("id"));
	                vo.setPwd(rs.getString("pwd"));
	                vo.setNickname(rs.getString("nickname"));
	                vo.setNewFileName(rs.getString("image"));
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	        }finally {
	            close(rs);
	            close(pstmt);
	        }
	        return vo;
	    }
	 
	//getMember�޼ҵ忡�� ���� mem_sq��ȣ�� �α��λ��·� ����
	    public int updateLoginState(MemberVo vo){
	        PreparedStatement pstmt=null;
	        int count=0;
	        try{
	            pstmt = con.prepareStatement("update member set lgn_fl=? where sq=?");
	            pstmt.setBoolean(1,vo.isLgn_fl());
	            pstmt.setInt(2,vo.getMem_sq());
	            count=pstmt.executeUpdate();
	        }catch (Exception e){
	            e.printStackTrace();
	        }finally {
	            close(pstmt);
	        }
	        return count;
	    }
	
	
	//���̵� �ߺ��˻�
    public int checkId(String id){
        PreparedStatement pstmt=null;
        ResultSet rs = null;
        int count=0;

        try{
            pstmt=con.prepareStatement("select count(id) as cnt from member where id=?");
            pstmt.setString(1,id);
            rs=pstmt.executeQuery();
            while(rs.next()){
                count=rs.getInt("cnt"); //0�̸� ���ߺ�
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return count;
    }
	
	
    // email �ߺ� Ȯ��
    public int checkEmail(String email){
        PreparedStatement pstmt=null;
        ResultSet rs = null;
        int count=0;

        try{
            pstmt=con.prepareStatement("select count(email) as cnt from member where email=?");
            pstmt.setString(1,email);
            rs=pstmt.executeQuery();
            while(rs.next()){
                count=rs.getInt("cnt"); //0�̸� ���ߺ�
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return count;
    }
    
    
    public int getMemberSequence(String id){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int sq = 0;
        try{
            //���� �α��ε� id�� �ش��ϴ� ������ȣ ��ȸ
            pstmt = con.prepareStatement("select sq from member where id=?");
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            while (rs.next()){
                sq=rs.getInt("sq");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return sq;
    }
    
	
    // ������ ������Ʈ
    public int profileUpdate(MemberVo vo){
        PreparedStatement pstmt = null;
        int count = 0;
        try{
            //���� �α��ε� id�� �ش��ϴ� ������ȣ ��ȸ
            pstmt = con.prepareStatement("update member set nickname=? where id=?");
            pstmt.setString(1,vo.getNickname());
            pstmt.setString(2,vo.getId());
            count=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }
    
    // ������ �̹��� ������Ʈ
    public int profileImgUpdate(MemberVo vo){
        PreparedStatement pstmt = null;
        int count = 0;
        try{
            //���� �α��ε� id�� �ش��ϴ� ������ȣ ��ȸ
            pstmt = con.prepareStatement("update member set image=? where id=?");
            pstmt.setString(1,vo.getNewFileName());
            pstmt.setString(2,vo.getId());
            count=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }
    
    // ��� ����
    public int deleteMember(String id){
        PreparedStatement pstmt = null;
        int count = 0;
        try{
            //���� �α��ε� id�� �ش��ϴ� ������ȣ ��ȸ
            pstmt = con.prepareStatement("delete from member where id=?");
            pstmt.setString(1,id);
            count=pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }
    
    
    
    
}
