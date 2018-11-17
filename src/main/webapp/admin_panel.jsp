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

            function updateList() {
                $("#my-list").html("");
                items.forEach(function(e) {
                    $("#my-list").append("<li>" + e + "</li>");
                });
            }

            function getListItems() {
                $.ajax({
                    url: "admin",
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
            });
        </script>
        <style>
            body {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h1>Welcome to Admin's panel!</h1>
        <h3>Current logs: </h3>
        <br>
        <ul id="my-list">
        </ul>
        <form action="AdminController" method="post">
            <h3>API logs</h3>
            <input type="submit" class="btn" value="Apply" />
        </form>
    </body>
    </html>