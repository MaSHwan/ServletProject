package common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcUtill {
	private static JdbcUtill instance = new JdbcUtill();
	
	private static DataSource ds; // ds�� �����ͼҽ� ������Ѿ���
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����");
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myOracle");
			System.out.println("Connection Pool ����");
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
	}
	
	private JdbcUtill() {}
	
	public static JdbcUtill getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		return ds.getConnection(); // Ǯ���� Ŀ�ؼ� ��ȯ
	}
}
