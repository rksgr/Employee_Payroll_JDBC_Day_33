import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EmployeePayrollDBService {

    private Connection getConnection() throws SQLException{
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String userName = "root";
        String password = "priya@1987";
        Connection connection;
        System.out.println("Connecting to database ");
        connection = DriverManager.getConnection(jdbcURL,userName,password);
        System.out.println("Connection is Successful "+ connection);
        return connection;
    }
    /*
    Use Case 2: Retrieve the employee payroll from the database
     */
    public List<EmployeePayrollData> readData(){
        String sql = "SELECT * FROM employee_payroll";
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try(Connection connection = this.getConnection()) {
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int emp_id = resultSet .getInt("emp_id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("basic_pay");
                LocalDate start_date = resultSet.getDate("start_date").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(emp_id,name,salary,start_date));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }


}
