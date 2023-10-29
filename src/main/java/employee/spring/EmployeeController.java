package employee.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private static EmployeeService employeeService = new EmployeeService();
    private static EmployeeBook employeeBook = new EmployeeBook();

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam String firstName, @RequestParam String lastName,
                              @RequestParam int salary, @RequestParam int department)
    {
        return employeeService.addEmployee(employeeBook, firstName, lastName, salary, department);
    }  //

    @GetMapping(path = "/remove")
    public String deleteEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName)
    {
        return employeeService.deleteEmployee(employeeBook, firstName, lastName);
    }

    @GetMapping(path = "/find")
    public String findEmployee(@RequestParam String firstName,
                               @RequestParam String lastName)
    {
        return employeeService.findEmployee(employeeBook, firstName, lastName);
    }   // add employee, remove employee, find employee

    @GetMapping(path = "/printEmployees")
    public String toString() {
        return employeeService.toString();
    }


    @GetMapping(path = "/departments/min-salary")
    public static Employee minSalaryInDepartment(@RequestParam int department) {
        return employeeService.minSalaryInDepartment(employeeBook, department);

    }

    @GetMapping(path = "/departments/max-salary")
    public static Employee maxSalaryInDepartment(@RequestParam int department) {
        return employeeService.maxSalaryInDepartment(employeeBook, department);
    }

    @GetMapping(path = "/sumDepartmentSalarys")
    public static int sumDepartmentSalarys(@RequestParam int department) {
        return employeeService.sumDepartmentSalarys(employeeBook, department);
    }

    @GetMapping(path = "/middleSalaryInDepartment")
    public static int middleSalaryInDepartment(@RequestParam int department) {
        return employeeService.middleSalaryInDepartment(employeeBook, department);
    }

    @GetMapping(path = "/multiplyPercentDepartmentsSalarys")
    public static String  multiplyPercentDepartmentsSalarys
            (@RequestParam int department,@RequestParam float percent) {

        return employeeService.multiplyPercentDepartmentsSalarys(employeeBook, department, percent);
    }

    @GetMapping(path = "/departments/all")
    public String printAllEmployee(@RequestParam(required = false) Integer department) {
        return (department == null) ?
                String.valueOf(ResponseEntity.ok(employeeService.allEmployees(employeeBook)))
                :
                String.valueOf(ResponseEntity.ok(employeeService.allEmployees(employeeBook, department)));
    }


}