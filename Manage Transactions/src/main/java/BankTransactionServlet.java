import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;

@WebServlet("/transfer")
public class BankTransactionServlet extends HttpServlet {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3305/bankdb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 1️⃣ Get form data
        int fromAcc = Integer.parseInt(request.getParameter("fromAcc"));
        int toAcc = Integer.parseInt(request.getParameter("toAcc"));
        double amount = Double.parseDouble(request.getParameter("amount"));

        Connection con = null;

        try {
            // 2️⃣ Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 3️⃣ Connect to database
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            con.setAutoCommit(false); // start transaction

            // 4️⃣ Check sender balance
            String checkSQL = "SELECT balance FROM accounts WHERE acc_no=?";
            PreparedStatement psCheck = con.prepareStatement(checkSQL);
            psCheck.setInt(1, fromAcc);
            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {
                out.println("<h3>❌ From account not found.</h3>");
                return;
            }

            double fromBalance = rs.getDouble("balance");
            if (fromBalance < amount) {
                out.println("<h3>❌ Insufficient balance!</h3>");
                return;
            }

            // 5️⃣ Perform withdrawal and deposit
            String withdrawSQL = "UPDATE accounts SET balance = balance - ? WHERE acc_no=?";
            String depositSQL = "UPDATE accounts SET balance = balance + ? WHERE acc_no=?";

            PreparedStatement psWithdraw = con.prepareStatement(withdrawSQL);
            PreparedStatement psDeposit = con.prepareStatement(depositSQL);

            psWithdraw.setDouble(1, amount);
            psWithdraw.setInt(2, fromAcc);
            psWithdraw.executeUpdate();

            psDeposit.setDouble(1, amount);
            psDeposit.setInt(2, toAcc);
            psDeposit.executeUpdate();

            // 6️⃣ Commit transaction
            con.commit();

            // 7️⃣ Show success message
            out.println("<h2>✅ Transaction Successful!</h2>");
            out.println("<p>Amount " + amount + " transferred from account " + fromAcc +
                        " to account " + toAcc + ".</p>");

            // 8️⃣ Display updated balances
            out.println("<h3>Updated Account Balances:</h3>");
            Statement stmt = con.createStatement();
            ResultSet rsAll = stmt.executeQuery("SELECT * FROM accounts");

            out.println("<table border='1' cellpadding='5' style='margin:auto;'>");
            out.println("<tr><th>Account No</th><th>Holder Name</th><th>Balance</th></tr>");
            while (rsAll.next()) {
                out.println("<tr><td>" + rsAll.getInt("acc_no") + "</td>" +
                            "<td>" + rsAll.getString("holder_name") + "</td>" +
                            "<td>" + rsAll.getDouble("balance") + "</td></tr>");
            }
            out.println("</table>");

        } catch (ClassNotFoundException e) {
            out.println("<h3>❌ MySQL Driver not found!</h3>");
            e.printStackTrace();
        } catch (SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            out.println("<h3>❌ Transaction Failed: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET to form page
        response.sendRedirect("index.html");
    }
}
