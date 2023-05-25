<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "util.DBUtil.*" %>
<%@ page import = "vo.*" %> 
<%@ page import = "dao.*" %>

<%
	//현재페이지
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	// sql이 담긴 클래스 객체 생성
	SubjectDao listDao = new SubjectDao();

	// 현재 페이지에 표시 할 리스트 생성
	ArrayList<Subject> list = listDao.selectSubjectListByPage(0, 10);

%>	


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SubjectList</title>
</head>
<body>

	<h1>과목리스트</h1>
	<table>
		<tr>
			<th>subject_no<th>
			<th>subject_name<th>
			<th>subject_time<th>
			<th>updatedate<th>
			<th>createdate<th>
		</tr>
		<tr>
		<%
						for(Subject s  : list){
		%>
		
		
			<td><%=s.getSubjectNo()%></td>
			<td><%=s.getSubjectName()%></td>
			<td><%=s.getSubjectTime()%></td>
			<td><%=s.getUpdatedate()%></td>
			<td><%=s.getCreatedate()%></td>
		</tr>
	
	
	<%
		}
	%>
	</table>
</body>
</html>