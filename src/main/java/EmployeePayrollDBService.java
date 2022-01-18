import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EmployeePayrollDBService {

    private PreparedStatement employeePayrollDataStatement;
    private static EmployeePayrollDBService employeePayrollDBService;

    private EmployeePayrollDBService(){

    }

    public static EmployeePayrollDBService getInstance(){
        if (employeePayrollDBService == null){
            employeePayrollDBService = new EmployeePayrollDBService();
            return employeePayrollDBService;
        }
        return employeePayrollDBService;
    }

    private void prepareStatementForEmployeeData() {
        try{
            Connection connection = this.getConnection();
            String sql = "SELECT * FROM employee_payroll WHERE name =?";
            employeePayrollDataStatement = connection.prepareStatement(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<EmployeePayrollData> getEmployeePayrollData(String name){
        List<EmployeePayrollData> employeePayrollList = null;
        if (this.employeePayrollDataStatement == null){
            this.prepareStatementForEmployeeData();
        }
        try{
            employeePayrollDataStatement.setString(1,name);
            ResultSet resultSet = employeePayrollDataStatement.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return employeePayrollList;
    }
    private List<EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet){
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int emp_id = resultSet.getInt("emp_id");
                String name = resultSet.getString("name");
                double basic_pay = resultSet.getDouble("basic_pay");
                LocalDate start_date = resultSet.getDate("start_date").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(emp_id, name, basic_pay, start_date));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return employeePayrollList;
    }

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
            employeePayrollList = this.getEmployeePayrollData(resultSet);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }
    public int updateEmployeeData(String name, double basic_pay){
        return this.updateEmployeeDataUsingStatement(name,basic_pay);
    }
    private int updateEmployeeDataUsingStatement(String name,double basic_pay){
        String sql = String.format("update employee_payroll set basic_pay= %f where name= '%s;',basic_pay,name");
        try(Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
