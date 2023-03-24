<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>ViewStudent JSP</h1>

<c:forEach var="temStudent" items="${Student_List}">
    ${temStudent}
   <a href="jsp/list-student.jsp"> go to the moon</a>

</c:forEach>
<h1>hihi</h1>
<form action=""></form>



</body>
</html>