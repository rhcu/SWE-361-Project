<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
            background: url("https://images.pexels.com/photos/956999/milky-way-starry-sky-night-sky-star-956999.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
            }
        ul#nat li {
            list-style-type: none;
            display: inline-block;
            color: white;
            font-size: 20px;
            padding: 20px;
        }
        div.registerform {
            text-align: center;
            margin-top: 150px;
        }
        div.ex {
            border: 2px solid #26AEF2;
            border-radius: 10px;
            text-align: center;
            width: 50%;
            margin: 0 auto;
            background-color: black;
        }
        h1 {
            color: #26AEF2;
        }
        .btn {
            background-color: #26AEF2;
            color: white;
        }
    </style>

    <body>

        <div class="registerform">
            <div class="ex">
                <form action="RegistrationController" method="post">
                    <ul id="nat">
                       		<div>
                            <li>First Name</li>
                            <li>
                                <input type="text" name="firstname" />
                            </li>
                            </div>
                            <div>
                            <li>Last Name</li>
                            <li>
                                <input type="text" name="lastname" />
                            </li>
                            </div>
                            <div>
                            <li>Username</li>
                            <li>
                                <input type="text" name="username" />
                            </li>
                            </div>
                            <div>
                            <li>Password</li>
                            <li>
                                <input type="password" name="password" />
                            </li>
                            </div>
                            <div>
                            <li>Address</li>
                            <li>
                                <input type="text" name="address" />
                            </li>
                            </div>
                            <div>
                            <li>Age</li>
                            <li>
                                <input type="text" name="age" />
                            </li>
                            </div>
                    </ul>
                    <input type="submit" class="btn" value="Register" />
                </form>
                <p>
					<a class="btn" href="login.jsp">Login</a>
				</p>
                <br>
       		</div>
        </div>
    </body>

    </html>