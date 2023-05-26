<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %> 
<%@ page import = "dao.*" %>

<%
	//현재페이지
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
	
	//페이지 메서드 가져오기
		teacherDao totalRowDao = new teacherDao();
		int totalRow = totalRowDao.selectTeacherCnt();
		
	//한 페이지에 담길 행의 개수 = 4
		int rowPerPage = 4;
		int pagePerPage = 4;
		
	//시작행
		int beginRow = (currentPage-1)*rowPerPage +1;
	//마지막 행
		int endRow = beginRow + (rowPerPage-1); //endrow는 무엇을 기준으로?
		
		if(endRow > totalRow){
			
			endRow = totalRow;
		}
	
	

	//마지막 페이지
		int lastPage = totalRow / rowPerPage;
		if(totalRow % rowPerPage != 0){
			lastPage = lastPage +1; //10으로 나누어 떨어지지 않는 나머지 게시글을 위한 1 페이지 생성
		}

	//페이지 목록 최초 게시물의 페이징
		int minPage = ((currentPage-1)/pagePerPage) * pagePerPage + 1;
	
	//페이지 목록 마지막 게시글의 페이징
		int maxPage = minPage +(pagePerPage -1);
		if(maxPage > lastPage){
			maxPage = lastPage;
		}
	
	
	// sql이 담긴 클래스 객체 생성
		teacherDao teacherDao = new teacherDao();

	// 현재 페이지에 표시 할 리스트 생성
		ArrayList<HashMap<String, Object>> list = teacherDao.selectTeacherListByPage(beginRow, rowPerPage);

%>





<!DOCTYPE html>
<html>
<head>

<!-- 부트스트랩 코드 복사 붙여넣기 // 안 하면 페이지 구현 안 됨 -->
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	
<meta charset="UTF-8">
<title>TeacherList</title>
</head>
<body>
	<h1>교사 리스트</h1>
	<table class=" table table-hover" >
		<tr>
			<th>teacher_no</th>
			<th>teacher_id</th>
			<th>teacher_name</th>
			<th>subject_no</th>
			<th>subject</th>
		</tr>
	
		<tr>
		<%
			for(HashMap<String, Object> m : list){
		%>
		
			<td><%=m.get("teacher_no")%></td>
			<td><%=m.get("teacher_id")%></td>
			<td><%=m.get("teacher_name")%></td>
			<td><%=m.get("subject_no")%></td>
			<td><%=m.get("subject")%></td>
		</tr>
		
		<%
			}
		%>
		
		
		
		
	
	
	</table>

	<!-- 페이징 -->
	<div class="container text-center">
		<% 
		  if(minPage > 1) {
		%>
		   <a href="<%=request.getContextPath()%>/Teacher/TeacherList.jsp?currentPage=<%=minPage-pagePerPage%>">이전</a>   
		<%
		}
		
		for(int i = minPage; i<=maxPage; i=i+1) {
		   if(i == currentPage) {
		%>
		      <span><%=i%></span>&nbsp;
		<%         
		   } else {      
		%>
		      <a href="<%=request.getContextPath()%>/Teacher/TeacherList.jsp?currentPage=<%=i%>"><%=i%></a>&nbsp;   
		<%   
		   }
		}
		
		if(maxPage != lastPage) {
		%>
		   <!--  maxPage + 1 -->
		   <a href="<%=request.getContextPath()%>/Teacher/TeacherList.jsp?currentPage=<%=minPage+pagePerPage%>">다음</a>
		<%
	 	      }
	
		%>
		</div>				


</body>
</html>