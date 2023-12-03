package employee.spring.actionInDepartment;

import employee.spring.action.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    private final EmployeeService employeeService = new EmployeeService();
    private final DepartmentService departmentService = new DepartmentService(employeeService);

    @GetMapping("/{id}/check")
    public String check(@PathVariable int id) {
        return "ID: " + (id + 10);
    }

    @GetMapping("/{department}/employees")
    public String employees(@PathVariable Integer department) {
        return departmentService.allEmployees(department);
    }

    @GetMapping("/{id}/salary/sum")
    public int salarySum(@PathVariable Integer id) {
        return departmentService.salarySum(id);
    }

    @GetMapping("{id}/salary/max")
    public int maxSalary(@PathVariable Integer id) {
        return departmentService.maxSalary(id);
    }

    @GetMapping("{id}/salary/min")
    public int minSalary(@PathVariable Integer id) {
        return departmentService.minSalary(id);
    }

    @GetMapping("/employees")
    public String employees() {
        return departmentService.allEmployees();
    }
}
