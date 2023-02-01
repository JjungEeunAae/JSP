/*empList.js*/

//목록출력하기
let totalAry = []; //전체목록을 담아놓을 배열 용도
fetch("../empListJson") //아작스 호출.
  .then((resolve) => resolve.json()) // 가져온 데이터를 제이슨으로 바꿔준다
  .then((result) => {
    //웹브라우저 추가속성, 값을 지정해주는 것, 윈도우 하위속성
    localStorage.setItem("total", result.length);
    totalAry = result;
    //배열관련 메소드: forEach, map, filter, reduce 메소드.
    // result.forEach(function (item, idx, arry) {
    //   let tr = makeTr(item); //tr생성 후 반환.
    //   list.append(tr);
    // });
    showPages(12);
    employeeList(12);
  })
  .catch((reject) => {
    console.log(reject);
  });
//저장버튼 submit 이벤트 등록
document
  .querySelector('form[name="empForm"]')
  .addEventListener("submit", addMemberFnc); // 폼 태그중에서 이름이 empForm인것을 가져오겠습니다.

// 전체선택하는 체크박스 이벤트
document
  .querySelector('thead input[type="checkbox"]')
  .addEventListener("change", allCheckChange);

//선택삭제하는 체크박스 이벤트
document
  .querySelector("#delSelectBtn")
  .addEventListener("click", deleteCheckedFnc);

// 데이터 한건을 활용해서 tr이라는 요쇼를 생성.
function makeTr(item) {
  //매개값으로 받아오면 그 값을 가지고 tr을 만들어서 반환해준다
  //result베열에 들어있는 값의 갯수만큼
  //DOM요소생성.
  let titles = ["id", "lastName", "email", "hireDate", "job"];
  //데이터 건수만큼 반복
  let tr = document.createElement("tr");
  //colums의 갯수만큼 반복.
  titles.forEach(function (title) {
    let td = document.createElement("td");
    td.innerText = item[title];
    tr.append(td);
  });
  //삭제버튼
  let td = document.createElement("td");
  let btn = document.createElement("button");
  btn.innerText = "삭제";
  btn.addEventListener("click", deleteRowFunc);
  td.append(btn);
  tr.append(td);

  //수정버튼
  let td2 = document.createElement("td");
  let updateBtn = document.createElement("button");
  updateBtn.innerText = "수정";
  updateBtn.addEventListener("click", modifyTrFunc);
  td2.append(updateBtn);
  tr.append(td2);

  //체크박스
  td = document.createElement("td");
  let chk = document.createElement("input");
  chk.setAttribute("type", "checkbox");
  chk.setAttribute("class", "checkList");
  chk.addEventListener("change", checkAllFun);
  td.append(chk);
  tr.append(td);

  //tr.반환
  return tr;
}
// 삭제버튼 이벤트 콜백함수.
function deleteRowFunc() {
  let id = this.parentElement.parentElement.firstChild.innerText;
  fetch("../empListJson?del_id=" + id, {
    method: "DELETE",
  })
    .then((resolve) => resolve.json())
    .then((result) => {
      console.log(result);
      if (result.retCode == "Success") {
        alert("정상적으로 삭제 되었습니다.");
        this.parentElement.parentElement.remove();
      } else if (result.retCode == "Fail") {
        alert("삭제중 오류 발생");
      }
    })
    .catch((reject) => console.log(reject));
}

// 수정버튼 이벤트 콜백함수
function modifyTrFunc() {
  // this = 수정버튼
  // 버튼을 기준으로 앞의 td는 형제이고, tr은 부모이다
  let thisTr = this.parentElement.parentElement;
  //사원번호
  console.log("사원번호 : ", thisTr.children[0].innerText);
  //이름
  console.log("이름 : ", thisTr.children[1].innerText);

  let id = thisTr.children[0].innerText;
  let name = thisTr.children[1].innerText;
  let mail = thisTr.children[2].innerText;
  let hire = thisTr.children[3].innerText;
  let job = thisTr.children[4].innerText;

  // 변경할 항목들만 배열에 등록하는 작업
  let modItems = [name, mail, hire, job];

  //생성한 변수를 input태그에 저장
  let newTr = document.createElement("tr");

  // 사원번호 변경
  let td = document.createElement("td");
  td.innerText = id; //변경하지 않을 값(변경불가항목)
  newTr.append(td);

  // 변경할 값을 반복해서 화면에 출력하는 방법
  modItems.forEach((item) => {
    td = document.createElement("td");
    let inp = document.createElement("input");
    //스타일 적용법
    inp.style = "width : 90px";
    inp.value = item;
    td.append(inp);
    newTr.append(td);
  });
  // 수정버튼을 누르면 삭제버튼은 없앤다
  // 삭제버튼 : 비활성화
  let btn = document.createElement("button");
  btn.innerText = "삭제";
  // true가 되면 비활성화 되는 방법
  btn.disabled = true;
  td = document.createElement("td");
  td.append(btn);
  newTr.append(td);

  // 변경버튼 : DB에 반영할 수 있도록 만들기
  // 수정버튼을 누르면 수정버튼이 변경버튼으로 변경되는 곳
  btn = document.createElement("button");
  btn.innerText = "변경";
  //btn.disabled = true;
  btn.addEventListener("click", updateMemberFnc); //실행코드( function() )가 들어가면 안된다
  td = document.createElement("td");
  td.append(btn);
  newTr.append(td);

  // 체크박스 생성
  let chk = document.createElement("input");
  chk.setAttribute("type", "checkbox");
  td = document.createElement("td");
  td.append(chk);
  newTr.append(td);

  //replaceWith() : 기존에 있던걸 바꿔주는 메소드
  thisTr.replaceWith(newTr);
}

// 수정버튼 이벤트 콜백함수
function updateMemberFnc() {
  //td요소의 하위요소를 읽어와야한다
  let currTr = this.parentElement.parentElement;
  //tr의 대한 모든 자식요소
  let id = currTr.children[0].innerText;
  //input은 value를 읽어와야한다
  let name = currTr.children[1].children[0].value;
  let email = currTr.children[2].children[0].value;
  let hDate = currTr.children[3].children[0].value;
  let job = currTr.children[4].children[0].value;
  //console.log(id, name, email, hDate, job);
  fetch("../empListJson", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body:
      "param=update&id=" +
      id +
      "&name=" +
      name +
      "&email=" +
      email +
      "&hire=" +
      hDate +
      "&job=" +
      job,
  })
    .then((resolve) => resolve.text())
    .then((result) => console.log(result))
    .catch((reject) => {
      console.log(reject);
      if (result.indexOf("Success" != -1)) {
        //한건에 대한 수정을 진행할 때 다른 건은 수정될 수 없도록 만들어주기
        alert("정상적으로 처리되었습니다");
        let newTr = makeTr({
          id: id,
          lastName: name,
          email: email,
          hireDate: hDate,
          job: job,
        });
        currTr.replaceWith(newTr);
      } else {
        console.log("에러");
      }
    });
}

// 저장버튼 이벤트 콜백함수.
function addMemberFnc(evnt) {
  //이벤트 콜백함수는 이벤트를 매개변수로 받는다.
  evnt.preventDefault(); // submit은 페이지를 이동하기 하기 때문에 이 메소드를 이용해 페이지 이동을 막는다.
  console.log("여기에 출력.");
  let id = document.querySelector('input[name="emp_id"]').value;
  let name = document.querySelector('input[name="last_name"]').value;
  let email = document.querySelector('input[name="email"]').value;
  let hDate = document.querySelector('input[name="hire_date"]').value;
  let job = document.querySelector('input[name="job_id"]').value;

  if (!id || !name || !email || !hDate || !job) {
    alert("입력 다 해라");
    return;
  }

  //값이 있으면 값을 전송시키는 곳
  fetch("../empListJson", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" }, //파라미터로 key와 value를 넘겨준다
    body:
      "param=add&id=" +
      id +
      "&name=" +
      name +
      "&email=" +
      email +
      "&hire=" +
      hDate +
      "&job=" +
      job,
  })
    .then((resolve) => resolve.json()) // 받은 값을 json형식으로 바꿔주는 곳
    .then((result) => {
      // 결과를 화면에 출력한다
      if (result.retCode == "Success") {
        alert("정상처리");
        list.append(
          makeTr({
            id: id,
            lastName: name,
            email: email,
            hireDate: hDate,
            job: job,
          })
        );
        //입력 항목 초기화
        document.querySelector('input[name="emp_id"]').value = "";
        document.querySelector('input[name="last_name"]').value = "";
        document.querySelector('input[name="email"]').value = "";
        document.querySelector('input[name="hire_date"]').value = "";
        document.querySelector('input[name="job_id"]').value = "";
      } else if (result.retCode == "Fail") {
        alert("에러발생");
      }
    });
}

//전체선택 체크박스 콜백함수
function allCheckChange() {
  console.log(this.checked);
  document.querySelectorAll('tbody input[type="checkbox"]').forEach((chk) => {
    chk.checked = this.checked;
  });
}

//------------------------------------------------------------선택삭제 콜백함수(클릭이벤트)
//첫번째 방식, 이 방식으로 하면 잘안된다고 하심
function deleteCheckedFnc1() {
  document
    .querySelectorAll('tbody input[type="checkbox"]:checked')
    .forEach((chk) => {
      //삭제처리 같은 기능을 구현하기
      chk.addEventListener("click", a(chk));
    });
}
//클릭(선택삭제) 이벤트에 대한 기능
function a(chk) {
  let chkbox = chk.parentElement.parentElement.firstChild.innerText;
  fetch("../empListJson?del_id=" + chkbox, {
    method: "DELETE",
  })
    .then((resolve) => resolve.json())
    .then((result) => {
      if (result.retCode == "Success") {
        chk.parentElement.parentElement.remove();
      } else if (result.retCode == "Fail") {
        console.log("error: " + chkbox);
      }
    })
    .catch((reject) => console.log(reject));
}

//fetch API -> 비동기방식 => 동기식으로 처리하겠다라고 해주는게(async(함수앞에), await(프라미스 객체에만 사용가능))
async function deleteCheckedFnc() {
  let ids = [];
  let chks = document.querySelectorAll('#list input[type="checkbox"]:checked');

  for (let i = 0; i < chks.length; i++) {
    let id = chks[i].parentElement.parentElement.firstChild.innerText;
    let resp = await fetch("../empListJson?del_id=" + id, {
      method: "DELETE",
    });
    let json = await resp.json();
    console.log(json);
    ids.push(json);
  }
  console.log("ids>>>>", ids);
  //새로고침(바로 반영해주는 방법)
  processAfterFetch(ids); //[{id:101,retCode:Success or Fail},{}...]
}

//ary => 별 뜻 없다, ary라는 변수가 배열이라는 뜻
function processAfterFetch(ary = []) {
  let targetTr = document.querySelectorAll("#list tr");
  targetTr.forEach((tr) => {
    for (let i = 0; i < ary.length; i++) {
      if (tr.children[0].innerText == ary[i].id) {
        if (ary[i].retCode == "Success") {
          tr.remove(); // 성공 조건하에 삭제진행
        } else {
          //속성을 추가한 이유는 스타일 반영을 위함이다
          tr.setAttribute("class", "delError");
        }
      }
    }
  });
}
//------------------------------------------------------------------------------

//--------------------------------리스트 체크박스 동작

//본인/class,id이용
//list checkbox 모두 선택 시 맨 위의 checkbox가 선택되는 장소
function selectBtn() {
  let checkCount = 0;
  document.querySelectorAll(".checkList").forEach(function (check) {
    console.log(check.checked);
    if (check.checked === false) {
      checkCount++;
    }
  });
  if (checkCount > 0) {
    document.getElementById("chk-all").checked = false;
  } else if (checkCount === 0) {
    document.getElementById("chk-all").checked = true;
  }
  console.log(checkCount);
}
//document.querySelectorAll('tbody input[type="checkbox"]:checked')

//지연이/DOM 이용
function countCheck() {
  let check =
    document.querySelector("thead").children[0].children[7].children[0];
  let count = document.querySelectorAll(
    'tbody input[type = "checkbox"]'
  ).length;
  let i = document.querySelectorAll(
    'tbody input[type = "checkbox"]:checked'
  ).length;
  if (count == i) {
    check.checked = true;
  } else {
    check.checked = false;
  }
}

//교수님 / 전체선택 체크박스 - 개별 체크박스 동기화
function checkAllFun() {
  let allTr = document.querySelectorAll("tbody#list tr");
  let chkTr = document.querySelectorAll(
    'tbody#list input[type="checkbox"]:checked'
  );
  //console.log(allTr.length, chkTr.length);
  if (allTr.length == chkTr.length) {
    document.querySelector('thead input[type="checkbox"]').checked = true;
  } else {
    document.querySelector('thead input[type="checkbox"]').checked = false;
  }
}

//--------------------------------페이지목록
/* 255건 중 한페이지당 10건으로 치면 1페이지~26페이지
<< 11p~15p~20p >>
<< 21p~25~26p
마지막 26페이지는 5개만 보일 수 있도록 */
function showPages(curPage = 5) {
  let endPage = Math.ceil(curPage / 10) * 10; //만약 페이지가 5라면 10
  let startPage = endPage - 9; //1
  let realEnd = Math.ceil(255 / 10);
  endPage = endPage > realEnd ? realEnd : endPage;
  let paging = document.getElementById("paging");
  for (let i = startPage; i <= endPage; i++) {
    let aTag = document.createElement("a");
    aTag.href = "index.html";
    aTag.innerText = i;
    paging.append(aTag);
  }
}
//--------------------------------사원목록
function employeeList(curPage = 5) {
  //데이터필터링
  let end = curPage * 10;
  let start = end - 9;
  let newList = totalAry.filter((emp, idx) => {
    return idx + 1 >= start && idx < end;
  });
  let list = document.getElementById("list");
  newList.forEach((emp) => {
    let tr = makeTr(emp);
    list.append(tr);
  });
}
