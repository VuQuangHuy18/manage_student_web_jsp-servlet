<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Web Student Tracker</title>
</head>
<body>


	<h2>FooBar University</h2>




	<div id="content">


		<select name="loai">
			<option></option>
			<option>Anh lông ngắn</option>
			<option>Anh lông dài</option>
			<option>Anh lông đen</option>

		</select> <input type="button" value="Search"> <br> <br>


		<!-- put new button: Add Student -->

		<input type="button" value="Add Student"
			onclick="window.location.href='\jsp\\add-student-form.jsp'; return false;"
			class="add-student-button" />


		<table style="width: 60%; border: solid 1px;">

			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Action</th>
			</tr>

			<c:forEach var="temStu" items="${STUDENT_LIST}">
				<tr>
					<td>${temStu.id}</td>

				</tr>
			</c:forEach>

			<c:forEach var="tempStudent" items="${STUDENT_LIST}">

				<!-- set up a link for each student -->
				<c:url var="tempLink" value="StudentControllerServlet">
					<c:param name="command" value="LOAD" />
					<c:param name="studentId" value="${tempStudent.id}" />
				</c:url>

				<!--  set up a link to delete a student -->
				<c:url var="deleteLink" value="StudentControllerServlet">
					<c:param name="command" value="DELETE" />
					<c:param name="studentId" value="${tempStudent.id}" />
				</c:url>
				<tr>
					<td>${tempStudent.id}</td>
					<td>${tempStudent.firstName}</td>
					<td>${tempStudent.lastName}</td>
					<td>${tempStudent.email}</td>
					<td><a href="${tempLink}">Update</a> | 
					<a href="${deleteLink}"
						onclick="if (!(confirm('delete ?'))) return false">
							Delete</a></td>
				</tr>

			</c:forEach>

		</table>

	</div>


</body>
</html>