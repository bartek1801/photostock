package pl.com.bottega.photostock.sales.misc;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class FireEmployee {

    private static final String GET_EMPLOYEE = "SELECT * FROM employees WHERE first_name = ? AND last_name = ?";
    private static final String UPDATE_DEPT_EMP ="UPDATE dept_emp SET to_date = ? WHERE emp_no = ? ";
    private static final String UPDATE_SALARIES ="UPDATE salaries SET to_date = ? WHERE emp_no = ? ";
    private static final String UPDATE_TITLES ="UPDATE titles SET to_date = ? WHERE emp_no = ? ";
    private static final String UPDATE_DEPT_MANAGER ="UPDATE dept_manager SET to_date = ? WHERE emp_no = ? ";

    private static final String GET_EMPLOYEE_BY_NUMBER = "SELECT * FROM employees WHERE emp_no = ?";

    private static final String UPDATE_TABLE ="UPDATE ? SET to_date = ? WHERE emp_no = ? ";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię pracownika");
        String firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko pracownika");
        String lastName = scanner.nextLine();

        Connection connection = getConnection();

        ResultSet resultSet = getEmployeesByName(connection, GET_EMPLOYEE, firstName, lastName);
        checkEmployee(resultSet);
        
        resultSet.beforeFirst();
        while (resultSet.next()) {
            System.out.printf("%d %s %s %s %s %s",
                    resultSet.getInt("emp_no"),
                    resultSet.getDate("birth_date"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("gender"),
                    resultSet.getDate("hire_date")
            );
            System.out.println();
        }

        System.out.println("Podaj nr pracownika do zwolnienia");
        Integer emp_number = scanner.nextInt();

        ResultSet rs = getEmployeeByNumber(connection, GET_EMPLOYEE_BY_NUMBER, emp_number);
        checkEmployee(rs);

        connection.setAutoCommit(false);

//        updateTable(connection, "dept_emp", emp_number, UPDATE_TABLE);
//        updateTable(connection, "salaries", emp_number, UPDATE_TABLE);
//        updateTable(connection, "titles", emp_number, UPDATE_TABLE);
//        updateTable(connection, "dept_manager", emp_number, UPDATE_TABLE);
        updateDeptEmp(connection, emp_number);
        updateSalaries(connection, emp_number);
        updateTitles(connection, emp_number);
        updateDeptManager(connection, emp_number);

        connection.commit();

    }

    private static ResultSet getEmployeeByNumber(Connection connection, String sql, Integer emp_number) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, emp_number);
        return stmt.executeQuery();
    }


    private static void updateDeptManager(Connection connection, Integer emp_number) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(UPDATE_DEPT_MANAGER);
        stmt.setDate(1, Date.valueOf(LocalDate.now()));
        stmt.setInt(2, emp_number);
        stmt.executeUpdate();
    }

    private static void updateTitles(Connection connection, Integer emp_number) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(UPDATE_TITLES);
        stmt.setDate(1, Date.valueOf(LocalDate.now()));
        stmt.setInt(2, emp_number);
        stmt.executeUpdate();
    }

    private static void updateSalaries(Connection connection, Integer emp_number) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(UPDATE_SALARIES);
        stmt.setDate(1, Date.valueOf(LocalDate.now()));
        stmt.setInt(2, emp_number);
        stmt.executeUpdate();
    }

    private static void updateDeptEmp(Connection connection, Integer emp_number) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(UPDATE_DEPT_EMP);
        stmt.setDate(1, Date.valueOf(LocalDate.now()));
        stmt.setInt(2, emp_number);
        stmt.executeUpdate();
    }

    private static void updateTable(Connection connection, String tableName, Integer emp_number, String sql) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, tableName);
        stmt.setDate(2, Date.valueOf(LocalDate.now()));
        stmt.setInt(3, emp_number);
        stmt.executeUpdate();
    }

    private static ResultSet getEmployeesByName (Connection connection, String sql, String firstName, String lastName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, firstName);
        statement.setString(2, lastName);
        return statement.executeQuery();
    }

    private static void checkEmployee(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            System.out.println("Nie ma takiego pracownika");
            return;
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/employees?user=root&password=Leszek1801&useSSL=false");
    }

}

//    Napisz program, który zwolni wybranego pracownika:
//        1. Po uruchomieniu program pyta którego pracwonika zwolnić. Użytkownik wpisuje imię i nazwisko pracownika.
//        2. Jeśli jest więcej niż jeden pracownik o takich danych, program wyświetla ich wszystkich wraz ze
//        wszystkimi danymi z tabelki employees i użytkonik musi podać nr pracownika do zwolnienia.
//        3. Jeśli nie ma takiego pracownika program wyświetla komunikat i kończy działanie.
//        4. Zwolnienie pracownika polega na ustawieniu to_date na bieżącą datę we wszsystkich bieżących
//        rekordach z tabelek dept_emp, titles, salaries i ew. dept_manager (pracownik może być menadżerem działu).