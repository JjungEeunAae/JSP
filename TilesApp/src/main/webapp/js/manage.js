/**
 * manage.js
 */
console.log("manage.js start.....!");
//$(document).ready ==> html or xml부터 실행하고 함수를 실행한다
//ajax는 비동기 함수, 실행시점을 달리 해주는 이벤트
$(document).ready(function(){
	//##################################################
	/*let clone = $('#template').clone(true);
	//console.log(clone.find('tr')); //tr의 하위요소
	//let tr = clone.find('tr');
	//tr.find('.name').val('test');
	$('#list').append(tr);*/
	
	//##################################################
	//목록을 가져오는 Ajax 호출
	console.log($('#list'));
	$.ajax({
		url: 'memberList.do',
		success: function(result){
			console.log(result);
			//제이쿼리 forEach 
			$(result).each(function(idx, item){ //인덱스, 인덱스에 들어있는 값
				$('#list').append(makeRow(item)); //화면출력
			})
		},
		error: function(reject){
			console.log(reject);
		}
	});
	
	//등록버튼 이벤트
	$('#addBtn').on('click',function(){
		
		let fileRepository = [];
		
		$('input[type="file"]').on('change',function(){
			fileRepository.push(this.files[0]);
		});
		
		let id = $('#mid').val(); // .val() : element.value 속성을 읽어옴
		let name = $('#mname').val();
		let phone = $('#mphone').val();
		let addr = $('#maddr').val();
		let img = $('#mimage')[0].files[0];
		console.log(img);
		
		//
		let formData = new FormData();
		formData.append('id', id);
		formData.append('name', name);
		formData.append('phone', phone);
		formData.append('addr', addr);
		formData.append('img', fileRepository);
		
		$.ajax({
			url: 'addMember.do',
			method: 'post',
			data: formData,
			contentType: false,
			processData: false,
			success: function(result){
				// 처리된 정보를 화면에 생성
				console.log(result);
				if(result.retCode == 'Success'){
					$('#list').append(makeRow(result.member));	
				} else{
					alert("입력 미완");
				}
			},
			error: function(err){
				//alert("실패");
				console.log(err);
			}
		})
	});
});

function deleteMemberFnc(e){
	//안내메세지
	if(!window.confirm("삭제하시겠습니까?")){
		//아니요 누르면 멈춘다
		return;
	}
	
	let user = $(e.target).attr('mid'); // .attr => getAttribute, 가져오다
	
	$.ajax({
		url: 'removeMember.do',	//회원 삭제하는 url 및 컨트롤 등록하기
		data: {id: user},		//removeMember.do?id=user
		success: function(result){
			console.log(result);
			if(result.retCode == 'Success'){
				alert("삭제완료");
				$(e.target).parent().parent().remove();
			} else{
				alert("삭제오류!!");
			}
		},
		error: function(reject, code, err){
			console.log(reject);
			console.log(code);
		}
	});
} // end of deleteFnc

//목록출력 함수
function makeRow(member={}){
	
	let tr = $('<tr />'); //document.createElement('tr')과 같은 기능
	tr.on('dblclick',function(e){
		//console.log("test");
		let id = $(this).children().eq(0).text(); //children() : 메소드, eq(0) : 첫번째값
		let name = $(this).children().eq(1).text();
		let phone = $(this).children().eq(2).text();
		let addr = $(this).children().eq(3).text();
		let resp = $(this).children().eq(4).text();
		
		
		let nTr = $('<tr />').append(
			$('<td />').text(id), //값을 수정하지 못하도록
			$('<td />').append($('<input/>').val(name)),
			$('<td />').append($('<input/>').val(phone)),
			$('<td />').append($('<input/>').val(addr)),
			$('<td />').append($('<input/>').val(resp)),
			$('<td />')											//이벤트 타겟을 매개값으로 넣는다
				.append($('<button class="btn btn-primary" onclick="updateMemberFnc(event);">수정</button>')
				.val(resp))
		)
		//nTr = $('#template tr').clone(true);
		//nTr.find('input.name').val(name);
		//새로운 tr로 기존 tr를 대체
		tr.replaceWith(nTr); //변셩시키다
	})
	tr.append(
		$('<td />').text(member.memberId), //innerText
		$('<td />').text(member.memberName),
		$('<td />').text(member.memberPhone),
		$('<td />').text(member.memberAddr),
		$('<td />').text(member.responsibility), //콜백함수 : 클릭이라는 이벤트가 발생해야 된다 뒤에()를 붙히면 클릭하기도 전에 실행된다
		$('<td />').append( //td 추가
			$('<button class="btn btn-danger">삭제</button>')
				.attr('mid', member.memberId) // .attr => setAttribute, 만들다
				.on('click',deleteMemberFnc) //이벤트
		) 
	);
	return tr;
}

//수정버튼
function updateMemberFnc(e){
	//ModifyMember.do 사용자 정보 수정기능
	//console.log(tr.find('input.name').val()); class 지정한 input 불러올 때
	//console.log(tr.children()); //tr의 하위요소(input)을 읽어옴
	let tr = $(e.target).parent().parent(); //tr
	console.log($(e.target).parent().parent());
	console.log("권한 ", tr.find('td:nth-of-type(5) input').val());//하위요소를 찾고싶다면?
	console.log("id ", tr.children().eq(0).text());
	let id = tr.children().eq(0).text();
	let name = tr.find('td:nth-of-type(2) input').val();
	let phone = tr.find('td:nth-of-type(3) input').val();
	let addr = tr.find('td:nth-of-type(4) input').val();
	let resp = tr.find('td:nth-of-type(5) input').val();
		
	$.ajax({
		url:'updateMember.do',
		method: 'post',
		data: {id: id,
				name: name,
				phone: phone,
				addr: addr,
				resp: resp},
		success: function(result){
			console.log(result);
			if(result.retCode == 'Success'){
				//tr에 덮어씌운다(목록출력(성공값));
				tr.replaceWith(makeRow(result.member));
			} else{
				alert("입력 미완");
			}
		},
		error: function(err){
			console.log(err);
		}
	})
} //end of updateMemberFnc
