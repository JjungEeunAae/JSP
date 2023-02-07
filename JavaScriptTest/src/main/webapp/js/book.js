/** @format */

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
  chk.addEventListener("change", checkAllFnc);
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
  btn.setAttribute("id", "belBtn");
  btn.innerText = "삭제";
  btn.addEventListener("click", deleteBtn);
  td.append(btn);
  tr.append(td);

  list.append(tr);
});

//저장기능
document
  .querySelector("div[id='btns']")
  .children[0].addEventListener("click", addFnc);
function addFnc() {
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

  let tr = document.createElement("tr");
  let td = document.createElement("td");
  //체크박스
  let chk = document.createElement("input");
  chk.setAttribute("type", "checkbox");
  chk.addEventListener("change", checkAllFnc);
  td.append(chk);
  tr.append(td);

  //목록
  td = document.createElement("td");
  td.append(bookCode);
  tr.append(td);

  td = document.createElement("td");
  td.append(bookName);
  tr.append(td);

  td = document.createElement("td");
  td.append(author);
  tr.append(td);

  td = document.createElement("td");
  td.append(press);
  tr.append(td);

  td = document.createElement("td");
  td.append(price);
  tr.append(td);

  //삭제버튼
  let belBtn = document.createElement("button");
  belBtn.setAttribute("id", "belBtn");
  belBtn.innerText = "삭제";
  belBtn.addEventListener("click", deleteBtn);
  td = document.createElement("td");
  td.append(belBtn);
  tr.append(td);

  list.append(tr);

  //input value 초기화
  inputClear();
}

//input value 초기화 함수
function inputClear() {
  document.getElementById("bookCode").value = "";
  document.getElementById("bookName").value = "";
  document.getElementById("author").value = "";
  document.getElementById("press").value = "";
  document.getElementById("price").value = "";
}

//목록 tr 삭제기능
function deleteBtn(e) {
  let del = e.target.parentElement.parentElement;
  del.remove();
}

//전체선택 체크박스
document
  .querySelector("thead input[type='checkbox']")
  .addEventListener("change", allCheckBox);
function allCheckBox() {
  //console.log(this.checked); //t or f
  document.querySelectorAll("#list input[type='checkbox']").forEach((chk) => {
    chk.checked = this.checked;
  });
}

//개별 체크박스 이벤트
function checkAllFnc() {
  let allTr = document.querySelectorAll("#list input[type='checkbox']");
  let chkTr = document.querySelectorAll("#list input[type='checkbox']:checked");

  if (allTr.length == chkTr.length) {
    document.querySelector('thead input[type="checkbox"]').checked = true;
  } else {
    document.querySelector('thead input[type="checkbox"]').checked = false;
  }
}

//클릭(선택삭제) 이벤트 기능
document.querySelector("#checkDel").addEventListener("click", delChkFnc);
function delChkFnc() {
  document
    .querySelectorAll("tbody input[type='checkbox']:checked")
    .forEach((chk) => {
      chk.addEventListener("click", selectDelFnc(chk));
    });
}
function selectDelFnc(chk) {
  chk.parentElement.parentElement.remove();
}
