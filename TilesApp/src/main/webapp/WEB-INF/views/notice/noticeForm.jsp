<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3 style="text-align: center">글 등 록</h3>
<form action="noticeAdd.do" enctype="multipart/form-data" method="post">
	<table class="table"
			style="width: 70%; margin:auto; taxt-align:center;">
		<tr>
			<th style="width: 200px">제목</th>
			<td>
				<input type="text" name="title" style="width: 500px">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name="subjcet" cols="70" rows="15"></textarea>
			</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<input type="text" name="writer"
						value="${id}" readonly
						style="width: 400px; border:none;">
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<input type="file" name="attach">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input class="btn btn-primary" type="submit" value="저장">
				<input class="btn btn-warning" type="reset" value="취소">
			</td>
		</tr>
	</table>
</form>