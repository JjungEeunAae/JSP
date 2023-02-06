<%@page import="co.yedam.emp.vo.EmpVO"%>
<%@page import="java.util.List"%>
<%@page import="co.yedam.emp.service.EmpServiceImpl"%>
<%@page import="co.yedam.emp.service.EmpService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
	List<EmpVO> list = (List<EmpVO>)request.getAttribute("empList");
%>
<h3>사원목록(EmpControl.do의 결과페이지)</h3>
<table class="table">	
	<thead>
		<tr>
			<th>사원번호</th>
			<th>FirstName</th>
			<th>LastName</th>
			<th>이메일</th>
			<th>직무</th>
			<th>입사일자</th>
		</tr>
	</thead>
	<tbody>
		<%
		for (EmpVO emp : list) {
		%>
		<tr>
			<td><a href="empDetail.do?eid=<%=emp.getEmployeeId()%>"><%=emp.getEmployeeId()%></a></td>
			<td><a><%=emp.getFirstName()%></a></td>
			<td><a><%=emp.getLastName()%></a></td>
			<td><a><%=emp.getEmail()%></a></td>
			<td><a><%=emp.getJobId()%></a></td>
			<td><a><%=emp.getHireDate()%></a></td>
		</tr>
		<%
		}
		%>
	</tbody>
</table>
<jsp:include page="../includes/footer.jsp"></jsp:include>
