package employee.spring.action;

import employee.exception.EmployeeStorageIsFullException;
import employee.exception.TaboSymbolsInEmployeeException;
import employee.workWithEmployees.Employee;
import employee.workWithEmployees.EmployeeBook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private static EmployeeService employeeService = new EmployeeService();




    @GetMapping
    public static String check() {
        return "check";
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam String firstName, @RequestParam String lastName,
                              @RequestParam int salary, @RequestParam int department) {
        try {
            return employeeService.addEmployee(firstName, lastName, salary, department);
        } catch (EmployeeStorageIsFullException e) {
            return "full list employees";
        } catch (TaboSymbolsInEmployeeException e) {
            return "there is tabo symbol!   ";
        } catch (Exception e) {
            return "400 Bad Request";
        }
    }  //

    @GetMapping(path = "/remove")
    public String deleteEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName) {
        try {
            return employeeService.deleteEmployee(firstName, lastName);
        } catch (Exception e) {
            return "400 Bad Request";
        }
    }

    @GetMapping(path = "/find")
    public String findEmployee(@RequestParam String firstName,
                               @RequestParam String lastName) {
        try {
            return employeeService.findEmployee(firstName, lastName);
        } catch (Exception e) {
            return "400 Bad Request";
        }
    }   // add employee, remove employee, find employee

    @GetMapping(path = "/printEmployees")
    public String toString() {
        return employeeService.toString();
    }


    @GetMapping(path = "/departments/min-salary")
    public static Employee minSalaryInDepartment(@RequestParam int department) {
        return employeeService.minSalaryInDepartment(department);

    }

    @GetMapping(path = "/departments/max-salary")
    public static Employee maxSalaryInDepartment(@RequestParam int department) {
        return employeeService.maxSalaryInDepartment(department);
    }

    @GetMapping(path = "/sumDepartmentSalarys")
    public static int sumDepartmentSalarys(@RequestParam int department) {
        return employeeService.sumDepartmentSalarys(department);
    }

    @GetMapping(path = "/middleSalaryInDepartment")
    public static int middleSalaryInDepartment(@RequestParam int department) {
        return employeeService.middleSalaryInDepartment(department);
    }

    @GetMapping(path = "/multiplyPercentDepartmentsSalarys")
    public static String multiplyPercentDepartmentsSalarys
            (@RequestParam int department, @RequestParam float percent) {

        return employeeService.multiplyPercentDepartmentsSalarys(department, percent);
    }

    @GetMapping(path = "/departments/all")
    public String printAllEmployee(@RequestParam(required = false) Integer department) {
        return (department == null) ?
                String.valueOf(ResponseEntity.ok(employeeService.allEmployees()))
                :
                String.valueOf(ResponseEntity.ok(employeeService.allEmployees(department)));
    }


}