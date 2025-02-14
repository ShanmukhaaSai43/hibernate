package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {
	@Id
    private int empId;
    private String empName;
    public Employee() {

    }
    public Employee(int empId, String empName)
    {
        super();
        this.empId = empId;
        this.empName = empName;
    }
    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }
    public String getEmpName() { return empName; }
    public void setEmpName(String empName)
    {
        this.empName = empName;
    }
}