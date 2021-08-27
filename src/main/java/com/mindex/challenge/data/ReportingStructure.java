package com.mindex.challenge.data;

import com.mindex.challenge.service.EmployeeService;

import java.util.List;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure() {
    }

    /**
     * Creates a ReportingStructure object for a given employee which also includes number of reports that said employee will receive.
     * @param employee The employee to base the reporting structure off of.
     * @param employeeService Service that holds all employee data. Needed in order to resolve employee ids to employee objects with reporting data.
     */
    public ReportingStructure(Employee employee, EmployeeService employeeService) {
        this.employee = employee;
        this.numberOfReports = getDirectReportsRecursively(employee.getDirectReports(), employeeService);
    }

    /**
     * Recursively counts how many reports will be generated from a list of employees
     * Due to recursion, this may not be the best solution for extremely large companies due to stack memory limits.
     * In the case of memory issues, a queue may work better where we would
     * effectively traverse down a branch entirely before checking next employee.
     * @param employeeList list of employees to check for reports
     * @param employeeService used to resolve employee ids from incomplete employee objects to get the full data on the employee.
     * @return total number of reports that will be generated from employeeList
     */
    private int getDirectReportsRecursively(List<Employee> employeeList, EmployeeService employeeService){
        int currentReportCount = 0;
        if(employeeList != null) {
            // Add current employees count
            currentReportCount += employeeList.size();

            // Loop over all employees reporting to above
            for(Employee emptyEmployee : employeeList) {
                // The direct reports holds an employee object with only id.
                // We need employeeService to get the complete employee data to get all reports.
                Employee fillEmployee = employeeService.read(emptyEmployee.getEmployeeId());

                // Check if the employee we have also has report reporting to them
                currentReportCount += getDirectReportsRecursively(fillEmployee.getDirectReports(), employeeService);
            }
        }
        return currentReportCount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }
}
