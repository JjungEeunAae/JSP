package co.yedam.emp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;

public class loginControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("로그인 컨트롤...");

	}

}
