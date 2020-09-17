<%@page import="com.yechan.daoto.BoardCommentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.yechan.daoto.BoardCommentDAO"%>
<%@page import="com.yechan.daoto.BoardDTO"%>
<%@page import="com.yechan.daoto.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!doctype html>

<%
	String name = (String) session.getAttribute("name");
	int bNum = Integer.parseInt(request.getParameter("bNum"));
%>

<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>yechan's</title>
    
     <style>
    html, body{
    min-width : 1024px;
    width:100%;
    }
    .navbar{
    
    padding-left: 50px;
    padding-right: 50px;
    }
    
    .container
    {
    padding-left : 50px;
    padding-top : 50px;
    width : 100%;
    }
    
    .row
    {
    	width:100%;
    }
    
   
    </style>
    
    
    
  </head>
<body>

<%
	BoardDAO dao = BoardDAO.getInstance();
	BoardDTO dto = dao.getBoardFrombNum(bNum);


%>


<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="index.jsp">Home</a>

  <!-- Links -->
  <ul class="navbar-nav ">
  
    <li class = "nav-item">
      <a class="nav-link" href="board.jsp">�Խ���</a>
    </li>
    
  </ul>
  
  
  <% if(name != null){ %>
	<ul class = "navbar-nav ml-auto">	
	<div class="btn-group">
	<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"><%= name%> ��</button>
	<div class="dropdown-menu">
      <a class="dropdown-item" href="modify.jsp">ȸ������ ����</a>
      <a class="dropdown-item" href="LogoutOk">�α׾ƿ�</a>
    </div>
    </div>
	</ul>
  <% }
  	else {
   %>
   
   
   <ul class = "navbar-nav ml-auto">	
	<a class = "btn btn-secondary" role = "button" href = "login.jsp">�α���</a>
	</ul>
    <%
   }
	%>
	
</nav>

    	
    
<div class ="container">
	<div class="row">
			<form action = "WriteOk" method = "post" style = "width : 100%">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd;">
					<thead>
						<tr style = "font-size : 0.8rem;">
							<th style="background-color: #eeeeee; text-align:center; min-width : 80px;">�� ����</th>
							<th style="background-color: #eeeeee; text-align:center;  width : 100px;">�ۼ���</th>
							<th style="background-color: #eeeeee; text-align:center; width : 100px;">�ۼ�����</th>
								
						</tr>
					</thead>

					<tbody>
						
						<tr style = "background-color:white;">
							<td style="text-align:center;"><%=dto.getbTitle() %></td>
							<td style="font-size : 0.8rem;text-align:center;"><%=dto.getbId() %></td>
							<td style="font-size : 0.8rem;text-align:center;"><%=dto.getbDate().getYear()+1900%>.<%=dto.getbDate().getMonth()+1%>.<%=dto.getbDate().getDate()%></td>
						</tr>
						
						<tr>
							<td colspan = "3" style = "min-height : 200px; text-align : left; padding : 50px">
							<%=dto.getbContent().replaceAll(" ","&nbsp;").replaceAll("<", "&lt;").replaceAll("\n","<br>") %>
							</td>
						
						</tr>
						
					</tbody>

				</table>
			
			</form>



		</div>




</div>

<div class ="container">
	<div class="row">
		<table class="table table-striped" style="text-align: center; border : 1px solid #dddddd">
			<thead>
				<tr style = "height : 40px;">
					<th style="background-color: #eeeeee; text-align:center; width:80px;">���</th>
				</tr>
			</thead>
			
			<tbody >
				<%
				
					BoardCommentDAO cdao = BoardCommentDAO.getInstance();
					ArrayList<BoardCommentDTO> cdtos = cdao.getComments(bNum);
					
					
					for(int i = 0; i < cdtos.size() ; i++){	
				%>
				
					<tr>
						<td style = "text-align : left ;background-color : white;">
							<%=cdtos.get(i).getcId()%>	<br>
							<div style = "text-color : gray; font-size:10px;">
							<%=cdtos.get(i).getcDate()%> <br> <br>
							</div>
				
						<%=cdtos.get(i).getcContent().replaceAll(" ","&nbsp;").replaceAll("<", "&lt;").replaceAll("\n","<br>") %>
						</td>
					<tr>
				<% 
					}
				%>
					
				</tbody>
    
    		<%if(name!=null)
    		{	
    			
    		
    		%>
				<form action ="CommentWriteOk" method = "post">
				
				
				<input type="hidden" name="bNum" value = <%= bNum%>> 
				
				<tr>
					<td><textarea type="text" class="form-control"
							placeholder="��� ����" name="cContent" required></textarea></td>
					
				</tr>
				
				<tr>
					<td>
						<button type="submit" class="btn btn-secondary" >��� �ۼ�</button>
					</td>
				</tr>
			
				</form>
				
				
			<% } 
    		
    		
     		else { %>
     		<tr>
     			<td> <a href ="login.jsp">�α���</a>�� �Ͻø� ����� �ۼ��Ͻ� �� �ֽ��ϴ�.
     			</td>
     		</tr>
     		
     		
     		<%} %>
    	
    
    	</table>
    	
  </div>
    	
    	
    	

</body>
</html>