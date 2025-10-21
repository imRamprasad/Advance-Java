<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Student MVC App</title></head>
<body style="font-family: Arial; text-align:center; margin-top:100px;">
    <h2>Enter Your Name</h2>
    <form action="student" method="post">
        <input type="text" name="studentName" placeholder="Enter your name" required />
        <br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
