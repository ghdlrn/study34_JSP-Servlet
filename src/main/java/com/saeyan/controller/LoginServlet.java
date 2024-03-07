package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dto.MemberDAO;
import com.saeyan.dto.MemberVO;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dis = request.getRequestDispatcher("member/login.jsp");
		dis.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		
		MemberDAO mDao = MemberDAO.getInstance();
		String url = "member/login.jsp";
		// -1 : 비밀번호X, 0 : 아이디X, 1: 로그인 성공
		int result = mDao.userCheck(userid, pwd);
		HttpSession session =request.getSession();
		MemberVO vo = mDao.getMember(userid);
		
		if(result == 1) {
			session.setAttribute("loginUser", vo);
			request.setAttribute("message", "로그인 성공했습니다.");
			url = "member/main.jsp";
		} else if(result == 0) {
			request.setAttribute("message", "존재하지 않는 회원입니다.");
		} else if(result == -1) {
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
