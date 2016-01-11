<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javax.servlet.*" import="java.util.*" 
    import="model.Contact" import="java.io.PrintWriter"
    import="javax.servlet.http.*" import="model.TableManager" 
    import="javax.servlet.*" import="javax.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Mapping Contacts</title>
	<link href="css/styles.css" rel="stylesheet">
  </head>
  <body>
  <br>
    <!-- Menu -->
	<form action="ControlServlet" method="post">
	  <input type='submit' class="mbtn" name="button" value='Menu'>
	  <input type='submit' class="mbtn" name="button" value='Contacts'>
	  <input type='submit' class="mbtn" name="button" value='Download'>
	</form>
    <br><br>   
    <!-- Table -->
    <table border="1">
    	<form action="SorterServlet" method="post">
    	<tr>
		  <td class="num"></td>
		  <td><input type='submit' class="nbtn" name="button" value='Name'></td>
		  <td><input type='submit' class="nbtn" name="button" value='Surname'></td>
		  <td><input type='submit' class="nbtn" name="button" value='Login'></td>
		  <td><input type='submit' class="nbtn" name="button" value='Email'></td>
		  <td><input type='submit' class="nbtn" name="button" value='Phone_number'></td>
	    </tr>
    	</form>
 		<c:forEach items="${tableManager.pageContacts}" var="contact">
	    <tr>
	      <td class="num"><c:out value="${contact.id}"></c:out></td>	
          <td><c:out value="${contact.name}"></c:out></td>
          <td><c:out value="${contact.surName}"></c:out></td>
          <td><c:out value="${contact.login}"></c:out></td>
          <td><c:out value="${contact.mail}"></c:out></td>
          <td><c:out value="${contact.phoneNumber}"></c:out></td>
        </tr>
  	    </c:forEach>
    </table>  
    <br><br>
    <!-- Page number -->
    <form action="PagesServlet" method="post">
  	  <input type='submit' name="button" class="nbtn" value='back'>
  	  <input type='text' class="tx" name="count_page" value='${tableManager.currentPage}'>
  	  <input type='submit' name="button" class="nbtn" value='forward'>
    </form>    	  
  </body>
</html>


