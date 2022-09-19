package com.travel.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JdbcUtil {	
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			//Ŀ�ؼ� Ǯ�� db����
			DataSource ds = (DataSource) envCtx.lookup("jdbc/hyeon4137");
			con = ds.getConnection();
			con.setAutoCommit(false); //autocommit ���� ����Ŀ��,�ѹ�
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//Connection �ڿ� ����
	public static void close(Connection con) {
		if (con!=null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//PreparedStatement �ڿ� ����
	public static void close(PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//ResultSet �ڿ� ����
	public static void close(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//����commit
	public static void commit(Connection con) {
		if (con!=null) {
			try {
				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//����rollback
	public static void rollback(Connection con) {
		if (con!=null) {
			try {
				con.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}