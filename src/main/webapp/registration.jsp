<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Data</title>
</head>


  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Roboto:100,300,400,500,700|Philosopher:400,400i,700,700i" rel="stylesheet">

  <!-- Bootstrap css -->
  <!-- <link rel="stylesheet" href="css/bootstrap.css"> -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">


  <!-- Libraries CSS Files -->
  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="lib/owlcarousel/assets/owl.theme.default.min.css" rel="stylesheet">
  <link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="lib/animate/animate.min.css" rel="stylesheet">
  <link href="lib/modal-video/css/modal-video.min.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="css/style.css" rel="stylesheet">

<style>
body {
	margin: 0;
	padding: 0;
	width: 100%;
	text-align: center;
	background-repeat: no-repeat;
    background-attachment: fixed;
	background: url("https://images.unsplash.com/photo-1432821596592-e2c18b78144f?ixlib=rb-0.3.5&s=a8ce5bf203fdaf826d2db0b2d8d51544&auto=format&fit=crop&w=1500&q=80");
}

ul#nat li {
	list-style-type: none;
	display: inline-block;
	color: white;
	font-size: 20px;
	padding: 20px;
}
section.registerform {
	text-align: center;
	margin-top: 350px;
}
div.ex{
	border: 2px solid #26AEF2;
	border-radius: 10px;
/* 	margin-left: 20%;
	margin-right: 20%; */
	text-align: center;
	width: 50%;
	margin:0 auto;
	background-color: black;
 	opacity: 0.6;

}
section.registerform {
	text-align: center;
	margin-top: 350px;
}

h1 {
	color: #26AEF2;
}
.btn{
	background-color: #26AEF2;
	color: white;
}


</style>
<body>

  <section class="registerform">

  <h1>Registration Form</h1>

	<div class="ex">
		<form action="RegistrationController" method="post">
			<ul id="nat">
				<div id="1">
					<li>First Name</li>
					<li><input type="text" name="firstname" /></li>
					<li>Last Name</li>
					<li><input type="text" name="lastname" /></li>
				</div>
				<div>
					<li>Username</li>
					<li><input type="text" name="username" /></li>
					<li>Password</li>

					<li><input type="password" name="password" /></li>

					<li><input type="text" name="password" /></li>
				</div>
				<div>
					<li>Address</li>
					<li><input type="text" name="address" /></li>
					<li>Age</li>
					<li><input type="text" name="age" /></li>
				</div>
			</ul>
			<input type="submit" class="btn" value="Register" />		
<!-- 			
<button type="button" class="btn">Register</button>
 -->		</form>
		<br>
  </section>
<!-- 

				<div>
					<li>Photo</li>
					<li><input type="file" name="photo" /></li>
				</div>	
			</ul>
			<button type="button" class="btn">Register</button>		
<!-- 			<input type="submit" value="Register" />
 -->		</form>
		<br>
  </section>
<!-- 
	
	create a student table in test database before registering this form
		<br> Syntax : <br>
		<i>create table student(name varchar(100), userName varchar(100), pass varchar(100), addr varchar(100), age int, qual varchar(100), percent varchar(100), year varchar(100));</i>
		
		 -->
	</div>
	
</body>
</html>