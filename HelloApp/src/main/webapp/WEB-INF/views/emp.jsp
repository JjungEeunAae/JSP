<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
    <form action="employee.do" method="post" name="myFrm">
      <label>사원번호 : </label>
      <input type="number" name="eid" />
      <br />
      <label>LastName : </label>
      <input type="text" name="last_name" />
      <br />
      <label>이메일 : </label>
      <input type="email" name="email" />
      <br />
      <label>입사일자 : </label>
      <input type="date" name="hire_date" />
      <br />
      <label>직무 : </label>
      <select name="job">
        <option value="IT_PROG">개발자</option>
        <option value="SA_REP" selected>영업사원</option>
        <option value="SA_MAN">영업팀장</option>
      </select>
      <br />
      <input type="submit" value="전송" />
      <input type="button" value="조회" onclick="empGetFnc()" />
      <input type="button" value="다음" onclick="daumGetFnc()" />

      <!-- get방식 -->
      <!-- ../result/empList.jsp -->
      <a href="../employee.do">조회</a>
    </form>

    <script>
      console.log(document.forms.myFrm);

      //---------------------조회버튼
      function empGetFnc() {
        //method를 안바꾸면 등록(post)이 되어버리기 때문에 get으로 바꿔서 목록을 보여주게 한다
        document.forms.myFrm.method = "get"; //form의 method를 get으로 변경
        document.forms.myFrm.action = "empList.do";
        document.forms.myFrm.submit(); //get을 전송
      }

      //--------------------다음버튼
      function daumGetFnc() {
        document.forms.myFrm.method = "delete"; //form의 method를 get으로 변경
        document.forms.myFrm.submit(); //get을 전송
      }
    </script>
<jsp:include page="../includes/footer.jsp"></jsp:include>