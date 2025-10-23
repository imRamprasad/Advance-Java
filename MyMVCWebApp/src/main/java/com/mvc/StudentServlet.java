package com.mvc;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Step 1: Get data from request
        String name = request.getParameter("studentName");

        // Step 2: Create Model object
        Student student = new Student(name);

        // Step 3: Add model data to request
        request.setAttribute("studentData", student);

        // Step 4: Forward to JSP (View)
        RequestDispatcher dispatcher = request.getRequestDispatcher("student.jsp");
        dispatcher.forward(request, response);
    }
}
