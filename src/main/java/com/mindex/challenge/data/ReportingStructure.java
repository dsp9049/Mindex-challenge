package com.mindex.challenge.data;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.dao.ReportingStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReportingStructure {
    private Employee employee;
    private String employeeId;
    private int numberOfReports;
    private ArrayList<String> reportPeople;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ReportingStructure(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.employee = employee;
        this.reportPeople = new ArrayList<>();
        this.numberOfReports = this.getNumberOfReports();
    }
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * gets the number of reports of the given employee through all direct reports
     * note: Subsequent employees have null information including their direct reports
     * as of the time of writing this it is inconclusive where / why this is happening.
     * @return number of reports
     */
    public int getNumberOfReports() {
        ArrayList<Employee> visited = new ArrayList<>();
        ArrayList<Employee> toVisit = new ArrayList<>();
        toVisit.add(this.employee);
        while(toVisit.size()>0) { //Iterate through direct reports to get total
            Employee e = toVisit.remove(0);
            if (!visited.contains(e)) {
                this.reportPeople.add(e.getFirstName()+" "+e.getLastName());
                visited.add(e);
                if (e.getDirectReports() != null) {
                    toVisit.addAll(e.getDirectReports());
                }
            }
        }
        return visited.size()-1;// subtract self from total
    }
}
