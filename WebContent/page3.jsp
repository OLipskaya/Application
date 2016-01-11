<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/styles.css" rel="stylesheet">
    <title>Download</title>
  </head>
  <body>
  <br>
    <form action="ControlServlet" method="post">
 	   <input type='submit' class="mbtn" name="button" value='Menu'>
 	   <input type='submit' class="mbtn" name="button" value='Contacts'>
  	   <input type='submit' class="mbtn" name="button" value='Download'>
    </form>
    <br><br>
    <form enctype="multipart/form-data" action="FilesServlet" method="post">
	   <input type="file" name="csv">
	   <br><br>
	   <input type='submit' name="button" class="nbtn" value='Load'>	
    </form>
  </body>
</html>