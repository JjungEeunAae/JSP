<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<table class="table">
	<tr>
		<th>글번호</th>
		<td>${vo.noticeId}</td>
		<th>조회수</th>
		<td>${vo.hitCount}</td>
	</tr>
	<tr>
		<th colspan="2">작성자</th>
		<td colspan="2">${vo.noticeWriter}</td>
	</tr>
	<tr>
		<th colspan="2">제목</th>
		<td colspan="2">${vo.noticeTitle}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="3"><textarea cols="60" rows="4">${vo.noticeSubject}</textarea></td>
	</tr>
	<tr>
		<th>작성일자</th>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${vo.noticeDate}"/></td>
		<th>첨부파일</th>
		<td><a target="_blank" href="upload/${vo.attachFile}">${vo.attachFile}</a></td>
	</tr>
</table>
<br />
<!-- 댓글등록 -->
<div>
	<span><b>제목:</b></span><span><input type="text" id="title"></span>
	<span><b>내용:</b></span><span><input type="text" id="subject"></span>
	<span><b>작성자:</b></span><span><input type="text" id="writer" value="${id}"></span>
	<button class="btn btn-primary" id="addReply">댓글등록</button>
</div>
<!-- 댓글목록 및 삭제  -->
<table class="table">
 <thead>
 	<tr>
 		<th colspan="4" scope="col">댓글정보</th>
 	</tr>
 </thead>
 <!-- 댓글 정보 보이는 장소 -->
 <tbody id="list"></tbody>
</table>
<script>
	let nid = ${vo.noticeId}; //게시글 번호
	let logid = '${id}';	   //login.java(Control) 속성의 Key 값
	fetch('replyList.do?nid='+nid)
		.then(resolve => resolve.json())
		.then(result => {
			console.log(result);
			result.forEach(reply => {makeTr(reply)})
		})
		.catch(error => {
			console.log(error)
		})
	
	function deleteReply(replyId){
		//ajax 호출
		$.ajax({ //=>함수
			url: 'removeReply.do',
			method: 'post',
			data: {rid: replyId},
			success: function(result){
				if(result.retCode == 'Success'){
					console.log(result);
					$("tr[data-id="+replyId+"]").remove();
				} else {
					alert('처리안됨');
				}
			},
			error: function(reject){
				console.log(reject);
			}
		});
		//<tr data-id = 3> => tr[data-id = 3]
		//$("tr[data-id="+replyId+"]").remove();
	}
	
	//jQuery event
	$("#addReply").on('click', function(){ //=>addEventListener
		//console.log(${vo.noticeId});
		console.log("nid: " + nid);
		let writer = $('#writer').val();
		let subject = $('#subject').val();
		let title = $('#title').val();
		
		$.ajax({
			url: 'addReply.do',
			method: 'post',
			data: {title: title,	//파라미터이름 : div id 속성 값
					writer: writer,
					subject: subject,
					nid: nid
				   }, 
			success: function(result){
				console.log(result);
				makeTr(result);		//tr 생성 후 tbody 아래에 append
			},
			error: function(reject){
				console.log(reject);
			}
		});
	})
	
	//댓글목록
	function makeTr(reply){
		//tr: 댓글번호, 제목, 작성자, 작성일자
		//tr: 댓글내용
		
		//=>let tr1 = document.createElement('tr');
		let tr1 = $('<tr />').attr('data-id', reply.replyId) //=>serAttribute(Key, value)
							.append(
								//=>let td = document.createElement('td');
								$("<td />").html("<b>번호:</b> " + reply.replyId),
								//=>td.innerText = reply.replyId;
								$("<td />").html("<b>제목:</b> " + reply.replyTitle),
								$("<td />").html("<b>작성자:</b> " + reply.replyWriter),
								$("<td />").html("<b>작성일자:</b> " + reply.replyDate)
							)
		let tr2 = $('<tr />')
				.attr('data-id', reply.replyId)
				.append( $("<td colspan='4' />")
					.html( function() {
						if(reply.replyWriter == logid){
							//로그인한 사용자랑 글을 작성한 아이디가 같으면 본인의 댓글에 버튼이 보인다
							return reply.replySubject
							+ "<button onclick='deleteReply("+reply.replyId+")' class='btn btn-warning btn-sm' style='float:right;'>삭제</button>"
						} else{
							//그게 아니라면 댓글만 보이게 한다
							return reply.replySubject
						}
					}))
		//console.log(tr1, tr2);
		
		//id가 list인걸 찾아와서 생성한 tr1, tr2를 추가한다
		$('#list').prepend(tr1, tr2);
	}
</script>
