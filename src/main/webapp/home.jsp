<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <title>CVBuilder</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

<<<<<<< HEAD
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Roboto:100,300,400,500,700|Philosopher:400,400i,700,700i" rel="stylesheet">
		<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
=======
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
body {
text-align: center;
}

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
/* 	font-size: 20px;	
 */}

.bio {
	background-color: #26AEF2;
	display: inline-block;
	padding: 20px;
	width: 80%;
}


</style>
</head>
<body>  
<header id="header" class="header header-hide">
    <div class="container">

      <div id="logo" class="pull-left">
        <!-- Uncomment below if you prefer to use an image logo -->
        <img src="img/logo.png" width="180" height="60" alt="">
>>>>>>> addeefd05f093add38718cee2d2760b9c0ec544c
        
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
                background-color: #26AEF2;
                cursor: pointer;
                color: white;
            }
            body {
            	margin: 0;
            	padding: 0;
            	width: 100%;
            	color: white;
            	background-repeat: no-repeat;
            	background-attachment: fixed;
            	background: url("https://images.pexels.com/photos/956999/milky-way-starry-sky-night-sky-star-956999.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
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
                
            	color: white;
            }
            h2 {
            color: #26AEF2;
        	}
        	p {
            color: white;
        	}
            div.loginform {
            	text-align: center;
            	margin-top: 0px;
            	
            	color: white;
       	 	}
        	div.ex {
            	border: 2px solid #26AEF2;
            	border-radius: 10px;
            	text-align: center;
            	width: 50%;
            	margin: 0 auto;
            	background-color: black;
            	color: white;
        	}
        </style>
    </head>

    <body>
        <header id="header" class="header header-hide">
                <div id="logo" class="pull-left">
                    <img src="img/logo.png" width="180" height="60" alt="">
                </div>
  		</header>
  <br>
  <br>
  <br>


<% 
	String firstname =  request.getParameter("firstname");
	String lastname = request.getParameter("lastname");
	String userName = request.getParameter("username");
	String addr = request.getParameter("address");
	String age = request.getParameter("age");
	
%>
<<<<<<< HEAD
      
<div class="loginform">
	<h2>Hi <%= firstname %> <%= lastname %>. Happy to see you here!</h2>
    	<div class="ex">
			<ul id ="nat">
				<li>
					<p>Address: <%= addr %></p>
				</li>
				<li>
					<p>Age: <%= age %></p>
				</li>
    		</ul>
            <b>Click the button below  to start filling information</b>
            	<br>
            	<br>
            </div>
            <div>
                <a class="btn" href="latexresume?username=<%=userName%>">Create CV </a>
		 
            	<a class="btn" href="logout">Logout <span class="glyphicon glyphicon-log-out"></span></a>
                    
            </div>
           
    </body>
    </html>
=======
<br>
<br>

      <h2>Hi, <%= firstname %>. Happy to see you here!</h2>
      <div class="bio">
<ul id ="nat">
<div>
<li>
	<p>First Name: <%= firstname %></p>
	
</li>
<li>
	<p>Last Name: <%= lastname %></p>
</li>
</div>
<div>
<li>
	<p>User Name: <%= userName %></p>
	
</li>
<li>
	<p>Address: <%= addr %></p>
	
</li>
<li>
	<p>Age: <%= age %></p>

</li>
</div>
<!-- <li>
	<img src="img/profile.png" class="imagecircle" alt="profile image" width="100" height="100">
</li>
 -->
</ul>
</div>
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

<a href="/pagingexercise361/latexresume.jsp"><button class="btn">Try samples now</button></a>
<br>
use " <i> select * from student; </i> " in mysql client to verify it.

</body>
</html>
>>>>>>> addeefd05f093add38718cee2d2760b9c0ec544c
