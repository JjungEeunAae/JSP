package co.yedam.emp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;

public class loginControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("로그인 컨트롤...");
		String method = req.getMethod();
		System.out.println("요청방식 : " + method);
		//요청정보를 가지고 있음
		String id = req.getParameter("uid"); //form 하위에 있는 input에 해당되는 name 속성을 적는다
		String pw = req.getParameter("upw");
		System.out.println("id : " + id + ", pw : " + pw);
	}

}
