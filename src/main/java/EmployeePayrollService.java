import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;

public class EmployeePayrollService {


    public enum IOService{
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }
    private List<EmployeePayrollData> employeePayrollList;
    private EmployeePayrollDBService employeePayrollDBService;

    public EmployeePayrollService(){

        // employeepayrolldbservice is a
        employeePayrollDBService = EmployeePayrollDBService.getInstance();
    }

    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList){
        this();
        this.employeePayrollList = employeePayrollList;
    }
    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService){
        if (ioService.equals(IOService.DB_IO)){
            this.employeePayrollList = employeePayrollDBService.readData();
        }
        return this.employeePayrollList;
    }


    public boolean checkEmployeePayrollInSyncWithDB(String name) {
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.getEmployeePayrollData(name);
        //System.out.println("returned by check "+ employeePayrollDataList.get(0).equals(getEmployeePayrollData(name)));
        return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
    }


    public void updateEmployeeSalary(String name, double basic_pay) {
        //String sql = String.format("update employee_payroll set basic_pay=%.2f where name= '%s';",basic_pay,name);
        int result = employeePayrollDBService.updateEmployeeData(name,basic_pay);
        if (result == 0) return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if (employeePayrollData != null) employeePayrollData.basic_pay = basic_pay;
    }

    private EmployeePayrollData getEmployeePayrollData(String name){
        return this.employeePayrollList.stream()
                .filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name))
                .findFirst()
                .orElse(null);
    }


}

