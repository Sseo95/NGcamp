package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import board.vo.BoardVO;
import JDBC.JDBCUtil;

public class BoardDAOImpl implements BoardDAO{
	//---------------------------------------------------------------------------------
	private static BoardDAO instance = new BoardDAOImpl();
	private BoardDAOImpl() {
		;
	}
	public static BoardDAO getInstance() {
		return instance;
	}
	//---------------------------------------------------------------------------------
	// SQL명령어 1개당 1개의 메서드로 만든다.
	/* 0. 리턴타입의 변수를 선언한다.
	 * 1. 사용할 SQL명령을 만든다.
	 * 2. 명령 객체를 만들어 미완성 SQL명령을 ?를 채워서 완성된 명령으로 만든다. 
	 * 3. 결과를 얻어온다.
	 * 4. 얻어온 결과를 리턴타입의 변수에 넣는다.
	 * 5. 사용한 객체를 닫는다.
	 * 6. 결과를 리턴한다.
	 */

	@Override
	public int selectCount(Connection conn) throws SQLException {
		//  0. 리턴타입의 변수를 선언한다.
		int count = 0;
		//  1. 사용할 SQL명령을 만든다.
		String sql = "select count(*) from board";
		// 2. 명령 객체를 만들어 미완성 SQL명령을 ?를 채워서 완성된 명령으로 만든다.
		Statement stmt = conn.createStatement();
		// 3. 결과를 얻어온다.
		ResultSet rs = stmt.executeQuery(sql);
		// 4. 얻어온 결과를 리턴타입의 변수에 넣는다.
		rs.next();
		count = rs.getInt(1);
		// 5. 사용한 객체를 닫는다.
		JDBCUtil.close(rs);
		JDBCUtil.close(stmt);
		//  6. 결과를 리턴한다.
		return count;
	}

	@Override
	public BoardVO selectByIdx(Connection conn, int idx) throws SQLException {
		//  0. 리턴타입의 변수를 선언한다.
		BoardVO vo = null;
		//  1. 사용할 SQL명령을 만든다.
		String sql = "select * from board where idx=?";
		// 2. 명령 객체를 만들어 미완성 SQL명령을 ?를 채워서 완성된 명령으로 만든다.
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, idx);
		// 3. 결과를 얻어온다.
		ResultSet rs = pstmt.executeQuery();
		// 4. 얻어온 결과를 리턴타입의 변수에 넣는다.
		if(rs.next()) {
			vo = new BoardVO();
			// 비어 있는 vo의 setter를 불러 내용을 채워준다.
			vo.setIdx(rs.getInt("idx"));
			vo.setName(rs.getString("name"));
			vo.setPassword(rs.getString("password"));
			vo.setSubject(rs.getString("subject"));
			vo.setContent(rs.getString("content"));
			vo.setRegDate(rs.getTimestamp("regDate"));
			vo.setHit(rs.getInt("hit"));
			vo.setIp(rs.getString("ip"));
		}
		// 5. 사용한 객체를 닫는다.
		JDBCUtil.close(rs);
		JDBCUtil.close(pstmt);
		//  6. 결과를 리턴한다.
		return vo;
	}

	@Override
	public List<BoardVO> selectList(Connection conn, int startNo, int pageSize) throws SQLException {
			//  0. 리턴타입의 변수를 선언한다.
			List<BoardVO> list = null;
			//  1. 사용할 SQL명령을 만든다.
			String sql = "select * from board order by idx desc limit ?, ?";
			// 2. 명령 객체를 만들어 미완성 SQL명령을 ?를 채워서 완성된 명령으로 만든다.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, pageSize);
			// 3. 결과를 얻어온다.
			ResultSet rs = pstmt.executeQuery();
			// 4. 얻어온 결과를 리턴타입의 변수에 넣는다.
			if(rs.next()) {
				list = new ArrayList<BoardVO>();
				do {
					BoardVO vo = new BoardVO();
					// 비어 있는 vo의 setter를 불러 내용을 채워준다.
					vo.setIdx(rs.getInt("idx"));
					vo.setName(rs.getString("name"));
					vo.setPassword(rs.getString("password"));
					vo.setSubject(rs.getString("subject"));
					vo.setContent(rs.getString("content"));
					vo.setRegDate(rs.getTimestamp("regDate"));
					vo.setHit(rs.getInt("hit"));
					vo.setIp(rs.getString("ip"));

					list.add(vo);
				}while(rs.next());
			}
			// 5. 사용한 객체를 닫는다.
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			//  6. 결과를 리턴한다.
			return list;
	}

	@Override
	public int insert(Connection conn, BoardVO vo) throws SQLException {
		//  0. 리턴타입의 변수를 선언한다.
		int count = 0;
		//  1. 사용할 SQL명령을 만든다.
		String sql = "insert into board  (name,password,subject,content,ip) values (?, password(?), ?,?,?)";
		// 2. 명령 객체를 만들어 미완성 SQL명령을 ?를 채워서 완성된 명령으로 만든다.
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPassword());
		pstmt.setString(3, vo.getSubject());
		pstmt.setString(4, vo.getContent());
		pstmt.setString(5, vo.getIp());
		// 3. 결과를 얻어온다.
		// 4. 얻어온 결과를 리턴타입의 변수에 넣는다.
		count = pstmt.executeUpdate();
		// 5. 사용한 객체를 닫는다.
		JDBCUtil.close(pstmt);
		//  6. 결과를 리턴한다.
		return count;
	}

	@Override
	public int update(Connection conn, BoardVO vo) throws SQLException {
		//  0. 리턴타입의 변수를 선언한다.
		int count = 0;
		//  1. 사용할 SQL명령을 만든다.
		String sql = "update board set subject=?, content=?, regDate=now(), ip=? where idx=?";
		// 2. 명령 객체를 만들어 미완성 SQL명령을 ?를 채워서 완성된 명령으로 만든다.
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getSubject());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getIp());
		pstmt.setInt(4, vo.getIdx());
		// 3. 결과를 얻어온다.
		// 4. 얻어온 결과를 리턴타입의 변수에 넣는다.
		count = pstmt.executeUpdate();
		// 5. 사용한 객체를 닫는다.
		JDBCUtil.close(pstmt);
		//  6. 결과를 리턴한다.
		return count;
	}

	@Override
	public int delete(Connection conn, int idx) throws SQLException {
		//  0. 리턴타입의 변수를 선언한다.
		int count = 0;
		//  1. 사용할 SQL명령을 만든다.
		String sql = "delete from board where idx=?";
		// 2. 명령 객체를 만들어 미완성 SQL명령을 ?를 채워서 완성된 명령으로 만든다.
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, idx);
		// 3. 결과를 얻어온다.
		// 4. 얻어온 결과를 리턴타입의 변수에 넣는다.
		count = pstmt.executeUpdate();
		// 5. 사용한 객체를 닫는다.
		JDBCUtil.close(pstmt);
		//  6. 결과를 리턴한다.
		return count;
	}

	@Override
	public int passwordCheck(Connection conn, int idx, String password) throws SQLException {
		//  0. 리턴타입의 변수를 선언한다.
		int count = 0;
		//  1. 사용할 SQL명령을 만든다.
		String sql = "select count(*) from board where idx=? and password=password(?)";
		// 2. 명령 객체를 만들어 미완성 SQL명령을 ?를 채워서 완성된 명령으로 만든다.
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, idx);
		pstmt.setString(2, password);
		// 3. 결과를 얻어온다.
		// 4. 얻어온 결과를 리턴타입의 변수에 넣는다.
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		count = rs.getInt(1);
		// 5. 사용한 객체를 닫는다.
		JDBCUtil.close(rs);
		JDBCUtil.close(pstmt);
		//  6. 결과를 리턴한다.
		return count;	
	}

	@Override
	public int incrementHit(Connection conn, int idx) throws SQLException {
		//  0. 리턴타입의 변수를 선언한다.
		int count = 0;
		//  1. 사용할 SQL명령을 만든다.
		String sql = "update board set hit = hit+1 where idx=?";
		// 2. 명령 객체를 만들어 미완성 SQL명령을 ?를 채워서 완성된 명령으로 만든다.
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, idx);
		// 3. 결과를 얻어온다.
		// 4. 얻어온 결과를 리턴타입의 변수에 넣는다.
		count = pstmt.executeUpdate();
		// 5. 사용한 객체를 닫는다.
		JDBCUtil.close(pstmt);
		//  6. 결과를 리턴한다.
		return count;
	}

}
