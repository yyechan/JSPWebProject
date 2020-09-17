package com.yechan.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.glass.ui.Application;
import com.yechan.daoto.BoardDAO;
import com.yechan.daoto.BoardDTO;

/**
 * Servlet implementation class WriteOk
 */
@WebServlet("/WriteOk")
public class WriteOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteOk() {
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
		// TODO Auto-generated method stub
		doAction(request,response);
	}
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ServletContext temp;
		System.setProperty("user.timezone", "Asia/Seoul");

		
		request.setCharacterEncoding("EUC-KR");
		response.setCharacterEncoding("EUC-KR");
		
		PrintWriter out = response.getWriter();
		
		int result = 0;
		BoardDAO dao = BoardDAO.getInstance();
		HttpSession session = request.getSession();
		
		
		int bNum = dao.getNextNum();
		String bTitle = request.getParameter("bTitle");
		String bId = (String)session.getAttribute("id");
		Timestamp bDate = new Timestamp(System.currentTimeMillis());
		String bContent = request.getParameter("bContent");
		int bAvailable = 1;
		
		BoardDTO dto = new BoardDTO(bNum,bTitle,bId,bDate,bContent,bAvailable);
		
		result = dao.InsertBoard(dto);
		
		
		if(result != 1 )
		{
			out.println("<script>");
			out.println("alert('글 작성에 실패하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.flush();
		}
		else
		{
			out.println("<script>");
			out.println("alert('작성이 완료되었습니다');");
			out.println("location.href = 'board.jsp';");
			out.println("</script>");
			out.flush();
			
		}
			
		
		out.close();

	}
	
}
