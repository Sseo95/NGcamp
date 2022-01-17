package board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.vo.BoardVO;

public interface BoardDAO {
	// 1. 개수 구하기
	int selectCount(Connection conn) throws SQLException;
	// 2. 1개얻기
	BoardVO selectByIdx(Connection conn, int idx) throws SQLException;
	// 3. 1페이지 얻기
	List<BoardVO> selectList(Connection conn, int startNo, int pageSize) throws SQLException;
	// 4. 저장
	int insert(Connection conn, BoardVO vo) throws SQLException;
	// 5. 수정
	int update(Connection conn, BoardVO vo) throws SQLException;
	// 6. 삭제
	int delete(Connection conn, int idx) throws SQLException;
	// 7. 비번확인
	int passwordCheck(Connection conn, int idx, String password) throws SQLException;
	// 8. 조회수 증가
	int incrementHit(Connection conn, int idx) throws SQLException;
}
