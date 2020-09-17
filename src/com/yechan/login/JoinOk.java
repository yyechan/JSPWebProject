package com.yechan.login;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class JoinOk
 */
@WebServlet("/JoinOk")
public class JoinOk extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinOk() {
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
		
		MemberDAO dao = MemberDAO.getInstance();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		Timestamp rDate = new Timestamp(System.currentTimeMillis());
		
		
		
		if(dao.confirmId(id) == dao.MEMBER_EXISTENT )
		{
			out.println("<script>");
			out.println("alert('이미 존재하는 아이디 입니다.')");
			out.println("location.href = 'join.html'");
			out.println("</script>");
			
			out.flush();
		}
		

		MemberDTO dto = new MemberDTO(id,pw,name,eMail,rDate,address);
		int insertResult = dao.InsertMembers(dto);
			

		if( insertResult == dao.MEMBER_JOIN_SUCCESS)
		{

			out.println("<script>");
			out.println("alert('회원가입이 완료되었습니다.')");
			
			
			HttpSession session = request.getSession();
			session.setAttribute("id", id);			
			out.println("location.href = 'login.jsp'");
			out.println("</script>");
			out.flush();
		}
		else
		{

			out.println("<script>");
			out.println("alert('회원가입에 실패하였습니다.')");
			out.println("location.href = 'join.html'");
			out.println("</script>");
			
			out.flush();
		}
		
		
		out.close();
		
	}
	
}
