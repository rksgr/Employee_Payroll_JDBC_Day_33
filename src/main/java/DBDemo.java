import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.util.Enumeration;

public class DBDemo {
    public static void main(String[] args) {
        System.out.println("Today we shall solve use cases related to Employee Payroll using JDBC.");





   /*
        Use Case 1: Create a payroll service database and have java program connect to database
         */
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=true";
        String userName = "root";
        String password = "priya@1987";
        Connection connection;
        try{
            // To check that driver is loaded
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded ");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            // To check that connection to database is made
            System.out.println("Connecting to database ");
            connection = DriverManager.getConnection(jdbcURL,userName,password);
            System.out.println("Connection is Successful "+ connection);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        listDrivers();
    }

    // To get the list of all the drivers installed
    private static void listDrivers(){
        System.out.println("The drivers are: ");
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while(driverList.hasMoreElements()){
            Driver driverClass = (Driver) driverList.nextElement();
            System.out.println(" "+ driverClass.getClass().getName());
        }
    }
}
