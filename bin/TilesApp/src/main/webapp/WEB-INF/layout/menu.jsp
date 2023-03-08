<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
@font-face {
    font-family: 'KIMM_Bold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2212@1.0/KIMM_Bold.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
}

.divFont{
	font-family: 'KIMM_Bold';
}
</style>
<div class="d-flex" id="wrapper">
	<!-- Sidebar-->
	<div class="border-end bg-white divFont" id="sidebar-wrapper">
		<div class="sidebar-heading border-bottom bg-light">
			연습페이지
		</div>
		<div class="list-group list-group-flush divFont">
			<a class="list-group-item list-group-item-action list-group-item-light p-3"
				href="noticeList.do">게시판목록</a>
			<c:if test="${!empty id}">
				<a class="list-group-item list-group-item-action list-group-item-light p-3"
				href="noticeForm.do">게시글등록</a>
			</c:if>
			<a class="list-group-item list-group-item-action list-group-item-light p-3"
				href="main.do">Main</a>
			<a class="list-group-item list-group-item-action list-group-item-light p-3"
				href="#!">Events</a>
			<a class="list-group-item list-group-item-action list-group-item-light p-3"
				href="noticeListAjax.do">noticeListAjax.do</a>
			<a class="list-group-item list-group-item-action list-group-item-light p-3"
				href="noticeListWithTables.do">noticeListWithTables.do</a>
		</div>
	</div>