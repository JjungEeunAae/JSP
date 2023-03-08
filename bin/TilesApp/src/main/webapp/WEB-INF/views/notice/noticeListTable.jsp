<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- css -->
<link href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css" rel="stylesheet">
<!-- 제이쿼리 라이브러리 -->
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<!-- dataTable 라이브러리 -->
<script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
<div>
	작성자: <input type="text" id="writer" value="user1" readonly>
	제목: <input type="text" id="title">
	내용: <input type="text" id="subject">
	<button id="addRow">저장</button>
	<button id="delBtn">삭제</button>
</div>
<br/>
<!-- 목록 -->
<table id="example" class="display" style="width:100%">
	 <thead>
	     <tr>
	         <th>글번호</th>
	         <th>작성자</th>
	         <th>제목</th>
	         <th>조회수</th>
	         <th>작성일자</th>
	     </tr>
	 </thead>
	 <tbody>
	 	<c:forEach var="notice" items="${noticeList}">
		 	<tr>
		 		<td>${notice.noticeId}</td>
		 		<td>${notice.noticeWriter}</td>
		 		<td>${notice.noticeTitle}</td>
		 		<td>${notice.hitCount}</td>
		 		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${notice.noticeDate}"/></td>
		 	</tr>
	 	</c:forEach>
	 </tbody>
	 <tfoot>
	     <tr>
	         <th>글번호</th>
	         <th>작성자</th>
	         <th>제목</th>
	         <th>조회수</th>
	         <th>작성일자</th>
	     </tr>
	 </tfoot>
</table>
<script>
var t = $('#example').DataTable();

$('#addRow').on('click', function () {
	var formData = new FormData();
	formData.append('writer', $('#writer').val());
	formData.append('title', $('#title').val());
	formData.append('subject', $('#subject').val());
	
	//DB insert 후 화면처리
	$.ajax({
		url: 'noticeAddJson.do',
		method: 'post',
		data: formData,
		contentType: false,
		processData: false,
		success: function(result){
			console.log(result);
			alert("등록완료");
			//배열에 담는 방식
		    t.row.add(
		    			[result.noticeId, 
		    				result.noticeWriter, 
		    				result.noticeTitle, 
		    				result.noticeSubject, 
		    				result.noticeDate])
		    .draw(false); //화면에 그려주는 방식
		},
		error: function(reject){
			console.log(reject);
		}
	});
});

//tr을 선택하면 스타일을 변경시켜주는 이벤트
$('#example tbody').on('click', 'tr', function () {
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
        t.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
    console.log($(this).children().eq(0).text());
    //localStorage.setItem : localStorage의 객체에 해당 이름으로 뒤에 있는 부분을 담는다(저장)
    localStorage.setItem('noticeId',$(this).children().eq(0).text());
});

//삭제버튼
$('#delBtn').on('click', function(){
	let id = localStorage.getItem('noticeId');
								//localStorage에 담은 값을 읽어온다
	console.log('삭제할 글번호 : ' + localStorage.getItem('noticeId'));
	//console.log('삭제할 글번호2 : ' + t.row('.selected').children().eq(0).text());
	$.ajax({
		url: 'noticeRemove.do',
		data: {nid : id},
		success: function(result){
			console.log(result);
			if(result.retCode == 'Success'){
				alert("삭제완료");
				t.row('.selected').remove().draw(false);
			} else{
				alert("삭제오류!");
			}
		},
		error: function(error){
			console.log(error);
		}
	})
})

// Automatically add a first row of data
//$('#addRow').click();

</script>