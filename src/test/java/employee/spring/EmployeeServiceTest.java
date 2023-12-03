package employee.spring;

import employee.exception.EmployeeAlreadyAddedException;
import employee.exception.EmployeeStorageIsFullException;
import employee.exception.TaboSymbolsInEmployeeException;
import employee.spring.action.EmployeeService;
import employee.workWithEmployees.Employee;
import employee.workWithEmployees.EmployeeBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService();


    @Test
    public void addEmployee() {
        employeeService.addEmployee("aa", "bb", 100, 1);

        Assertions.assertEquals(employeeService.allEmployees(),
                "departmen Number 11) Employee{firstName='Aa', lastName='Bb', salary=100, department=1}. ");

    }

    @Test
    public void addWithTaboSymbols() {
        Assertions.assertThrows(TaboSymbolsInEmployeeException.class, ()->{
            employeeService.addEmployee("aa1", "bb-", 100, 1);
        });
    }

    @Test
    public void crowdedEmployeeBook() {
        employeeService.addEmployee("aa", "bb", 100, 1);
        employeeService.addEmployee("cc", "dd", 100, 2);
        employeeService.addEmployee("ee", "ff", 100, 3);
        employeeService.addEmployee("gg", "hh", 100, 1);
        employeeService.addEmployee("jj", "kk", 100, 2);
        Assertions.assertThrows(EmployeeStorageIsFullException.class, () -> {
            employeeService.addEmployee("gg", "mm", 100, 3);
        });

    }

    @Test
    public void isEmployeeBookSet() {
        employeeService.addEmployee("aa", "bb", 100, 1);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.addEmployee("aa", "bb", 333, 2);
        });

    }

    @Test
    public void salaryInDepartment() {
        employeeService.addEmployee("aa", "bb", 100, 1);
        employeeService.addEmployee("aaTwo", "bbTwo", 200, 1);
        employeeService.addEmployee("aaThree", "bbThree", 300, 2);

        Assertions.assertEquals(employeeService.sumDepartmentSalarys(1), 300);
    }

    @Test
    public void deleteEmpty() {
        Assertions.assertEquals(
                "такого сотрудника итак нет",
                employeeService.deleteEmployee("Aa", "Bb")
        );


    }
    @Test
    public void deleteEmployee() {
        employeeService.addEmployee("aa", "bb", 100, 1);
        Assertions.assertEquals(
                "Сотрудник удален",
                employeeService.deleteEmployee("Aa", "Bb")
        );
    }

    @Test
    public void findEmployee() {
        employeeService.addEmployee("aa", "bb", 100, 1);
        employeeService.addEmployee("aaTwo", "bbTwo", 200, 1);
        employeeService.addEmployee("aaThree", "bbThree", 300, 2);

        Assertions.assertEquals(employeeService.findEmployee("Aa", "Bb"),
                "Employee{firstName='Aa', lastName='Bb', salary=100, department=1}");
    }

    @Test
    public void findTaboSymbolEx() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.findEmployee("^&*-", "4567");
        });
    }

    @Test
    public void notFindEmployee() {
        employeeService.addEmployee("aaTwo", "bbTwo", 200, 1);
        employeeService.addEmployee("aaThree", "bbThree", 300, 2);

        Assertions.assertEquals(employeeService.findEmployee("Aa", "Bb"),
                "сотрудник не найден");
    }

}
