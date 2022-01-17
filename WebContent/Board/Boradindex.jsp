<%@page import="java.text.SimpleDateFormat"%>
<%@page import="board.service.BoardServiceImpl"%>
<%@page import="board.service.BoardService"%>
<%@page import="board.dao.BoardDAOImpl"%>
<%@page import="board.vo.BoardVO"%>
<%@page import="board.vo.PagingVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 공통코드 삽입 --%>
<%@ include file="include.jsp"%>
<%
	// 1페이지 분량의 글을 얻어온다.
PagingVO<BoardVO> pagingVO = BoardServiceImpl.getInstance().selectList(currentPage, pageSize, blockSize);
request.setAttribute("pv", pagingVO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 목록보기</title>
<%-- 부트스트랩을 사용하기 위한 준비 시작 --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- 부트스트랩을 사용하기 위한 준비 끝 --%>
<script type="text/javascript">
	
</script>
<style type="text/css">
table {
	width: 1300px;
	margin: auto;
	padding: 5px;
}

th {
	padding: 5px;
	border: 1px solid gray;
	background-color: silver;
	text-align: center;
}

td {
	padding: 5px;
	border: 1px solid gray;
	text-align: center;
}

.title {
	border: none;
	font-size: 20pt;
	text-align: center;
}

.sub_title {
	border: none;
	text-align: right;
}
/* 링크의 모양을 변경한다. */
a:link {
	color: black;
	text-decoration: none;
} /* 링크가 걸린모양 */
a:visited {
	color: black;
	text-decoration: none;
} /* 방문했던 링크 */
a:hover {
	color: black;
	text-decoration: none;
	font-weight: bold;
} /* 마우스오버시 모양 */
a:active {
	color: orange;
	text-decoration: none;
} /* 마우스 클릭시 모양 */
</style>
</head>
<body>
	<div style="text-align: center;">
		<h3>자유게시판</h3>
	</div>
	<div class="container">
		<div class="row">
			<div class="col"></div>
			<div class="col"></div>
			<div class="col" style="text-align: right;">
				<button class="btn btn-outline-success btn-sm"
					onclick="location.href='insert.jsp?p=${pv.currentPage}&s=${pv.pageSize }&b=${pv.blockSize }'">새글쓰기</button>
			</div>
		</div>
	</div>
	<br>
	<div class="container">
		<div class="row">
			<div class="col">
				<select>
					<option value="0">선택</option>
					<option value="Title">제목</option>
					<option value="userID">작성자</option>
				</select> <input type="text" placeholder="검색어 입력" name="searchText"
					maxlength="50">
				<button type="submit" class="btn btn-outline-success btn-sm">검색</button>
			</div>
			<div class="col"></div>
			<div class="col" style="text-align: right;">${pv.pageInfo }</div>
		</div>
	</div>
	<table>
		<tr>
			<th>No</th>
			<th width="50%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>IP</th>
		</tr>
		<%
			if (pagingVO.getTotalCount() == 0) {
			out.println("<tr><td colspan='6' align='center'>등록된 글이 없습니다.</td></tr>");
		} else {
			for (BoardVO vo : pagingVO.getList()) {
		%>
		<tr>
			<td><%=vo.getIdx()%></td>
			<td style="text-align: left;">
				<%
					String subject = vo.getSubject();
				subject = subject.replaceAll("<", "&lt;");
				if (subject.length() >= 20) {
					subject = subject.substring(0, 20) + "....";
				}
				%> <a title="<%=vo.getSubject() %>"
				href="view.jsp?p=${pv.currentPage }&s=${pv.pageSize }&b=${pv.blockSize }&idx=<%=vo.getIdx() %>&h=1">
					<%=subject%></a>
			</td>
			<td>
				<%
					String name = vo.getName();
				name = name.replaceAll("<", "&lt;");
				out.println(name);
				%>
			</td>
			<td>
				<%
					SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm");
				out.println(sdf.format(vo.getRegDate()));
				%>
			</td>
			<td><%=vo.getHit()%></td>
			<td><%=vo.getIp()%></td>
		</tr>
		<%
			}
		if (pagingVO.getTotalCount() > 0) {
		out.println("<tr><td colspan='6' align='center' style='border:none;'>" + pagingVO.getPageList() + "</td></tr>");
		}
		}
		%>

	</table>
</body>
</html>