package com.yechan.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yechan.daoto.MemberDAO;
import com.yechan.daoto.MemberDTO;

/**
 * Servlet implementation class LoginOk
 */
@WebServlet("/LoginOk")
public class LoginOk extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request, response);
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int loginResult = 0;
		
		request.setCharacterEncoding("EUC-KR");
		response.setCharacterEncoding("EUC-KR");
		
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = dao.getMembersFromId(id);
		
		
		if(dto == null)
		{
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호를 확인해 주세요.')");
			out.println("location.href = 'login.jsp'");
			out.println("</script>");
			out.flush();
			
		}
		else if(dto.getId().equals(id) && dto.getPw().equals(pw))
		{
				
				HttpSession session = request.getSession();
				session.setAttribute("id", dto.getId());
				session.setAttribute("pw", dto.getPw());
				session.setAttribute("name",dto.getName());
				session.setAttribute("rDate", dto.getrDate());
				session.setAttribute("LoginSession",1);
				
				
				loginResult = 1;
				response.sendRedirect("index.jsp");
				
		}
		
			
		
		if(loginResult == 0)
		{
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호를 확인해 주세요.')");
			out.println("location.href = 'login.jsp'");
			out.println("</script>");
			out.flush();
			
		}
		
			
	
	}
}
