<%@page import="board.service.BoardServiceImpl"%>
<%@page import="board.service.BoardService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 공통코드 삽입 --%>
<%@ include file="include.jsp" %>
<%
	// POST전송이 아니면 목록으로 보내버린다.
	if(!request.getMethod().equals("POST")){
		response.sendRedirect("index.jsp");
		return;
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>
<style type="text/css">

</style>
</head>
<body>
	<%-- 넘어온 데이터 받기 --%>
	<jsp:useBean id="vo" class="board.vo.BoardVO"></jsp:useBean>
	<jsp:setProperty property="*" name="vo"/>
	<jsp:setProperty property="ip" name="vo" value="${pageContext.request.remoteAddr }"/>
	
	<%-- 모드 값에따라 저장/수정/삭제를 서비스 클래스를 불러서 처리하면 된다.  --%>
	<%
		String query = "";
		int count = 0;
		switch(mode){
		case 1: // 저장
			count = BoardServiceImpl.getInstance().insert(vo);
			if(count==0){
				out.println("<script>alert('저장 실패!!!')</script>");
				query = "index.jsp?p="+currentPage+"&s=" + pageSize + "&b=" + blockSize; // 원래 페이지
			}else{
				out.println("<script>alert('저장 성공!!!')</script>");
				query = "index.jsp?p=1&s=" + pageSize + "&b=" + blockSize; // 1페이지
			}
			break;
		case 2: // 수정
			count = BoardServiceImpl.getInstance().update(vo);
			if(count==0){
				out.println("<script>alert('수정 실패!!!')</script>"); // 수정화면으로
				query = "update.jsp?p=" + currentPage + "&s=" + pageSize + "&b=" + blockSize + "&idx=" + idx +"&h=0";
			}else{
				out.println("<script>alert('수정 성공!!!')</script>"); // 내용보기 화면으로
				query = "view.jsp?p=" + currentPage + "&s=" + pageSize + "&b=" + blockSize + "&idx=" + idx +"&h=0";
			}
			break;
		case 3: // 삭제
			count = BoardServiceImpl.getInstance().delete(vo);
			if(count==0){
				out.println("<script>alert('삭제 실패!!!')</script>"); // 삭제 실패하면 삭제화면으로
				query = "delete.jsp?p=" + currentPage + "&s=" + pageSize + "&b=" + blockSize + "&idx=" + idx +"&h=0";
			}else{
				out.println("<script>alert('삭제 성공!!!')</script>"); // 삭제 성공하면 목록으로
				query = "index.jsp?p=" + currentPage + "&s=" + pageSize + "&b=" + blockSize;
			}
			break;
		}
		
		// 위에서 자바스크립트를 사용했기때문에 response.sendRedirect사용 못함
		// 페이지 이동을 자바스크립트로 처리해야 한다.
		out.println("<script>location.href='" + query + "'</script>"); // 페이지 이동
	%>
</body>
</html>