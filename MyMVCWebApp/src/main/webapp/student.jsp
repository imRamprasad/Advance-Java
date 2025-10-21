<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mvc.Student" %>
<html>
<head><title>Student Result</title></head>
<body style="font-family: Arial; text-align:center; margin-top:100px;">
    <%
        Student s = (Student) request.getAttribute("studentData");
    %>
    <h2><%= s.getMessage() %></h2>
    <a href="index.jsp">Go Back</a>
</body>
</html>
