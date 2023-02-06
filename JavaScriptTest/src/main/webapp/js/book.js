/**
 *
 */

// bookList배열을 활용해서 처리하도록 하세요.
let bookList = [
  {
    bookCode: "P0206001",
    bookTitle: "이것이자바다",
    bookAuthor: "홍성문",
    bookPress: "신흥출판사",
    bookPrice: "20000",
  },
  {
    bookCode: "P0206002",
    bookTitle: "이것이자바스크립트다",
    bookAuthor: "홍영웅",
    bookPress: "우리출판사",
    bookPrice: "25000",
  },
];

bookList.forEach((book) => {
  let tr = document.createElement("tr");
  let td = document.createElement("td");

  //체크박스
  let chk = document.createElement("input");
  chk.setAttribute("type", "checkbox");
  chk.setAttribute("class", "checkList");
  td.append(chk);
  tr.append(td);

  //목록
  for (let prop in book) {
    let td = document.createElement("td");
    td.innerText = book[prop];
    tr.append(td);
  }

  //삭제버튼
  td = document.createElement("td");
  let btn = document.createElement("button");
  btn.innerText = "삭제";
  //btn.addEventListener("click", deleteFowFunc);
  td.append(btn);
  tr.append(td);

  list.append(tr);
});

//저장기능
document
  .querySelector("div[id='btns']")
  .children[0].addEventListener("click", addFnc);

function addFnc(e) {
  let bookCode = document.getElementById("bookCode").value;
  let bookName = document.getElementById("bookName").value;
  let author = document.getElementById("author").value;
  let press = document.getElementById("press").value;
  let price = document.getElementById("price").value;

  //빈칸없이 모두 입력하기
  if (!bookCode || !bookName || !author || !press || !price) {
    alert("모두 입력해주세요");
    return;
  }

  list = document.querySelectorAll("#list td").length;

  list.forEach((a) => {});
}
