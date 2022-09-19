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
	private JdbcUtill ju;// DB占쏙옙占쏙옙
	private ResultSet rs;
	
	public FreeBoardDao() {
		ju = JdbcUtill.getInstance();
	}

	// 삽입 (C)
	public int insert(FreeBoardVO fvo) { // BoardVO클占쏙옙占쏙옙占쏙옙占쏙옙 占쌨아울옙
		Connection con = null;
		PreparedStatement pstmt = null;
		int ret = -1; // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 -1占쏙옙 占쏙옙占쏙옙占싱듸옙
		String query = "insert into freeboard(num, title, writer, content, regdate, cnt, likenum, board_file) values(board_seq.nextval,  ?, ?, ?, sysdate, 0, 0, ?)";
		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query); // con占쏙옙占싸븝옙占쏙옙 prepareStatement占쏙옙 占쌀뤄옙 占쏙옙占쏙옙
			pstmt.setString(1, fvo.getTitle()); // 첫번째 ? 값에 삽입
			pstmt.setString(2, fvo.getWriter()); // 2번째 ? 값에 삽입
			pstmt.setString(3, fvo.getContent()); // 3번째 ? 값에 삽입
			pstmt.setString(4, fvo.getBoard_file()); // 4번째 ? 값에 삽입
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

	// 조회 (R)
	
	public List<FreeBoardVO> selectAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select num, title, writer, content, regdate, cnt, likenum, board_file from freeboard order by num desc";
		ArrayList<FreeBoardVO> ls = new ArrayList<FreeBoardVO>(); // 커占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙트占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙漫占� 占쏙옙占쏙옙
		try {
			con = ju.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				FreeBoardVO fvo = new FreeBoardVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						new Date(rs.getDate(5).getTime()), rs.getInt(6), rs.getInt(7), rs.getString(8));


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
	
	// 상세조회 (R)
	public FreeBoardVO selectOne(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeBoardVO fvo = null;
		String query = "select num, title, writer, content, regdate, cnt from freeboard where num = ?";
		ArrayList<FreeBoardVO> ls = new ArrayList<FreeBoardVO>(); // 커占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙트占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙漫占� 占쏙옙占쏙옙
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				updateCnt(num); // 占쏙옙회占쏙옙 占쏙옙占쏙옙
				 fvo = new FreeBoardVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						new Date(rs.getDate(5).getTime()), rs.getInt(6)+1, rs.getInt(7), rs.getString(8));

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
	// 수정 (U)
	
	public int update(FreeBoardVO fvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int ret = -1; // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 -1占쏙옙 占쏙옙占쏙옙占싱듸옙
		String query = "update freeboard set title=?, content=? board_file=? where num=?";
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query); // con占쏙옙占싸븝옙占쏙옙 prepareStatement占쏙옙 占쌀뤄옙 占쏙옙占쏙옙
			pstmt.setString(1, fvo.getTitle()); // 첫占쏙옙째 占식띰옙占쏙옙占� 占쏙옙占싸듸옙(?) 占쏙옙占쏙옙 占쏙옙占쏙옙
			pstmt.setString(2, fvo.getContent()); // 占싸뱄옙째 占쏙옙占싸듸옙 占쏙옙占쏙옙
			pstmt.setString(3, fvo.getBoard_file());
			pstmt.setInt(4, fvo.getNum()); // 占쏙옙占쏙옙째 占쏙옙占싸듸옙 占쏙옙占쏙옙
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
	// 占쏙옙회占쏙옙 占쏙옙占쏙옙
	public int updateCnt(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int ret = -1; // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 -1占쏙옙 占쏙옙占쏙옙占싱듸옙
		String query = "update freeboard set cnt=cnt+1 where num=?";
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query); // con占쏙옙占싸븝옙占쏙옙 prepareStatement占쏙옙 占쌀뤄옙 占쏙옙占쏙옙
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
	// 占쏙옙占쏙옙 (D)
	public int delete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int ret = -1; // 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 -1占쏙옙 占쏙옙占쏙옙占싱듸옙
		String query = "delete from freeboard where num = ?";
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query); // con占쏙옙占싸븝옙占쏙옙 prepareStatement占쏙옙 占쌀뤄옙 占쏙옙占쏙옙
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
