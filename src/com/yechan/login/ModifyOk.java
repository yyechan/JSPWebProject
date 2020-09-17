package com.yechan.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yechan.daoto.MemberDAO;
import com.yechan.daoto.MemberDTO;

/**
 * Servlet implementation class ModifyOk
 */
@WebServlet("/ModifyOk")
public class ModifyOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doAction(request,response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("EUC-KR");
		response.setCharacterEncoding("EUC-KR");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		String session_pw = (String)session.getAttribute("pw");
		
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		Timestamp rDate = (Timestamp)session.getAttribute("rDate");
		String address = request.getParameter("address");
		
		if(!session_pw.equals(pw))
		{
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
		}
		else {
			
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO(id,pw,name,eMail,rDate,address);
		
		if(dao.ModifyMembers(dto)==dao.MEMBER_MODIFY_SUCCESS)
		{
			out.println("<script>");
			out.println("alert('수정되었습니다.')");
			out.println("location.href = 'index.jsp'");
			out.println("</script>");
			out.flush();
			
			session.setAttribute("name", dto.getName());
			
		}
		
		}
	}
	
	
}
