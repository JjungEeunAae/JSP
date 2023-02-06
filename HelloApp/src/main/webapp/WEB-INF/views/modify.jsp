
<%@page import="java.util.Map.Entry"%>
<%@page import="co.yedam.emp.service.EmpServiceImpl"%>
<%@page import="co.yedam.emp.service.EmpService"%>
<%@page import="co.yedam.emp.vo.EmpVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
EmpVO emp = (EmpVO) request.getAttribute("updateVO");
Map<String, String> list = (Map<String, String>)request.getAttribute("jobList");
%>
<h4>!!현재 페이지는 empModForm.do의 결과 modify.jsp입니다!!</h4>
<form action="./empModify.do" method="post" name="addFrm">
	<table class="table">
		<tr>
			<td>사원번호</td>
			<td><input type="text" value="<%=emp.getEmployeeId()%>"
				name="upEid" readonly></td>
				<!--name => url 파라미터 이름-->
		</tr>
		<tr>
			<td>FirstName</td>
			<td><input type="text" value="<%=emp.getFirstName()%>"
				name="fname"></td>
		</tr>
		<tr>
			<td>LastName</td>
			<td><input type="text" value="<%=emp.getLastName()%>"
				name="lname"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" value="<%=emp.getEmail()%>" name="mail"></td>
		</tr>
		<tr>
			<td>직무</td>
			<td><input type="text" name="jobInput" id="jobInput" value="<%=emp.getJobId()%>">
				<select name="job" onchange="func(this.value)">
					<%
						//Entry => 키와 벨류를 구분해주는 메소드
					for (Entry<String, String> ent : list.entrySet()) {
					%>
						<option value="<%=ent.getKey() %>"><%=ent.getValue() %></option>
					<%
					}
					%>
				</select>
			</td>
		</tr>
		<tr>
			<td>입사일자</td>
			<td><input type="DATE" value="<%=emp.getHireDate()%>"
				name="hire"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="변경"
				class="btn btn-primary" /></td>
		</tr>
	</table>
</form>
<script>
	function func(job) {
		document.addFrm.jobInput.value = job;
	}
</script>
<jsp:include page="../includes/footer.jsp"></jsp:include>