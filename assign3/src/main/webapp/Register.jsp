<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="rb" class="com.sunbeam.entities.RegisterBean" />
	<jsp:setProperty property="firstName" name="rb" param="fname" />
	<jsp:setProperty property="lastName" name="rb" param="lname" />
	<jsp:setProperty property="email" name="rb" param="email" />
	<jsp:setProperty property="password" name="rb" param="passwd" />
	<jsp:setProperty property="birth" name="rb" param="dob" />
	${rb.saveData()}
	<c:choose>
		<c:when test="${rb.cnt==1}">
			<p>Registration successful</p>
			<a href="index.jsp">Login here</a>
		</c:when>
		<c:otherwise>
			<p>failed</p>
		</c:otherwise>
	</c:choose>
</body>

</html>