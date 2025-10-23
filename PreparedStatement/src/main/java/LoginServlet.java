

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

// ✅ No web.xml required — annotation mapping
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple authentication logic
        if ("admin".equals(username) && "1234".equals(password)) {
            out.println("<h2 style='color:green;'>Login Successful!</h2>");
            out.println("<p>Welcome, " + username + " 🎉</p>");
        } else {
            out.println("<h2 style='color:red;'>Invalid username or password!</h2>");
            out.println("<a href='index.html'>Try again</a>");
        }
    }

    // Optional: handle GET request (prevents 405 error)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3 style='color:blue;'>Please use the login form to sign in.</h3>");
        out.println("<a href='index.html'>Go to Login Page</a>");
    }
}
