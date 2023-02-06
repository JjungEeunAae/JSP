<%@page import="co.yedam.emp.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<!--request -> 뷰에서 DB로 자료를 요청-->
<!--id를 가진 searchVO 속성을 가져와서 사용-->
<!--  -->
<%
  EmpVO emp = (EmpVO) request.getAttribute("searchVO"); 
  Integer age = (Integer) request.getAttribute("myAge");
  String id = (String) request.getAttribute("loginId");
%>

 Attribute test : <%=age %>,<%=id %>
<h4>!!현재 페이지는 empDetail.do의 결과 empDetail.jsp 입니다!!</h4>
<table class="table">
  <tr>
    <th>사원번호</th>
    <td><%=emp.getEmployeeId() %></td>
  </tr>
  <tr>
    <th>FistName</th>
    <td><%=emp.getFirstName() %></td>
  </tr>
    <tr>
    <th>LastName</th>
    <td><%=emp.getLastName() %></td>
  </tr>
  <tr>
    <th>이메일</th>
    <td><%=emp.getEmail() %></td>
  </tr>
    <tr>
    <th>직무</th>
    <td><%=emp.getJobId() %></td>
  </tr>
    <tr>
    <th>입사일자</th>
    <td><%=emp.getHireDate() %></td>
  </tr>
  <tr>
  	<td colspan="2" align="center">
  		<button class="btn btn-primary"
  			onclick="location.href='empModForm.do?id=<%=emp.getEmployeeId() %>'">수정</button>
  		<button class="btn btn-warning" 
  			onclick="location.href='empRemove.do?id=<%=emp.getEmployeeId() %>'" >삭제</button>
  		<!-- empRemove.do?id=?, removeEmp(int id) -->
 	 </td>
  </tr>
  
</table>
<jsp:include page="../includes/footer.jsp"></jsp:include>
