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
	<jsp:useBean id="lgn" class="com.sunbeam.entities.LoginBean" />
	<jsp:setProperty property="email" name="lgn" param="uemail" />
	<jsp:setProperty property="password" name="lgn" param="passwd" />

	${lgn.Login()}
	<c:choose>
		<c:when test= "${ lgn.u!=null }">
		   <c:redirect url="candidate.jsp" />
		</c:when>
		<c:otherwise>
			<p>failed</p>
			<c:redirect url="index.jsp" />
		</c:otherwise>
	</c:choose>

</body>
</html>