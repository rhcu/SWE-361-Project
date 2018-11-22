<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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
<script src="scripts/jquery.min.js"></script>
<script src="scripts/lodash.min.js"></script>
<script type="text/javascript">

items = [];

var loginClicked = "";
var registrationClicked = "";

function updateList() {
 $("#my-list").html("");
 items.forEach(function(e) {
  $("#my-list").append("<li>" + e + "</li>");
 });
}

function getListItems() {
 $.ajax({
  url: "admin_panel",
  dataType: 'json',
  success: function(r) {
   console.log(r);
   items = r;
   updateList();
  }
 });
}

$(document).ready(function() {
 $("#my-list").append("<li>test");
 getListItems();
 $("#send-item").on('click', function () {
  sendListItem();
 });
});

function sendListItem() {

 var s = "";
 var loginCheckBox = document.getElementById("loginCheck");
 var registrationCheckBox = document.getElementById("registrationCheck");
 var LatexResume1CheckBox = document.getElementById("LatexResume1Check");
 var LatexResume2CheckBox = document.getElementById("LatexResume2Check");
 var LogoutCheck = document.getElementById("LogoutCheck");

 if (loginCheckBox.checked == true){
  s += "login";
 }

    if (registrationCheckBox.checked == true){
        s += " registration";
 }

    if (LatexResume1CheckBox.checked == true){
  s += " Resume1";
 }

    if (LatexResume2CheckBox.checked == true){
  s += " Resume2";
 }
    
    if(LogoutCheck.checked == true) {
     s += " Logout";
    }

    $.post("admin_panel", { texttosend: s}, function() {
  getListItems();
 });

}

</script>
<style>
            body {
    margin: 0 auto;
    font-family: Philosopher;
    /* text-align: center; */
    		 background-repeat: no-repeat;
             background-attachment: fixed;
             background: url("https://images.pexels.com/photos/956999/milky-way-starry-sky-night-sky-star-956999.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
             /*background-size:  cover; */
            }
            
            .container nav {
             height: 80px;
          
            }
            
            .container h1 {
             padding-top: 20px;
             color: white;
             font-size: 30px;
             /* margin-left: 50px; */
            }
            
            .container h3 {
             margin-left: 100px;
             margin-top: 15px;
             color: white;
             font-size: 25px;
            }
            
            .list-group {
             list-style-type: none;
            }
            
            .list-group li {
             color: white;
             font-family: Roboto;
             font-size: 15px;
            }
            
            .btn {
             margin-left: 100px;
             margin-top: 40px;
             background-color: #26AEF2;
             color: white;
             font-family: Roboto;
             font-size: 15px;
            }
            
            .type {
             margin-top: 5px;
             margin: 0 auto;
             width: 70%;             
            }
            
            p {
             color: white;
             font-size: 20px;
             font-family: Philosopher;
            }
            
            .pull-left {
             float: left;
            }
            
           input[type=checkbox]{
         height: 0;
         width: 0;
         visibility: hidden;
       }
       
       label {
         cursor: pointer;
         text-indent: -9999px;
         width: 80px;
         height: 30px;
         background: grey;
         display: block;
         border-radius: 15px;
         position: relative;
       }
       
       label:after {
         content: '';
         position: absolute;
         top: 2.5px;
         left: 2.5px;
         width: 25px;
         height: 25px;
         background: #fff;
         border-radius: 25px;
         transition: 0.3s;
       }
       
       input:checked + label {
         background: #26AEF2;
       }
       
       input:checked + label:after {
         left: calc(100% - 2.5px);
         transform: translateX(-100%);
       }
       
       label:active:after {
         width: 45px;
       }
       
     /*   .star {
        margin-left: 500px;
       } */
       
      /*  body {
         display: flex;
         justify-content: center;
         align-items: center;
         height: 50vh;
       }
          */
          
</style>
</head>
<body>

     -->
    <div class="container">

     <nav class="navbar fixed-top navbar-light" style="background-color: #26AEF2;">
      <div id="logo" class="pull-left">
       <img src="img/logo.png" width="180" height="60" alt="">
      </div>
     <h1>Welcome to Admin's panel!</h1><span><a href="logout" class="logout">Logout</a></span>
    
  </nav>
   <h3>Please choose logs: </h3>

        <br>
        
        <div class="type"> 
           <p>Login <input type="checkbox" id="loginCheck" /><label class="star" for="loginCheck">Toggle</label></p>
           <p>Registration <input type="checkbox" id="registrationCheck" /><label class="star" for="registrationCheck">Toggle</label></p>
           <p>Resume 1<input type="checkbox" id="LatexResume1Check" /><label class="star" for="LatexResume1Check">Toggle</label></p> 
           <p>Resume 2<input type="checkbox" id="LatexResume2Check" /><label class="star" for="LatexResume2Check">Toggle</label></p>
   <p>Logout <input type="checkbox" id="LogoutCheck" /><label class="star" for="LogoutCheck">Toggle</label></p>
   </div>
   
   <input id="send-item" type="submit" class="btn" value="Submit" />
         <ul class="list-group" id="my-list"></ul>
    </div>


</body>
</html>