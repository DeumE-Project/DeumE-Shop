<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Go To Main Page</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<%--
    이 페이지는 /main 으로 redirect 하기 위한 페이지입니다.
--%>
<%
    response.sendRedirect("/main");
%>
</body>
</html>