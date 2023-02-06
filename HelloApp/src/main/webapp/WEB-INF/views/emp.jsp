<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
//Key : job_id, value : job_title
Map<String, String> list = (Map<String, String>)request.getAttribute("jobList");
%>
<form action="employee.do" method="post" name="myFrm">
	<table class="table">
		<tr>
			<th><label>사원번호 : </label></th>
			<td><input type="number" name="eid" /></td>
		</tr>
		<tr>
			<th><label>LastName : </label></th>
			<td><input type="text" name="last_name" /></td>
		</tr>
		<tr>
			<th><label>이메일 : </label></th>
   			<td><input type="email" name="email" /></td>
		</tr>
		<tr>
			<th><label>입사일자 : </label></th>
    		<td><input type="date" name="hire_date" /></td>
		</tr>
		<tr>
			<th><label>직무 : </label></th>
			<td>
				<select name="job">
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
			<td colspan="2" align="center">
				<input type="submit" value="전송" class="btn btn-success"/>
				<input type="button" value="조회" onclick="empGetFnc()" class="btn btn-primary"/>
				<!-- get방식 --> <!-- ../result/empList.jsp -->
				<input type="button" value="다음" onclick="daumGetFnc()" class="btn btn-primary"/> 
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a href="../employee.do">../employee.do</a>
			</td>
		</tr>
	</table>
</form>

    <script>
					console.log(document.forms.myFrm);

					//---------------------조회버튼
					function empGetFnc() {
						//method를 안바꾸면 등록(post)이 되어버리기 때문에 get으로 바꿔서 목록을 보여주게 한다
						document.forms.myFrm.method = "get"; //form의 method를 get으로 변경
						document.forms.myFrm.action = "empList.do";
						document.forms.myFrm.submit(); //get을 전송
					}

					//--------------------다음버튼
					function daumGetFnc() {
						document.forms.myFrm.method = "delete"; //form의 method를 get으로 변경
						document.forms.myFrm.submit(); //get을 전송
					}
				</script>
<jsp:include page="../includes/footer.jsp"></jsp:include>