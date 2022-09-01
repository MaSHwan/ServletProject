package board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.JdbcUtill;

public class BoardDao {
	private JdbcUtill ju;// DB접근
	private ResultSet rs;
	
	public BoardDao() {
		ju = JdbcUtill.getInstance();
	}

	// 삽입 (C)
	public int insert(BoardVO vo) { // BoardVO클래스에서 받아옴
		Connection con = null;
		PreparedStatement pstmt = null;
		int ret = -1; // 문제가 있을시 -1로 리턴이됨
		String query = "insert into board(num, title, writer, content, regdate, cnt, likenum) values(board_seq.nextval,  ?, ?, ?, sysdate, 0,0)";
		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query); // con으로부터 prepareStatement를 불러 세팅
			pstmt.setString(1, vo.getTitle()); // 첫번째 파라미터 바인딩(?) 변수 설정
			pstmt.setString(2, vo.getWriter()); // 두번째 바인딩 설정
			pstmt.setString(3, vo.getContent()); // 세번째 바인딩 설정
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
	
	public List<BoardVO> selectAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select num, title, writer, content, regdate, cnt from board order by num desc";
		ArrayList<BoardVO> ls = new ArrayList<BoardVO>(); // 커리의 결과는 리스트로 만들어져야해서 생성
		try {
			con = ju.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				BoardVO vo = new BoardVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						new Date(rs.getDate(5).getTime()), rs.getInt(6));

				ls.add(vo);
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
	
	// 조회 (R)
	public BoardVO selectOne(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO vo = null;
		String query = "select num, title, writer, content, regdate, cnt from board where num = ?";
		ArrayList<BoardVO> ls = new ArrayList<BoardVO>(); // 커리의 결과는 리스트로 만들어져야해서 생성
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				updateCnt(num); // 조회수 증가
				 vo = new BoardVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						new Date(rs.getDate(5).getTime()), rs.getInt(6)+1);

				ls.add(vo);
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
		return vo;
	}
	// 수정 (U)
	
	public int update(BoardVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int ret = -1; // 문제가 있을시 -1로 리턴이됨
		String query = "update board set title=?, content=? where num=?";
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query); // con으로부터 prepareStatement를 불러 세팅
			pstmt.setString(1, vo.getTitle()); // 첫번째 파라미터 바인딩(?) 변수 설정
			pstmt.setString(2, vo.getContent()); // 두번째 바인딩 설정
			pstmt.setInt(3, vo.getNum()); // 세번째 바인딩 설정
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
	// 조회수 증가
	public int updateCnt(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int ret = -1; // 문제가 있을시 -1로 리턴이됨
		String query = "update board set cnt=cnt+1 where num=?";
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query); // con으로부터 prepareStatement를 불러 세팅
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
	// 삭제 (D)
	public int delete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int ret = -1; // 문제가 있을시 -1로 리턴이됨
		String query = "delete from board where num = ?";
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query); // con으로부터 prepareStatement를 불러 세팅
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
