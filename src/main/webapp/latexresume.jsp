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
<script src="scripts/jquery.min.js">
</script>
<script>

experience_num = 1;
education_num = 1;
project_num = 1;
function addExperienceField(v){
	if(!v){
		v = experience_num + 1;
	}
	$("#experience").append("<div id='experienceField-"+v+"'></div>");
	currentField = "#experienceField-"+v;
	$(currentField).append("<input width='100%' class='form-control' name='title-"+v+"' placeholder='Title'><br>");
	$(currentField).append("<input width='100%' class='form-control' type='text' name='company-"+v+"' placeholder='Company'><br>");
	$(currentField).append("<input width='100%' class='form-control' type='text' name='dates-"+v+"'='dates-"+v+"' placeholder='Dates'><br>");
	$(currentField).append("<input width='100%' class='form-control' type='text' name='location-"+v+"' placeholder='Location'><br>");
	$(currentField).append("<textarea width='100%' class='form-control' name='description-"+v+"' placeholder='Description'></textarea><br>");
	$(currentField).append("<input type='button' id='deleteExperience' data-num='"+v+"' value='Delete'>");
	$(currentField).append("<hr>");
}

function addEducationField(){
	var v = education_num.toString();
	$("#education").append("<input width='100%' class='form-control' type='text' name='school-"+v+"' placeholder='School'><br>");
	$("#education").append("<input width='100%' class='form-control' type='text' name='degree-"+v+"' placeholder='Degree type'><br>");
	$("#education").append("<input width='100%' class='form-control' type='text' name='major-"+v+"' placeholder='Major'><br>");
	$("#education").append("<input width='100%' class='form-control' type='text' name='ed-dates-"+v+"' placeholder='Dates'><br>");
	$("#education").append("<textarea width='100%' class='form-control' name='ed-description-"+v+"' placeholder='Description (optional)'></textarea><br>");
	$("#education").append("<hr>");

	education_num += 1;
}
function addProjectField(){
	var v = project_num.toString();
	$("#projects").append("<input width='100%' class='form-control' type='text' name='project-name-"+v+"' placeholder='Name'><br>");
	$("#projects").append("<input width='100%' class='form-control' type='text' name='project-url-"+v+"' placeholder='URL to project (optional)'><br>");
	$("#projects").append("<input width='100%' class='form-control' type='text' name='project-dates-"+v+"' placeholder='Dates of creation'><br>");
	$("#projects").append("<textarea width='100%' class='form-control' name='project-description-"+v+"' placeholder='Description (optional)'></textarea><br>");
	$("#projects").append("<hr>");
	project_num += 1;
}
function getNum(obj){
	for(var key in obj){
		arr = key.split("-")
		return arr[arr.length - 1];
	}
}
function getExperience(){
	$.ajax({
	    url: "experience",
	    cache: false,
	    type: "GET",
	    dataType: 'json',
	    success: function(response) {;
			var max_experience = 0;
			for(var i = 0; i < response.length;i++){
				var obj = response[i];
		    	console.log(obj);
		    	var num = getNum(obj);
		    	max_experience = Math.max(max_experience, parseInt(num, 10));
		    	addExperienceField(num)
				for (var key in obj) {
			    	if (obj.hasOwnProperty(key)) {
				    	$( "input[name='"+key+"']" ).val(obj[key]);
				    	$( "textarea[name='"+key+"']" ).val(obj[key]);
				    }
				}
			}
			experience_num = max_experience + 1;
			addExperienceField((max_experience + 1).toString());
	    },
	    error: function(xhr) {

	    }
	});
}



$( document ).ready(function() {
	
	$("#experienceForm").submit(function(e) {
		e.preventDefault();
	    var form = $(this);
	    var url = form.attr('action');

		console.log(form.serialize());
	    $.ajax({
	           type: "post",
	           url: url,

	           data: form.serialize(),
	           success: function(data)
	           {
	        	   $("#experience").empty();

	        	   getExperience();
	           }
	    });
	});
	getExperience();
	//addExperienceField();
    //addEducationField();
    //addProjectField();
    
    
	$("#experience").on("click","#deleteExperience", function(e){
		num = $(this).attr("data-num");
		deleteButton = $(this);
		$.ajax({
	        type: "post",
	        url: "experience",
	        data: {
	        	"method": "delete",
	        	"num": num
	        }, // serializes the form's elements.
	        success: function(data)
	        {
	        	$("#experienceField-"+num).remove();
	        }
	   });
	});
});



</script>
</head>
<body>  
<header id="header" class="header header-hide">
    <div class="container">

      <div id="logo" class="pull-left">
        <!-- Uncomment below if you prefer to use an image logo -->
        <img src="img/logo.png" width="180" height="60" alt="">
        
      </div>

      
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
	String firstname = request.getAttribute("firstname").toString();
	//String firstname =  request.getParameter("firstname");
	String lastname = request.getAttribute("secondname").toString();
	String userName = request.getAttribute("userName").toString();
	String addr = request.getAttribute("addr").toString();
	String age = request.getAttribute("age").toString();
	
%>
<br>
<br>

<section id="hero" class="wow fadeIn">
<div class="hero-container">
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

</ul>
 
<form action="experience" id="experienceForm" method="post">
<div id="education">
</div>
<!-- <input class="btn btn-info"  type="button" value="Add education" onclick="addEducationField()"> -->
<br>
<div id = "experience">
</div>
<input class="btn btn-info"  type="button" value="Add experience" onclick="addExperienceField()">
<br>
<input class="btn btn-info"  type="submit" value="Save experience">
<div id="projects">
</div>
<br>
<!-- <input class="btn btn-info"  type="button" value="Add project" onclick="addProjectField()">  -->
<br>
</form>
<form action="latexresume" method="post">
<input class="btn btn-success" type="submit" value="Convert to PDF">
</form>
<!-- <button class="btn" type="submit" value="logout">Logout</button> -->

</div>
</section>
</body>
</html>