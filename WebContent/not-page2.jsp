<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/styles.css" rel="stylesheet">
    <title>Missing list</title>
  </head>
  <body>
  <br>
    <form action="ControlServlet" method="post">
 	   <input type='submit' class="mbtn" name="button" value='Menu'>
 	   <input type='submit' class="mbtn" name="button" value='Contacts'>
  	   <input type='submit' class="mbtn" name="button" value='Download'>
    </form>
    <br><br>
    <h2><font>Сontact list is not created!</font></h2>
  </body>
</html>
