/** @format */

// fetch("../borard")
//   .then((resolve) => resolve.json())
//   .then((result) => {
//     result.forEach(function (a) {
//       let tr = makeTr(item);
//       list.append(tr);
//     });
//   })
//   .catch((reject) => {
//     console.log(reject);
//   });

//회원가입
document.querySelector('form[name="signUpForm"]').addEventListener("submit", addMemberFnc);
function addMemberFnc(e) {
  e.preventDefault();
  console.log("Print");
  let id = document.getElementById("id").value;
  let pw = document.getElementById("pw").value;
  let name = document.getElementById("name").value;
  let addr = document.getElementById("addr").value;
  let tel = document.getElementById("tel").value;
  let birth = document.getElementById("birth").value;
  let mail = document.getElementById("mail").value;

  //값이 있으면 값을 전송시키는 곳
  fetch("../member", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" }, //파라미터로 key와 value를 넘겨준다
    body: "id=" + id + "&pw=" + pw + "&name=" + name + "&addr=" + addr + "&tel=" + tel + "&birth=" + birth + "&mail=" + mail,
  })
    .then((resolve) => resolve.json())
    .then((result) => {
      if (result.retCode == "Success") {
        alert("가입이 완료되었습니다");
      } else if (result.retCode == "Fail") {
        alert("에러발생!");
      }
    });
}
