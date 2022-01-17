package board.service;

import java.sql.Connection;
import java.util.List;

import board.dao.BoardDAO;
import board.dao.BoardDAOImpl;
import board.vo.BoardVO;
import board.vo.PagingVO;
import JDBC.JDBCUtil;

public class BoardServiceImpl implements BoardService{
	//--------------------------------------------------------------------------------
	private static BoardService instance = new BoardServiceImpl();
	private BoardServiceImpl() {
		;
	}
	public static BoardService getInstance() {
		return instance;
	}
	//--------------------------------------------------------------------------------

	@Override
	public PagingVO<BoardVO> selectList(int currentPage, int pageSize, int blockSize) {
		// 1. 리턴 타입의 변수를 만든다. ----------------------------> 바뀌는 부분
		PagingVO<BoardVO> pagingVO = null;
		// 2. 컨넥션 변수를 만든다.
		Connection conn = null;
		// 3. DAO변수를 만든다.
		BoardDAO dao = null;
		// 4. 트랜젝션 처리를 만든다.
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false); // 1) 자동커밋 취소
			dao = BoardDAOImpl.getInstance();
			//---------------------------------------------
			// 5. 로직을 처리한다. ---- 이 부분만 변경된다. --------------------> 바뀌는 부분
			int totalCount = dao.selectCount(conn); // 전체개수얻기
			pagingVO = new PagingVO<BoardVO>(currentPage, pageSize, blockSize, totalCount); // 페이지 계산
			// 글목록을 얻어 페이징 객체에 넣기
			List<BoardVO> list = dao.selectList(conn, pagingVO.getStartNo(), pagingVO.getPageSize());
			pagingVO.setList(list);
			//---------------------------------------------
			conn.commit(); // 2) 에러가 없으면 DB에 명령 완료
		}catch (Exception e) {
			// 3) 에러가 나면 명령 취소
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			// 6. 닫기
			JDBCUtil.close(conn);
		}
		// 7. 처리 결과를 리턴한다.  ----------------------------> 바뀌는 부분
		return pagingVO;
	}

	@Override
	public BoardVO selectByIdx(int idx, boolean isHit) {
		// 1. 리턴 타입의 변수를 만든다. ----------------------------> 바뀌는 부분
		BoardVO vo = null;
		// 2. 컨넥션 변수를 만든다.
		Connection conn = null;
		// 3. DAO변수를 만든다.
		BoardDAO dao = null;
		// 4. 트랜젝션 처리를 만든다.
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false); // 1) 자동커밋 취소
			dao = BoardDAOImpl.getInstance();
			//---------------------------------------------
			// 5. 로직을 처리한다. ---- 이 부분만 변경된다. --------------------> 바뀌는 부분
			// 1) 글 1개를 가져온다.
			vo = dao.selectByIdx(conn, idx);
			// 2) 글이 존재하면 조회수를 isHit값에따라 증가 시켜준다.
			if(vo!=null) {
				if(isHit) {
					dao.incrementHit(conn, idx); // 조회수 증가
					// 이미 얻어온 vo의 조회수를 증가 시킨다. DB에서 다시 읽는것보다 빠르다.
					vo.setHit(vo.getHit()+1);
				}
			}
			//---------------------------------------------
			conn.commit(); // 2) 에러가 없으면 DB에 명령 완료
		}catch (Exception e) {
			// 3) 에러가 나면 명령 취소
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			// 6. 닫기
			JDBCUtil.close(conn);
		}
		// 7. 처리 결과를 리턴한다.  ----------------------------> 바뀌는 부분
		return vo;
	}

	@Override
	public int insert(BoardVO vo) {
		// 1. 리턴 타입의 변수를 만든다. ----------------------------> 바뀌는 부분
		int count = 0;
		// 2. 컨넥션 변수를 만든다.
		Connection conn = null;
		// 3. DAO변수를 만든다.
		BoardDAO dao = null;
		// 4. 트랜젝션 처리를 만든다.
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false); // 1) 자동커밋 취소
			dao = BoardDAOImpl.getInstance();
			//---------------------------------------------
			// 5. 로직을 처리한다. ---- 이 부분만 변경된다. --------------------> 바뀌는 부분
			if(vo!=null) {
				count = dao.insert(conn, vo);
			}
			//---------------------------------------------
			conn.commit(); // 2) 에러가 없으면 DB에 명령 완료
		}catch (Exception e) {
			// 3) 에러가 나면 명령 취소
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			// 6. 닫기
			JDBCUtil.close(conn);
		}
		// 7. 처리 결과를 리턴한다.  ----------------------------> 바뀌는 부분
		return count;
	}

	@Override
	public int update(BoardVO vo) {
		// 1. 리턴 타입의 변수를 만든다. ----------------------------> 바뀌는 부분
		int count = 0;
		// 2. 컨넥션 변수를 만든다.
		Connection conn = null;
		// 3. DAO변수를 만든다.
		BoardDAO dao = null;
		// 4. 트랜젝션 처리를 만든다.
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false); // 1) 자동커밋 취소
			dao = BoardDAOImpl.getInstance();
			//---------------------------------------------
			// 5. 로직을 처리한다. ---- 이 부분만 변경된다. --------------------> 바뀌는 부분
			if(vo!=null) { // 넘어온 데이터가 있고
				if(dao.passwordCheck(conn, vo.getIdx(), vo.getPassword())==1) { // 비번이 일치 한다면
					count = dao.update(conn, vo); // 수정한다.
				}
			}
			//---------------------------------------------
			conn.commit(); // 2) 에러가 없으면 DB에 명령 완료
		}catch (Exception e) {
			// 3) 에러가 나면 명령 취소
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			// 6. 닫기
			JDBCUtil.close(conn);
		}
		// 7. 처리 결과를 리턴한다.  ----------------------------> 바뀌는 부분
		return count;
	}

	@Override
	public int delete(BoardVO vo) {
		// 1. 리턴 타입의 변수를 만든다. ----------------------------> 바뀌는 부분
		int count = 0;
		// 2. 컨넥션 변수를 만든다.
		Connection conn = null;
		// 3. DAO변수를 만든다.
		BoardDAO dao = null;
		// 4. 트랜젝션 처리를 만든다.
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false); // 1) 자동커밋 취소
			dao = BoardDAOImpl.getInstance();
			//---------------------------------------------
			// 5. 로직을 처리한다. ---- 이 부분만 변경된다. --------------------> 바뀌는 부분
			if(vo!=null) { // 넘어온 데이터가 있고
				if(dao.passwordCheck(conn, vo.getIdx(), vo.getPassword())==1) { // 비번이 일치 한다면
					count = dao.delete(conn, vo.getIdx()); // 삭제한다.
				}
			}
			//---------------------------------------------
			conn.commit(); // 2) 에러가 없으면 DB에 명령 완료
		}catch (Exception e) {
			// 3) 에러가 나면 명령 취소
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		}finally {
			// 6. 닫기
			JDBCUtil.close(conn);
		}
		// 7. 처리 결과를 리턴한다.  ----------------------------> 바뀌는 부분
		return count;
	}

}
