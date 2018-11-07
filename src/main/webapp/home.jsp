<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  
  <title>CVBuilder</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

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

<title>Display</title>
<style>
.btn {
        background-color:red;
        cursor:pointer;
    }
.row {
  display: flex;
}

.column {
  flex: 33.33%;
  padding: 5px;
}

.imagecircle {
	border-radius: 50%
}

ul#nat li {
	list-style-type: none;
}


</style>
</head>
<body>  
<header id="header" class="header header-hide">
    <div class="container">

      <div id="logo" class="pull-left">
        <!-- Uncomment below if you prefer to use an image logo -->
        <img src="img/logo.png" width="180" height="60" alt="">
        
      </div>

      <nav id="nav-menu-container">
        <ul class="nav-menu">
          <li class="menu-active"><a href="#hero">Home</a></li>
          <li><a href="#about-us">About</a></li>
          <li><a href="#features">Features</a></li>
          <li><a href="#screenshots">Screenshots</a></li>
          <li><a href="#team">Team</a></li>
          <li><a href="#pricing">Pricing</a></li>
          <li class="menu-has-children"><a href="">Drop Down</a>
            <ul>
              <li><a href="#">Example 1</a></li>
              <li><a href="#">Example 2</a></li>
              <li><a href="#">Example 3</a></li>
            </ul>
          </li>
          <li><a href="#blog">Blog</a></li>
          <li><a href="#contact">Contact</a></li>
        </ul>
      </nav><!-- #nav-menu-container -->
    </div>
  </header><!-- #header -->
  <br>
  <br>
  <br>
<section id="hero" class="wow fadeIn">
    <div class="hero-container">
      <h1>Welcome to CVBuilder!</h1>
      <img src="img/hero-img.png" alt="Hero Imgs">
      </div>
    </div>
  </section><!-- #hero -->



<% 
	String firstname =  request.getParameter("firstname");
	String lastname = request.getParameter("lastname");
	String userName = request.getParameter("username");
	String addr = request.getParameter("address");
	String age = request.getParameter("age");
	
%>
<br>
<br>

      <h2>Hi, <%= firstname %>. Happy to see you here!</h2>
<ul id ="nat">
<li>
	<p>First Name: <%= firstname %></p>
	
</li>
<li>
	<p>Last Name: <%= lastname %></p>
	
</li>
<li>
	<p>User Name: <%= userName %></p>
	
</li>
<li>
	<p>Address: <%= addr %></p>
	
</li>
<li>
	<p>Age: <%= age %></p>

</li>
<li>
	<img src="img/profile.png" class="imagecircle" alt="profile image" width="100" height="100">
</li>

</ul>
<p><u>Some examples of templates that you can use: </u></p>
<b><i>TRY AND CLICK THE TEMPLATE YOU LIKED TO START MAKING A CV</i></b>
<div class="row">
  <div class="column">
  <a href="latexresume?username=<%=userName%>"><img src="img/Example1.png" alt="The 1st example" width="200px" height="400px"></a>
  </div>
  <div class="column">  
	<a href="latexresume?username=<%=userName%>"><img src="img/Example2.png" alt="The 2nd example" width="200px" height="400px"></a>
  </div>
  <div class="column">
	<a href="latexresume?username=<%=userName%>"><img src="img/Example3.png" alt="The 3rd example" width="200px" height="400px"></a>
  </div>
</div>
<br>
<button class="btn" type="submit" value="logout">Logout</button>

<br>
use " <i> select * from student; </i> " in mysql client to verify it.
</body>
</html>