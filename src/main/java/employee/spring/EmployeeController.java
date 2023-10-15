package employee.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeService();

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam String firstName,
                              @RequestParam String lastName)
    {
        return employeeService.addEmployee(firstName, lastName);
    }
    @GetMapping(path = "/remove")
    public String deleteEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName)
    {
        return employeeService.deleteEmployee(firstName, lastName);
    }

    @GetMapping(path = "/find")
    public String findEmployee(@RequestParam String firstName,
                               @RequestParam String lastName)
    {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping(path = "/printEmployees")
    public String toString() {
        return employeeService.toString();
    }
}
