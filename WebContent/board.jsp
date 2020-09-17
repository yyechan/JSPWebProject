<%@page import="com.yechan.daoto.BoardCommentDTO"%>
<%@page import="com.yechan.daoto.BoardCommentDAO"%>
<%@page import="com.yechan.daoto.BoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.yechan.daoto.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%
	String name = (String) session.getAttribute("name");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">


<title>Insert title here</title>

    <style>
    
    html, body{
    min-width : 1024px;
    width : 100%;
    }
    
    .navbar{
    
    padding-left: 50px;
    padding-right: 50px;
    }
    
    .container
    {
    width:100%;
   	padding-left : 30px;
   	padding-right:30px;
    padding-top: 50px;
    }
    
	.row
    {
 	width : 100%;
 	}
	
    </style>
    
</head>
<%
	int pageNumber = 1;

	if(request.getParameter("pageNumber") != null)
	{
		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	}


%>


<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="index.jsp">Home</a>

  <!-- Links -->
  <ul class="navbar-nav ">
  
    <li class = "nav-item">
      <a class="nav-link" href="board.jsp">게시판</a>
    </li>
    
  </ul>
  
  
  <% if(name != null){ %>
	<ul class = "navbar-nav ml-auto">	
	<div class="btn-group">
	<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"><%= name%> 님</button>
	<div class="dropdown-menu">
      <a class="dropdown-item" href="modify.jsp">회원정보 수정</a>
      <a class="dropdown-item" href="LogoutOk">로그아웃</a>
    </div>
    </div>
	</ul>
  <% }
  	else {
   %>
   <ul class = "navbar-nav ml-auto">	
	<a class = "btn btn-secondary" role = "button" href = "login.jsp">로그인</a>
    <%
   }
	%>
	</ul>
</nav>


<div class ="container">
	<div class="row">
		<table class="table" style="text-align: center
		; border : 1px solid #dddddd ; border-radius : 30px;">
		
			<thead>
				<tr class = "thead-light"style = "font-size : 0.8rem;">
					<th style="background-color: #eeeeee; text-align:center;min-width:80px; width:80px; ">번호</th>
					<th style="background-color: #eeeeee; text-align:left; ">제목</th>
					<th style="background-color: #eeeeee; text-align:center; width : 100px">작성자</th>
					<th style="background-color: #eeeeee; text-align:center; width : 100px;">작성일</th> 
				</tr>
			</thead>
			
			<tbody>
				<%
					BoardDAO dao = BoardDAO.getInstance();
					ArrayList<BoardDTO> dtos = dao.getBoards(pageNumber);
					for(int i = 0; i < dtos.size() ; i++){	

						
				%>
					<tr>
						<td style = "font-size : 0.8rem;"><%=dtos.get(i).getbNum()%></td>
						<td style = "text-align:left;"><a href="boardView.jsp?bNum=<%=dtos.get(i).getbNum()%>" style = "color:gray;">
								<%=dtos.get(i).getbTitle()%></a>&nbsp;
								<%
								BoardCommentDAO cdao = BoardCommentDAO.getInstance();
								ArrayList<BoardCommentDTO> cdtos = cdao.getComments(dtos.get(i).getbNum());
								
								int size = cdtos.size();
								
								if(size>0)
								{	
								%>
								[<%=size%>]
								<%
								}
								%>
						</td>
						<td style = "font-size : 0.8rem;"><%=dtos.get(i).getbId()%></td>
						<td style = "font-size : 0.8rem;"><%=dtos.get(i).getbDate().getYear()+1900%>.<%=dtos.get(i).getbDate().getMonth()+1%>.<%=dtos.get(i).getbDate().getDate()%></td>
					
					</tr>
				<% 
					}
				%>
					
				</tbody>
		</table>
		
		
		
		<%
			if(pageNumber != 1){
		%>
			<a href = "board.jsp?pageNumber=<%=pageNumber - 1 %>" class ="btn btn-success btn-arraw-left">이전</a>
		<%
			} if(dao.nextPage(pageNumber+1)){
		%>
		<a href = "board.jsp?pageNumber=<%= pageNumber+1 %>" class = "btn btn-success btn-arraw-left">다음</a>
		<%
			}
		%>
		
		<div style = "margin : 0 auto;">
		
		
		<% int totalpage = (int)Math.ceil((double)dao.getNextNum()/10);
		%>(&nbsp;
		<%
			for(int i = 1; i<=totalpage; i++)
			{
				%>
				
				<a href = "board.jsp?pageNumber=<%=i %>" ><%=i%></a>
				<%
			}
		%>
		
		)
		
		</div>
		
		
		<a href = "write.jsp" class ="btn btn-secondary ml-auto">글쓰기</a>
	
	</div>




</div>







</body>
</html>