package board.service;

import board.vo.BoardVO;
import board.vo.PagingVO;

public interface BoardService {
	// 로직 1개당 1개의 메서드로 작성
	// 1. 목록보기
	PagingVO<BoardVO> selectList(int currentPage, int pageSize, int blockSize);
	// 2. 1개 얻기 -- 내용보기, 수정폼, 삭제폼
	BoardVO selectByIdx(int idx, boolean isHit); // isHit는 조회수 증가여부 판단
	// 3. 새글쓰기
	int insert(BoardVO vo);
	// 4. 수정하기
	int update(BoardVO vo);
	// 5. 삭제하기
	int delete(BoardVO vo);
}
