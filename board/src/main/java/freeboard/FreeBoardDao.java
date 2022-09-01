package freeboard;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import board.BoardVO;
import common.JdbcUtill;

public class FreeBoardDao {
	private JdbcUtill ju;// DB����
	private ResultSet rs;
	
	public FreeBoardDao() {
		ju = JdbcUtill.getInstance();
	}

	// ���� (C)
	public int insert(FreeBoardVO fvo) { // BoardVOŬ�������� �޾ƿ�
		Connection con = null;
		PreparedStatement pstmt = null;
		int ret = -1; // ������ ������ -1�� �����̵�
		String query = "insert into freeboard(num, title, writer, content, regdate, cnt, likenum) values(board_seq.nextval,  ?, ?, ?, sysdate, 0,0)";
		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query); // con���κ��� prepareStatement�� �ҷ� ����
			pstmt.setString(1, fvo.getTitle()); // ù��° �Ķ���� ���ε�(?) ���� ����
			pstmt.setString(2, fvo.getWriter()); // �ι�° ���ε� ����
			pstmt.setString(3, fvo.getContent()); // ����° ���ε� ����
			ret = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {

				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	} // end insert
	
//	public int insert(Board_LikeVO vlo) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		int ret = -1;
//		String query = "insert into board_like (likeno) values ?";
//	}

	// ��ȸ (R)
	
	public List<FreeBoardVO> selectAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select num, title, writer, content, regdate, cnt from freeboard order by num desc";
		ArrayList<FreeBoardVO> ls = new ArrayList<FreeBoardVO>(); // Ŀ���� ����� ����Ʈ�� ����������ؼ� ����
		try {
			con = ju.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				FreeBoardVO fvo = new FreeBoardVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						new Date(rs.getDate(5).getTime()), rs.getInt(6));

//				ls.add(fvo);
				ls.add(fvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {

				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {

				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ls;
	}
	
	// ��ȸ (R)
	public FreeBoardVO selectOne(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeBoardVO fvo = null;
		String query = "select num, title, writer, content, regdate, cnt from freeboard where num = ?";
		ArrayList<FreeBoardVO> ls = new ArrayList<FreeBoardVO>(); // Ŀ���� ����� ����Ʈ�� ����������ؼ� ����
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				updateCnt(num); // ��ȸ�� ����
				 fvo = new FreeBoardVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						new Date(rs.getDate(5).getTime()), rs.getInt(6)+1);

				ls.add(fvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {

				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {

				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return fvo;
	}
	// ���� (U)
	
	public int update(FreeBoardVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int ret = -1; // ������ ������ -1�� �����̵�
		String query = "update freeboard set title=?, content=? where num=?";
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query); // con���κ��� prepareStatement�� �ҷ� ����
			pstmt.setString(1, vo.getTitle()); // ù��° �Ķ���� ���ε�(?) ���� ����
			pstmt.setString(2, vo.getContent()); // �ι�° ���ε� ����
			pstmt.setInt(3, vo.getNum()); // ����° ���ε� ����
			ret = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {

				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	// ��ȸ�� ����
	public int updateCnt(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int ret = -1; // ������ ������ -1�� �����̵�
		String query = "update freeboard set cnt=cnt+1 where num=?";
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query); // con���κ��� prepareStatement�� �ҷ� ����
			pstmt.setInt(1, num);
			ret = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {

				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	// ���� (D)
	public int delete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int ret = -1; // ������ ������ -1�� �����̵�
		String query = "delete from freeboard where num = ?";
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query); // con���κ��� prepareStatement�� �ҷ� ����
			pstmt.setInt(1, num);
			ret = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {

				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {

				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}
	
}
