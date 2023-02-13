<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- css -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<!-- 제이쿼리 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- bxslider 라이브러리 -->
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<style>
img{
	width: 100%;
	height: 200px;
}
</style>
<div class="slider">
  <div><img src="images/배경화면.jpg"></div>
  <div><img src="images/배경화면2.jpg"></div>
  <div><img src="images/배경화면3.jpg"></div>
</div>

<script>
$('.slider').bxSlider({
	auto: true,
	speed: 500,
	pause: 4000,
	mode:'fade',
	autoControls: true,
	pager:true,
});
</script>