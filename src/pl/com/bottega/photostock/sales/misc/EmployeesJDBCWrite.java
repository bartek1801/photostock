package pl.com.bottega.photostock.sales.misc;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class EmployeesJDBCWrite {

    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GENERATE_EMP_NO = "SELECT max(emp_no) + 1 FROM employees";
    private static final String INSERT_SALARY_SQL = "INSERT INTO salaries VALUES (?, ?, ?, ?)";

    private static final String INSTERT_TITLE = "INSERT INTO titles VALUES (?, ?, ?, ?)";
    private static final String INSERT_DEPT_EMP = "INSERT INTO dept_emp VALUES (?, ?, ?, ?)" ;
    private static final String GET_DEP_NO = "SELECT dept_no FROM departments WHERE dept_name = ? " ;
    private static final String INSERT_DEPARTMENT = "INSERT INTO departments VALUES (?, ?)";
    private static final String GENERATE_DEPT_NUMBER = "SELECT concat('d', LPAD(substr(dept_no, 2) + 1, 3, '0'))  " +
                                                        "FROM departments ORDER BY dept_no DESC LIMIT 1";

    public static final Date MAX_DATE = Date.valueOf("9999-01-01");

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String firstName = getFirstName(scanner);
        String lastName = getLastName(scanner);
        LocalDate hireDate =  LocalDate.now();
        String gender = getGender(scanner);
        LocalDate birthDate = getBirthDate(scanner);
        Integer salary = getSalary(scanner);
        scanner.nextLine();
        String departmentName = getDepartmentName(scanner);
        //String title = getTitle(scanner);

        Connection connection = getConnection();
        connection.setAutoCommit(false);

        Long empNo = insertEmployee(connection, firstName, lastName, hireDate, gender, birthDate);
        insertSalary(connection, empNo, salary);
        insertDepatment(connection,empNo, departmentName);
        //insertTitle(connection, empNo, title);

        connection.commit();

    }

    private static void insertTitle(Connection connection, Long empNo, String title) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(INSTERT_TITLE);

    }

    private static void insertDepatment(Connection connection, Long empNo, String departmentName) throws SQLException {
        String deptNo = getOrCreateDepartment(connection, departmentName);
        PreparedStatement ps = connection.prepareStatement(INSERT_DEPT_EMP);
        ps.setLong(1, empNo);
        ps.setString(2, deptNo);
        ps.setDate(3, Date.valueOf(LocalDate.now()));
        ps.setDate(4, MAX_DATE);
        ps.executeUpdate();
    }

    private static String getOrCreateDepartment(Connection connection, String departmentName) throws SQLException {
        String id = null;
        PreparedStatement stmt = connection.prepareStatement(GET_DEP_NO);
        stmt.setString(1, departmentName);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            id = rs.getString(1);
        }
        else {
            ResultSet newDeptNo = connection.createStatement().executeQuery(GENERATE_DEPT_NUMBER);
            newDeptNo.next();
            id = newDeptNo.getString(1);
            PreparedStatement ps = connection.prepareStatement(INSERT_DEPARTMENT);
            ps.setString(1, id);
            ps.setString(2,departmentName);
            ps.executeUpdate();
        }
        return id;
    }

    private static void insertSalary(Connection connection, Long empNo, Integer salary) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(INSERT_SALARY_SQL);
        stmt.setLong(1, empNo);
        stmt.setInt(2, salary);
        stmt.setDate(3, Date.valueOf(LocalDate.now()));
        stmt.setDate(4, MAX_DATE);
        stmt.executeUpdate();
    }

    private static Long insertEmployee(Connection connection, String firstName, String lastName, LocalDate hireDate, String gender, LocalDate birthDate) throws SQLException {
        ResultSet rs = connection.createStatement().executeQuery(GENERATE_EMP_NO);
        rs.next();
        Long id = rs.getLong(1);
        PreparedStatement stmt = connection.prepareStatement(INSERT_EMPLOYEE_SQL);
        stmt.setLong(1, id);
        stmt.setDate(2, Date.valueOf(birthDate));
        stmt.setString(3, firstName);
        stmt.setString(4, lastName);
        stmt.setString(5, gender);
        stmt.setDate(6, Date.valueOf(hireDate));
        stmt.executeUpdate();
        return id;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/employees?" +
                        "user=root&password=Leszek1801&useSSL=false");
    }

    private static String getTitle(Scanner scanner) {
        System.out.println("Podaj stanowisko: ");
        return scanner.nextLine();
    }

    private static String getDepartmentName(Scanner scanner) {
        System.out.println("Podaj nazwę działu: ");
        return scanner.nextLine();
    }

    private static Integer getSalary(Scanner scanner) {
        System.out.println("Wpisz zarobki;");
        return scanner.nextInt();
    }

    private static LocalDate getBirthDate(Scanner scanner) {
        System.out.println("Podaj datę urodzenia:");
        return LocalDate.parse(scanner.nextLine());
    }

    private static String getGender(Scanner scanner) {
        System.out.println("Podaj płeć");
        return scanner.nextLine();
    }

    private static String getLastName(Scanner sc) {
        System.out.println("Podaj nazwisko: ");
        return sc.nextLine();
    }

    private static String getFirstName(Scanner scanner) {
        System.out.println("Podaj imię:");
        return scanner.nextLine();
    }

}
