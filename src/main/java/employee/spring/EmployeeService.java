package employee.spring;

import exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();
    static int maxEmployeesCount = 10;
    static int employeesCounter = 0;

    public String addEmployee(EmployeeBook employeeBook, String firstName, String lastName, int salary, int department) {
        if (employeesCounter >= maxEmployeesCount) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        }
        Employee employee = new Employee(firstName, lastName, salary, department);
        employeeBook.put(employee.getFirstName() + " " + employee.getLastName(), employee);
        employeesCounter++;
        return "Сотрудник добавлен";
    }
    public String deleteEmployee(EmployeeBook employeeBook, String firstName, String lastName) {
        String name = firstName + " " + lastName;
        if (employeeBook.getAllEmployeeName().contains(name)) {
            employeeBook.getEmployeesMap().remove(name);
            return "Сотрудник удален";
        } else {
            return "такого сотрудника итак нет";
        }

    }
    public String findEmployee(EmployeeBook employeeBook, String firstName, String lastName) {
        String name = firstName + " " + lastName;
        if (employeeBook.getAllEmployeeName().contains(name)) {
            return employeeBook.getEmployeesMap().get(name).toString();
        } else {
            return "сотрудник не найден";
        }
    }

    public String toString() {
        return employees.toString() +
                '.';
    }


    public Employee minSalaryInDepartment(EmployeeBook employeeBook, int department) {
        return employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow();
    }

    public Employee maxSalaryInDepartment(EmployeeBook employeeBook, int department) {
        return employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow();
    }

    public int sumDepartmentSalarys(EmployeeBook employeeBook, int department) {
        return employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(employee -> employee.getSalary())
                .collect(Collectors.toList()).stream()
                .mapToInt(Integer::intValue).sum();
        }

    public int middleSalaryInDepartment(EmployeeBook employeeBook, int department) {
        int count = employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .toList().size();

        return (int) ((float) sumDepartmentSalarys(employeeBook, department) / (float) count);
    }

    public String multiplyPercentDepartmentsSalarys
            (EmployeeBook employeeBook, int department, float percent) {
        employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getSalary() == department)
                .toList().
                forEach(employee -> employee.setSalary((int)((float) employee.getSalary()* percent)));

        return "Зарплата успешно изменена";
    }

    public String allEmployees(EmployeeBook employeeBook, int department) {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger count = new AtomicInteger(1);
        employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .toList()
                .forEach(employee -> {
                    stringBuilder.append(count.getAndIncrement()).append(") ").append(employee.toString()).append(". ");
                });
        if (!stringBuilder.isEmpty()) {
            return stringBuilder.toString();
        }
        return "в отделе пусто";
    }

    public String allEmployees(EmployeeBook employeeBook) {
        StringBuilder stringBuilder = new StringBuilder();
        employeeBook.getAllDepartments().
                forEach(department -> stringBuilder.append("departmen Number ").append(department).append(allEmployees(employeeBook, department)));
        return stringBuilder.toString();
    }
}