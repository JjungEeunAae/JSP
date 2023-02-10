<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Page content wrapper-->
<div id="page-content-wrapper">
	<!-- Top navigation-->
	<nav
		class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
		<div class="container-fluid">
			<button class="btn btn-primary" id="sidebarToggle">Toggle
				Menu</button>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mt-2 mt-lg-0">
					<li class="nav-item active"><a class="nav-link" href="main.do">Home</a></li>
					<c:choose>
						<c:when test="${!empty id}">
							<li class="nav-item"><a class="nav-link" href="logout.do">Logout(${name})</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link" href="loginForm.do">Login</a></li>
						</c:otherwise>
					</c:choose>
					
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Dropdown</a>
						<div class="dropdown-menu dropdown-menu-end"
							aria-labelledby="navbarDropdown">
							<c:if test="${!empty Auth}">
								<a class="dropdown-item" href="myPageForm.do">My Page</a>
							</c:if>
							<c:if test="${Auth == 'admin'}">
							<a class="dropdown-item" href="memberManageForm.do">Member Manage</a>
							</c:if>
							<div class="dropdown-divider">
						</div>
							<a class="dropdown-item" href="#!">Something else here</a>
						</div></li>
				</ul>
			</div>
		</div>
	</nav>