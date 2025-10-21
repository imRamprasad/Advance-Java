
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


@WebServlet("/aaa")
public class HelloServlet  extends HttpServlet {
	
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        out.println("<html><body style='text-align:center;'>");
	        out.println("<a href='/aaa'></a>");
	        out.println("<h2>Hello from Servlet!</h2>");
	        out.println("<p>This content is generated dynamically using Java.</p>");
	        out.println("</body></html>");
	       
	    }
	}