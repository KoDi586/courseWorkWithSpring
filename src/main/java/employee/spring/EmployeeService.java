package employee.spring;

import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();
    static int maxEmployeesCount = 10;
    static int employeesCounter = 0;

    public String addEmployee(String firstName, String lastName)
    {

        if (employeesCounter == maxEmployeesCount) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains((Employee) employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }
        this.employees.add(employee);
        employeesCounter++;
        return "Сотрудник создан";
    }
    public String deleteEmployee( String firstName, String lastName)
    {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains((Employee) employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден!");
        }
        this.employees.remove((Employee) employee);
        employeesCounter--;
        return "Сотрудник удален";
    }
    public String findEmployee( String firstName, String lastName)
    {
        Employee employee = new Employee(firstName, lastName);
        int id =0;
        if (!employees.contains((Employee) employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден!");
        }
        return "Данный сотрудник под индексом: " + (id + 1) + "\n(нумерация с 1)";
    }

    public String toString() {
        return employees.toString() +
                '.';
    }
}
