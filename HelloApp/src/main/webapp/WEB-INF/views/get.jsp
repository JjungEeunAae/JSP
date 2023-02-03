<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>views/get.jsp</title>
  </head>
  <body>
    <!-- http://localhost:8081/HelloApp/views/get.jsp -->
    <h3>form 요청 get/post</h3>
    <form action="../login.do" method="post">
      <label>ID <input type="text" name="uid" value="user1" /></label>
      <label>PW <input type="password" name="upw" value="1234" /></label>
      <br />
      <input type="submit" value="전송" />
    </form>
    <br />
    <!-- 링크타는건 모두 get방식, post방식으로는 사용할 수 없다 -->
    <a href="../login.do?uid=user1&upw=1234">login페이지</a>

    <script>
      //fetch는 기본으로 get방식이다
      //post방식이 필요하면 method를 지정해줘야한다
      fetch("../login.do?uid=user1&upw=1234", {
        method: "post",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: "uid=user1&upw=1234",
      })
        .then((resolve) => resolve.text())
        .catch((reject) => console.log(reject));
    </script>
  </body>
</html>
