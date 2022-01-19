//import EmployeePayrollService.IOService.DB_IO;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmployeePayrollServiceTest {
    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount(){
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        Assertions.assertEquals(10,employeePayrollData.size());
    }

    /*
    Use case 3: Update base pay for Terissa and sync it with database
     */
    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB(){
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData =
                employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);

        employeePayrollService.updateEmployeeSalary("Terisa",3700000.0);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
        Assert.assertTrue(result);
    }

    /*
    Use case 4: Update base pay for Terisa using Prepared statement and sync it with database
     */
    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB_UsePrepared(){
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData =
                employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        employeePayrollService.updateEmployeeSalaryPreparedStmt("Terisa",3700000.0);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDBPrepared("Terisa");
        Assert.assertTrue(result);
    }
}
