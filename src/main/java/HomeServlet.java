
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 *
 * @author masterplan + rwezaula
 */
public class HomeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Step 1: Loading driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establishing connection
            String url = "jdbc:mysql://localhost:3306";
            String username = "root";
            String password = "master123";
            connection = DriverManager.getConnection(url, username, password);
            out.println("Connected to MySQL server successfully!<br>");

            // Step 3: Creating database if not exists
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS StudentData";
            preparedStatement = connection.prepareStatement(createDatabaseQuery);
            preparedStatement.executeUpdate();
            out.println("StudentData database created successfully!<br>");

            // Step 4: Switch to the StudentData database
            String useDatabaseQuery = "USE StudentData";
            preparedStatement = connection.prepareStatement(useDatabaseQuery);
            preparedStatement.executeUpdate();

            // Step 5: Creating a PreparedStatement
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Student (id INT PRIMARY KEY, name VARCHAR(50))";
            preparedStatement = connection.prepareStatement(createTableQuery);
            preparedStatement.executeUpdate();
            out.println("Student table created successfully!<br>");

            // Step 6: Execute PreparedStatement
            preparedStatement = connection.prepareStatement("INSERT INTO Student (id, name) VALUES (?, ?)");
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "John");
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                out.println("Data inserted successfully!<br>");
            } else {
                out.println("Failed to insert data!<br>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(out); // Print exception details to the browser
        } finally {
            // Step 7: Closing connection and resources
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(out); // Print exception details to the browser
            }
        }

        out.println("</body></html>");
        out.close();
        
        
        
        /*
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        */
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}