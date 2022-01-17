<%@page import="board.service.BoardServiceImpl"%>
<%@page import="board.service.BoardService"%>
<%@page import="board.vo.BoardVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 공통코드 삽입 --%>
<%@ include file="include.jsp" %>
<%
	// 1개 분량의 글을 얻어온다.
	// incHit가 1이면 조회수가 증가되고 0이면 증가되지 않는다.
	BoardVO vo = BoardServiceImpl.getInstance().selectByIdx(idx, incHit==1); 
	request.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 내용보기</title>
<%-- 부트스트랩을 사용하기 위한 준비 시작 --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- 부트스트랩을 사용하기 위한 준비 끝 --%>
<script type="text/javascript">

</script>
<style type="text/css">
	table { width: 800px; margin: auto; padding: 5px;}
	th {padding: 5px; border: 1px solid gray; background-color: silver;text-align: center;}
	td {padding: 5px; border: 1px solid gray; }
	.title {border: none; font-size: 20pt; text-align: center;}
	.item { width: 100px; background-color: silver; text-align: right;}
</style>
</head>
<body>
	<table>
		<tr>
			<td colspan="4" class="title">자유게시판 내용보기</td>
		</tr>
		<tr>
			<td class="item">이름</td>
			<td>
				<%
					// 혹시라도 이름에 태그가 입력되었다면 태그를 특수문자로 변경
					String name = vo.getName();
					name = name.replaceAll("<", "&lt;");
					out.println(name);
				%>
			</td>
			<td class="item">조회수</td>
			<td>${vo.hit }</td>
		</tr>
		<tr>
			<td class="item">작성일</td>
			<td>${vo.regDate }</td>
			<td class="item">IP</td>
			<td>${vo.ip }</td>
		</tr>
		<tr>
			<td class="item">제목</td>
			<td colspan="3">
				<%
					// 혹시라도 제목에 태그가 입력되었다면 태그를 특수문자로 변경
					String subject = vo.getSubject();
					subject = subject.replaceAll("<", "&lt;");
					out.println(subject);
				%>		
			</td>
		</tr>
		<tr>
			<td class="item">내용</td>
			<td colspan="3">
				<%
					// 혹시라도 제목에 태그가 입력되었다면 태그를 특수문자로 변경
					String content = vo.getContent();
					content = content.replaceAll("<", "&lt;");
					// 엔터를 <br>로 바꿔줘야 줄바꿈이 나타난다.
					content = content.replaceAll("\n", "<br>");
					out.println(content);
				%>		
			</td>
		</tr>
		<tr>
			<td colspan="4" style="border: none;text-align: right;">
				<button class="btn btn-outline-success btn-sm" 
				onclick="location.href='update.jsp?p=${p}&s=${s}&b=${b}&idx=${idx }&h=0'">수정</button>
				<button class="btn btn-outline-success btn-sm" 
				onclick="location.href='delete.jsp?p=${p}&s=${s}&b=${b}&idx=${idx }&h=0'">삭제</button>
				<button class="btn btn-outline-success btn-sm" 
				onclick="location.href='index.jsp?p=${p}&s=${s}&b=${b}'">목록</button>
			</td>
		</tr>
	</table>
</body>
</html>