<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.myPageTable{
	width: 50%;
	margin:auto;
	taxt-align:center;
}
.myPageUpdateInput{
	width: 400px;
	border-radius: 10px;
	padding: 10px;
}
</style>
<!-- JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 제이쿼리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<h3 align="center">My Page</h3>
<h5 align="center">modifyMember.do, imageUpload.do</h5>
<form  id="form" action="modifyMember.do" method="post" >
	<input
		type="file" id="fileUpload"
		accept="image/*" style="display:none"
		onchange="imageChangeFnc()"> <!--  -->
	<table class="table myPageTable" >
		<tr>
			<th>아이디</th>
			<td>
				<input class="myPageUpdateInput" type="text"
						name="mid" value="${vo.memberId}" readonly>
			</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>
				<input class="myPageUpdateInput" type="text"
						name="mname" value="${vo.memberName}">
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input class="myPageUpdateInput" type="text"
						name="mpass" value="${vo.memberPw}">
			</td>
		</tr>
		<tr>
			<th>연락처</th>
			<td>
				<input class="myPageUpdateInput" type="tel"
						name="mphone" value="${vo.memberPhone}">
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<input class="myPageUpdateInput" type="text"
				name="maddr" value="${vo.memberAddr}">
			</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td>
				<img id="imgSrc" width="150px" src="upload/${vo.image}">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input id="modifyBtn" type="button" onclick="updateMemberFnc(event)" value="수정">
			</td>
		</tr> <!-- submit -->
	</table>
</form>
<script>
	//수정버튼
	function updateMemberFnc(e){
		//수정여부 안내매세지
		if(!window.confirm("수정하시겠습니까?")){
			//아니요 누르면 멈춘다
			return;
		}
		
		let id = $('.table').find("input[name='mid']").val();
		let name = $('.table').find("input[name='mname']").val();
		let pw = $('.table').find("input[name='mpass']").val();
		let phone = $('.table').find("input[name='mphone']").val();
		let addr = $('.table').find("input[name='maddr']").val();
		
		$.ajax({
			url:'modifyMember.do',
			method: 'post',
			data: {id: id,
					name: name,
					pw: pw,
					phone: phone,
					addr: addr
				   },
			success: function(result){
				console.log(result);
				if(result.retCode == 'Success'){
					//tr에 덮어씌운다(목록출력(성공값));
					alert("수정이 완료되었습니다");
				} else{
					alert("수정이 되지 않았습니다");
				}
			},
			error: function(err){
				console.log(err);
			}
		})
	} // end of updateMemberFnc


	//js event 등록 == addEventListener('type',function(){})
	//jQuery event 등록 == elem.on('click',function(){})
	$('#imgSrc').on('click',function(){
		//파일선택
		$('#fileUpload').click();
	})
	
	//선택한 이미지
	function imageChangeFnc(){
		//서버 업로드할 때 필요한 정보
		console.log($('#fileUpload')[0].files[0]);
		let file = $('#fileUpload')[0].files[0];
		
		//multipart 요청할 땐 formData라는 객체를 활용하는게 유용함
		//=multipart를 처리해준다
		let formData = new FormData();
		//id, file 업로드해서 서버에 저장하고, 데이터베이스에도 데이터를 변경할 것
		formData.append('id', "${vo.memberId}"); //imageUpload Control에 선언되어있는 parameter 값
		formData.append('image', file);			 //imageUpload Control에 선언되어있는 parameter 값
		
		//서버에 multipart/form-data : ajax 요청
		$.ajax({
			url: 'imageUpload.do',
			method: 'post',
			data: formData, 	// 보낼 값
			contentType: false, // multipart 요청일 경우 false로 지정
			processData: false,	// multipart 요청일 경우 false로 지정
			success: function(result){
				console.log(result);
				// 화면에서도 선택된 이미지가 보여짐
				let reader = new FileReader();
				reader.onload = function(ev){ //파일을 읽겠습니다라는 이벤트
					console.log(ev.target); // 이미지 값
					$('#imgSrc').attr('src', ev.target.result); //이미지 값을 가져온다
				}
				reader.readAsDataURL(file); 
			},
			error: function(err){
				console.log(err);
				
			}
		});
		
	} // end of imageChangeFnc
	
	
</script>