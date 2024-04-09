package team12project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



class Main(){
    public static void main(String[] args){
        @WebServlet("/contact_form")
public class ContactFormServlet extends HttpServlet implements DatabaseOperations {
    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/contacts_db";
    private static final String JDBC_USERNAME = "your_username";
    private static final String JDBC_PASSWORD = "your_password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");

        // Save the form data to the database
        saveFormDataToDatabase(name, email, phone, message);

        // Redirect to a success page
        response.sendRedirect("contactus.html");
    }

    // Method to save form data to the database
    @Override
    public void saveFormDataToDatabase(String name, String email, String phone, String message) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
                // Prepare SQL statement
                String sql = "INSERT INTO contacts (name, email, phone, message) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Set parameters
                    statement.setString(1, name);
                    statement.setString(2, email);
                    statement.setString(3, phone);
                    statement.setString(4, message);

                    // Execute the insert statement
                    statement.executeUpdate();
                }
            }
        } catch (ClassNotFoundException e) {
            // Handle ClassNotFoundException
            System.err.println("Error loading JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            // Handle SQLException
            System.err.println("Error executing SQL statement: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            System.err.println("Error: " + e.getMessage());
        }
    }
}

interface DatabaseOperations {
    void saveFormDataToDatabase(String name, String email, String phone, String message);
}
    }
}