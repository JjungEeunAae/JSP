<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.addMember{
	width: 50%;
	margin:auto;
	taxt-align:center;
}
.Memberinput{
	width: 400px;
	border-radius: 10px;
	padding: 10px;
}
</style>
<!-- 제이쿼리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script type="text/javascript" src="js/manage.js"></script>
<h3 align="center">회원관리 페이지</h3>
<h5 align="center">memberList.do, addMember.do, removeMember.do, updateMember.do</h5>
<table class="table addMember">
	<tr>
		<th style="width: 200px">❁ 아이디</th>
		<td>
			<input class="Memberinput"
					type="text" id="mid" placeholder="아이디를 입력하세요" >
		</td>
	</tr>
	<tr>
		<th>❁ 이름</th>
		<td>
			<input class="Memberinput"
					type="text" id="mname" placeholder="이름을 입력하세요" >
		</td>
	</tr>
	<tr>
		<th>❁ 연락처</th>
		<td>
			<input class="Memberinput"
					type="tel" id="mphone" placeholder="연락처를 입력하세요" >
			</td>
	</tr>
	<tr>
		<th>❁ 주소</th>
		<td>
			<input class="Memberinput"
					type="text" id="maddr" placeholder="주소를 입력하세요" >
		</td>
	</tr>
	<tr>
		<th>❁ 이미지</th>
		<td><input type="file" id="mimage" multiple></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input  type="button" id="addBtn" value="등록" class="btn btn-secondary">
		</td>
	</tr>
</table>
<table class="table">
	<thead class="table-light">
		<tr>
			<th>회원아이디</th>
			<th>회원 이름</th>
			<th>연 락 처</th>
			<th>주   소</th>
			<th>권한</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tbody id="list"></tbody>
</table>

<table id="template" style="display: none;">
	<tr>
		<td>admin</td>
		<td><input type="text" value="sample" class="name"></td>
		<td><input type="text" value="sample" class="name"></td>
		<td><input type="text" value="sample" class="name"></td>
		<td><input type="text" value="sample" class="name"></td>
		<td>
			<button mid="admin">삭제</button>
		</td>
	</tr>
	<!-- <tr>
		<td><input type="text" class="name"></td>
		<td><input type="text" class="addr"></td>
	</tr> -->
</table>