package employee.workWithEmployees;

import employee.exception.EmployeeAlreadyAddedException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EmployeeBook {
    private Map<String, Employee> employeesMap = new HashMap<>();
    private Set<String> allEmployeeName = new HashSet<>();

    private Set<Integer> allDepartments = new HashSet<>();

    public Set<String> getAllEmployeeName() {
        return allEmployeeName;
    }

    public Set<Integer> getAllDepartments() {
        return allDepartments;
    }

    public void put(String str, Employee employee) {
        if (!employeesMap.containsKey(str)) {
            this.employeesMap.put(str, employee);
            this.allEmployeeName.add(str);
            allDepartments.add(employee.getDepartment());
        } else {
            throw new EmployeeAlreadyAddedException("такой сотрудник уже есть!");
        }
    }

    public Map<String, Employee> getEmployeesMap() {
        return employeesMap;
    }
}
