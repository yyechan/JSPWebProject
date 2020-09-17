package com.yechan.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yechan.daoto.BoardCommentDAO;
import com.yechan.daoto.BoardCommentDTO;

/**
 * Servlet implementation class CommentWriteOk
 */
@WebServlet("/CommentWriteOk")
public class CommentWriteOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentWriteOk() {
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

		
		request.setCharacterEncoding("EUC-KR");
		response.setCharacterEncoding("EUC-KR");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		int cNum = Integer.parseInt(request.getParameter("bNum"));
		String cContent = request.getParameter("cContent");
		String cId = (String)session.getAttribute("id");
		Timestamp cDate = new Timestamp(System.currentTimeMillis());
		int cAvailable = 1;
		
		BoardCommentDAO dao = BoardCommentDAO.getInstance();
		BoardCommentDTO dto = new BoardCommentDTO(cNum,cId,cContent,cDate,cAvailable);
		
		int result = dao.insertComment(dto);
		
		if(result < 0)
		{
			out.println("<script>");
			out.println("alert('댓글작성에 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.flush();
		}else
		{
			out.println("<script>");
			out.println("alert('댓글이 작성되었습니다.')");
			out.println("location.href = 'boardView.jsp?bNum=" + cNum + "'");
			out.println("</script>");
			out.flush();
			
		}
		
		
		
		
		out.close();
		
	}
}
