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
  html,body{
min-width : 1024px;
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
   <script>
   
   alert("로그인이 필요합니다.");
   location.href="login.jsp";

   </script>
	
    <%
   }
	%>
	</ul>
</nav>


<div class ="container">
	<div class="row">
			<form action = "WriteOk" method = "post" style = "width : 100%">
				<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd;">
					<thead>
						<tr>
							<td colspan="2"
								style="background-color: #eeeeee; text-align: center;">게시판
								글쓰기</td>
						</tr>
						

					</thead>
					
					


	
					<tbody>
						<tr>
							<td><input type="text" class="form-control"
								placeholder="글 제목" name="bTitle" maxlength="50" required></td>
						</tr>	
						
						<tr>	
							<td><textarea type="text" class="form-control"
									placeholder="글 내용" name="bContent" style="height : 350px;" required></textarea>
							</td>
						</tr>

					</tbody>

					

				</table>
				
				
				<button type="submit" class="btn btn-secondary" style="float: right;">작성</button>
				
			</form>



		</div>




</div>






</body>
</html>