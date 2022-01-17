<%@page import="board.service.BoardServiceImpl"%>
<%@page import="board.service.BoardService"%>
<%@page import="board.vo.BoardVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 공통코드 삽입 --%>
<%@ include file="include.jsp" %>
<%
	// 서비스 클래스에서 삭제할 데이터를 가져와야 한다.
	BoardVO vo = BoardServiceImpl.getInstance().selectByIdx(idx, false);
	if(vo==null){ // 해당 글번호의 글이 없다면
		response.sendRedirect("index.jsp");
		return;
	}
	request.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 내용삭제</title>
<%-- 부트스트랩을 사용하기 위한 준비 시작 --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<%-- 부트스트랩을 사용하기 위한 준비 끝 --%>
<script type="text/javascript">
	function formCheck(){
		var obj = $("#password");
		if(obj.val()==null || obj.val().trim().length==0){
			alert("비밀번호는 반드시 입력해야 합니다.");
			obj.val("");
			obj.focus();
			return false;
		}
	}
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
	<form action="updateOk.jsp" method="post" onsubmit="return formCheck();">
	<%-- 여기에서 몇개를 숨겨서 가지고 가자 --%>
	<input type="hidden" name="p" value="${p }" />
	<input type="hidden" name="s" value="${s }" />
	<input type="hidden" name="b" value="${b }" />
	<input type="hidden" name="m" value="3" />
	<input type="hidden" name="h" value="0" />
	<input type="hidden" name="idx" value="${idx }" />
	<table>
		<tr>
			<td colspan="4" class="title">자유게시판 삭제하기</td>
		</tr>
		<tr>
			<td class="item">이름</td>
			<td>
				<input type="text" name="name" id="name" value="${vo.name }" readonly="readonly"/>
			</td>
			<td class="item">비밀번호</td>
			<td>
				<input type="password" name="password" id="password" required="required" maxlength="30"/>
			</td>
		</tr>
		<tr>
			<td class="item">제목</td>
			<td colspan="3">
				<input type="text" name="subject" id="subject" readonly="readonly" size="90" maxlength="100" value="${vo.subject }"/>
			</td>
		</tr>
		<tr>
			<td class="item">내용</td>
			<td colspan="3">
				<textarea name="content" id="content" cols="93" rows="10" readonly="readonly">${vo.content }</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4" style="border: none;text-align: right;">
				<input type="submit" value="삭제" class="btn btn-outline-success btn-sm" />
				<input type="button" class="btn btn-outline-success btn-sm" 
				onclick="location.href='index.jsp?p=${p}&s=${s}&b=${b}'" value="목록">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>