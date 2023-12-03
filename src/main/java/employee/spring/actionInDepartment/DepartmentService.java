package employee.spring.actionInDepartment;

import employee.spring.action.EmployeeService;
import employee.workWithEmployees.Employee;
import employee.workWithEmployees.EmployeeBook;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public String allEmployees(Integer id) {
        return employeeService.allEmployees(id);
    }

    public int salarySum(Integer id) {
        EmployeeBook employeeBook = employeeService.getEmployeeBook();
        AtomicInteger counter = new AtomicInteger();
        employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == id)
                .forEach(employee -> counter.addAndGet(employee.getSalary()));

        return counter.get();
    }

    public int maxSalary(Integer id) {
        EmployeeBook employeeBook = employeeService.getEmployeeBook();
        return employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == id)
                .max(Comparator.comparingInt(Employee::getSalary)).orElseThrow().getSalary();
    }

    public int minSalary(Integer id) {
        EmployeeBook employeeBook = employeeService.getEmployeeBook();
        return employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == id)
                .min(Comparator.comparingInt(Employee::getSalary)).orElseThrow().getSalary();
    }

    public String allEmployees() {
        EmployeeBook employeeBook = employeeService.getEmployeeBook();
        StringBuilder stringBuilder = new StringBuilder();
        employeeBook.getAllDepartments().
                forEach(department -> stringBuilder.append("departmen Number ")
                        .append(department).append(" ")
                        .append(employeeService.allEmployees(department)));
        return stringBuilder.toString();
    }
}
