<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- css -->
<link href="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css" rel="stylesheet">
<!-- 제이쿼리 라이브러리 -->
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<!-- dataTable 날짜 라이브러리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/luxon/2.3.1/luxon.min.js"></script>
<!-- dataTable 라이브러리 -->
<script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>
<div>
	<button id="delBtn">삭제</button>
</div>
<br/>
<table id="example" class="display" style="width:100%">
    <thead>
        <tr>
            <th>글번호</th>
            <th>유저</th>
            <th>글제목</th>
            <th>조회수</th>
            <th>작성일자</th>
        </tr>
    </thead>
    <tfoot>
        <tr>
            <th>글번호</th>
            <th>유저</th>
            <th>글제목</th>
            <th>조회수</th>
            <th>작성일자</th>
        </tr>
    </tfoot>
</table>
<script>
//ajax 호출
var t = $('#example').DataTable({
    ajax: 'noticeListJson.do',
    columnDefs: [
        {
            targets: 4,
            render: DataTable.render.datetime('yyyy-MM-dd'),
        },
    ],
});

//tbody tr 스타일
$('#example tbody').on('click', 'tr', function () {
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
        t.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
});

//삭제버튼
$('#delBtn').on('click', function(){
								//localStorage에 담은 값을 읽어온다
	console.log('삭제할 글번호 : ' + localStorage.getItem('noticeId'));
	//console.log('삭제할 글번호2 : ' + t.row('.selected').children().eq(0).text());
	t.row('.selected').remove().draw(false);
})
</script>