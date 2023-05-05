package com.mindex.challenge.data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReportingStructure {
    private Employee employee;
    private String employeeId;
    private int numberOfReports;
    private ArrayList<String> reportPeople;

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
