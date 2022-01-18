import java.time.LocalDate;

public class EmployeePayrollData {
    public int emp_id;
    public String name;
    public double salary;
    public LocalDate startDate;

    public EmployeePayrollData(int emp_id, String name, double salary){
        this.emp_id = emp_id;
        this.name = name;
        this.salary = salary;
    }

    public EmployeePayrollData(int emp_id, String name, double salary, LocalDate startDate){
        this(emp_id,name,salary);
        this.startDate = startDate;
    }
    public String toString(){
        return "emp_id="+ emp_id + ",+ name ='"+name+ '\'' +", salary= " + salary ;
    }
    public boolean equals(Object o){
        if (this ==o)  return true;
        if (o== null || getClass() != o.getClass()) return false;
        EmployeePayrollData that = (EmployeePayrollData) o;
        return emp_id == that.emp_id  &&
                    Double.compare(that.salary,salary) == 0 &&
                    name.equals(that.name);
    }
}

