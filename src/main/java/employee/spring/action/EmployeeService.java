package employee.spring.action;

import employee.exception.TaboSymbolsInEmployeeException;
import employee.workWithEmployees.Employee;
import employee.workWithEmployees.EmployeeBook;
import employee.exception.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final static char[] tabooSymbols = "1234567890!@#$%^&*()_+;%:?=/.,".toCharArray();
    private List<Employee> employees = new ArrayList<>();
    private static EmployeeBook employeeBook = new EmployeeBook();
    static int maxEmployeesCount = 5;
    static int employeesCounter = 0;

    public EmployeeBook getEmployeeBook() {
        return employeeBook;
    }

    public String addEmployee(String firstName, String lastName,
                              int salary, int department)
    {
        if (employeesCounter >= maxEmployeesCount) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        }
        if (!StringUtils.containsNone(firstName + lastName, tabooSymbols)) {
            throw new TaboSymbolsInEmployeeException("ошибка ввода");
        }
        Employee employee = new Employee(StringUtils.capitalize(firstName),
                        StringUtils.capitalize(lastName), salary, department);

        employeeBook.put(employee.getFirstName() + " " + employee.getLastName(), employee);
        employeesCounter++;
        return "Сотрудник добавлен";
    }
    public String deleteEmployee(String firstName, String lastName) {
        if (!StringUtils.containsNone(firstName + lastName, tabooSymbols)) {
            throw new RuntimeException("ошибка ввода");
        }
        String name = StringUtils.capitalize(firstName) + " " + StringUtils.capitalize(lastName);
        if (employeeBook.getAllEmployeeName().contains(name)) {
            employeeBook.getEmployeesMap().remove(name);
            return "Сотрудник удален";
        } else {
            return "такого сотрудника итак нет";
        }

    }
    public String findEmployee(String firstName, String lastName) {
        if (!StringUtils.containsNone(firstName + lastName, tabooSymbols)) {
            throw new RuntimeException("ошибка ввода");
        }
        String name = StringUtils.capitalize(firstName) + " " + StringUtils.capitalize(lastName);
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


    public Employee minSalaryInDepartment(int department) {
        return employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow();
    }

    public Employee maxSalaryInDepartment(int department) {
        return employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow();
    }

    public int  sumDepartmentSalarys(int department) {
        return employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(employee -> employee.getSalary())
                .collect(Collectors.toList()).stream()
                .mapToInt(Integer::intValue).sum();
        }

    public int middleSalaryInDepartment(int department) {
        int count = employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .toList().size();

        return (int) ((float) sumDepartmentSalarys(department) / (float) count);
    }

    public String multiplyPercentDepartmentsSalarys
            (int department, float percent) {
        employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .toList().
                forEach(employee -> employee.setSalary((int)((float) employee.getSalary()* percent)));

        return "Зарплата успешно изменена";
    }

    public String allEmployees(int department) {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger count = new AtomicInteger(1);
        employeeBook.getEmployeesMap().values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .toList()
                .forEach(employee -> {
                    stringBuilder.append("(").append(count.getAndIncrement()).append(") ").append(employee.toString()).append(". ");
                });
        if (!stringBuilder.isEmpty()) {
            return stringBuilder.toString();
        }
        return "в отделе пусто";
    }

    public String allEmployees() {
        StringBuilder stringBuilder = new StringBuilder();
        employeeBook.getAllDepartments().
                forEach(department -> stringBuilder.append("departmen Number ").append(department).append(" ").append(allEmployees(department)));
        return stringBuilder.toString();
    }
}