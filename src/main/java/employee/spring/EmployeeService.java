package employee.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class EmployeeService {
    public List<Employee> employees = new ArrayList<>();
    static int maxEmployeesCount = 10;

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam String firstName,
                            @RequestParam String lastName)
    {
        Employee employee = new Employee(firstName, lastName);
        this.employees.add(employee);
        return "Сотрудник создан";
    }
    @GetMapping(path = "/delete")
    public String deleteEmployee(@RequestParam String firstName,
                               @RequestParam String lastName)
    {
        Employee employee = new Employee(firstName, lastName);
        this.employees.remove((Employee) employee);
        return "Сотрудник удален";
    }
    @GetMapping(path = "/find")
    public String findEmployee(@RequestParam String firstName,
                               @RequestParam String lastName)
    {
        Employee employee = new Employee(firstName, lastName);
        int id = this.employees.indexOf((Employee) employee);
        return "Данный сотрудник под индексом: " + (id + 1) + "\n(нумерация с 1)";
    }

    @GetMapping(path = "/printEmployees")
    public String toString() {
        return employees.toString() +
                '.';
    }
}
