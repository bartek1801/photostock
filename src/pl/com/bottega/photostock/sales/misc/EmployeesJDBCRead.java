package pl.com.bottega.photostock.sales.misc;

import java.sql.*;
import java.util.Scanner;

public class EmployeesJDBCRead {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię: ");
        String firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko: ");
        String lastName  = scanner.nextLine();

        String sql = "SELECT * FROM employees e " +
                "LEFT JOIN salaries s ON s.emp_no = e.emp_no AND s.to_date > NOW()" +
                "WHERE first_name =? AND last_name = ? ";

        Connection conn =
                DriverManager.getConnection("jdbc:mysql://localhost/employees?user=root&password=Leszek1801&useSSL=false");

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            System.out.printf("%d %s %s %s %s %s %s",
                    rs.getLong("emp_no"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("birth_date"),
                    rs.getString("gender"),
                    rs.getString("hire_date"),
                    rs.getString("salary")
                    );
            System.out.println();

            // Georgi Facello
        }



    }

}
