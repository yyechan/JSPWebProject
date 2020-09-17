<%@page import="com.yechan.daoto.MemberDTO"%>
<%@page import="com.yechan.daoto.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
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

<title>yechan's web</title>

<script>

function modifyConfirm()
{
	var form = document.mf_frm;
	
	
	if(form.pw.value.length == 0)
		{
		alert("��й�ȣ�� �ʼ� �����Դϴ�.");
		form.pw.focus();
		return;
		}
	
	if(form.name.value.length == 0)
		{
		alert("�̸��� �ʼ� �����Դϴ�.");
		form.name.focus();
		return;
		}
	
	if(form.eMail.value.length == 0)
	{
	alert("�̸����� �ʼ� �����Դϴ�.");
	form.eMail.focus();
	return;
	}
	
	if(form.address.value.length == 0)
	{
	alert("�ּҴ� �ʼ� �����Դϴ�.");
	form.address.focus();
	return;
	}
	
	form.submit();
	
}

</script>

<style>

html, body{
min-width : 1024px;
}

#head {
	text-align: center;
	margin-top: 30px;
}


 .navbar{
    
    padding-left: 50px;
    padding-right: 50px;
    }
    
.form-group input {
	
	width : 400px;
	margin : 0 auto;
}

.container {
	text-align : center;
	width : 600px;
	border: 1px solid #ccc;
    border-radius: 4px;
    margin : 0 auto;
    padding : 50px;
    
}


.form-group input
{

width : 300px;
}

</style>



</head>


<%!
	String id;
	String pw;
	String name;
	String eMail;
	String address;
	
%>

<% 

	id = (String)session.getAttribute("id");
	pw = (String)session.getAttribute("pw");
	
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO dto = dao.getMembersFromId(id);
	
	name = dto.getName();
	eMail = dto.geteMail();
	address = dto.getAddress();
	
    
%>


<body>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<!-- Brand -->
		<a class="navbar-brand" href="index.jsp">Home</a>

		<!-- Links -->
		<ul class="navbar-nav ">

			<li class="nav-item"><a class="nav-link" href="board.jsp">�Խ���</a></li>

		</ul>



	</nav>
	
	
	
	
	<div id="head">
			<h1>ȸ�� ���� ����</h1>
		</div>
	

<div class = "container"> 

<form action="ModifyOk" class="was-validated" name ="mf_frm" method = "post">
  <div class="form-group">
    <label >���̵�:</label> <br>
    <button type="button" class="btn btn-outline-secondary" disabled style ="width : 300px; height : 38px; text-align : left ;"><%= id %></button>
    
   
  </div>
  
  
  <div class="form-group">
    <label for="pwd">��й�ȣ:</label>
    <input type="password" class="form-control" id="pwd" placeholder="��й�ȣ�� �Է����ּ���" name="pw" required>
  </div>
  
  <div class="form-group">
  	<label>�̸�:</label>
  	<input type= "text" class="form-control" value = "<%= name%>"placeholder="�̸��� �Է����ּ���" name="name" required>
  </div>
  
  <div class="form-group">
  	<label>�̸���:</label>
  	<input type= "text" class="form-control" value = "<%= eMail%>" placeholder="�̸����� �Է����ּ���" name="eMail" required>
  </div>
  
  <div class="form-group">
  	<label>�ּ�:</label>
  	<input type= "text" class="form-control" value = "<%= address%>"placeholder="�ּҸ� �Է����ּ���" name="address" required>
  </div>
  


  <button type = "button" class="btn btn-secondary" onclick = "modifyConfirm();">����</button>
  <button type = "button" class="btn btn-secondary" onclick = "history.back();">���</button>

 </form>

</div>



</body>
</html>