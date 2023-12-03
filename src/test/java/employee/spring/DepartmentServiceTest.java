package employee.spring;

import employee.spring.action.EmployeeService;
import employee.spring.actionInDepartment.DepartmentService;
import employee.workWithEmployees.Employee;
import employee.workWithEmployees.EmployeeBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    public void initEmploueeService() {
        departmentService = new DepartmentService(employeeService);
        employeeService.addEmployee("aa", "bb", 100, 1);
    }

    @Test
    public void allEmployeesInDepartment() {
        String exampleDiscription = "first = Bb, last = Cc, salary = 1000, dep = 2";

        when(employeeService.allEmployees(2)).thenReturn(exampleDiscription);

        Assertions.assertEquals(departmentService.allEmployees(2), "first = Bb, last = Cc, salary = 1000, dep = 2");

        verify(employeeService, times(1)).allEmployees(2);
    }


    private static EmployeeBook employeeBook;
    @Test
    public void salarySum() {
//        inicialiceEB();
        when(employeeService.getEmployeeBook()).thenReturn(employeeBook);

        Assertions.assertEquals(departmentService.salarySum(1),1000);
        Assertions.assertEquals(departmentService.salarySum(2),999);

        verify(employeeService, times(2)).getEmployeeBook();
    }

    @BeforeEach
    public void inicialiceEB() {
        employeeBook = new EmployeeBook();
        Employee employee1 = new Employee("aa", "bb", 111, 1);
        Employee employee2 = new Employee("aatwo", "bb", 222, 1);
        Employee employee3 = new Employee("aathree", "bb", 300, 2);
        Employee employee4 = new Employee("aafour", "bb", 667, 1);
        Employee employee5 = new Employee("aafive", "bb", 699, 2);
        employeeBook.put(employee1.getFirstName() + " " + employee1.getLastName(), employee1);
        employeeBook.put(employee2.getFirstName() + " " + employee2.getLastName(), employee2);
        employeeBook.put(employee3.getFirstName() + " " + employee3.getLastName(), employee3);
        employeeBook.put(employee4.getFirstName() + " " + employee4.getLastName(), employee4);
        employeeBook.put(employee5.getFirstName() + " " + employee5.getLastName(), employee5);

    }

    @Test
    public void maxSalary() {
        when(employeeService.getEmployeeBook()).thenReturn(employeeBook);

        Assertions.assertEquals(departmentService.maxSalary(1), 667);
        Assertions.assertEquals(departmentService.maxSalary(2), 699);

        verify(employeeService, times(2)).getEmployeeBook();
    }

    @Test
    public void minSalary() {
        when(employeeService.getEmployeeBook()).thenReturn(employeeBook);

        Assertions.assertEquals(departmentService.minSalary(1), 111);
        Assertions.assertEquals(departmentService.minSalary(2), 300);

        verify(employeeService, times(2)).getEmployeeBook();
    }

    @Test
    public void allEmployees() {
        when(employeeService.getEmployeeBook()).thenReturn(employeeBook);
        when(employeeService.allEmployees(anyInt())).thenReturn("null. ");

        Assertions.assertEquals("departmen Number 1 null. departmen Number 2 null. ", departmentService.allEmployees());

        verify(employeeService, times(1)).getEmployeeBook();
        verify(employeeService, times(2)).allEmployees(anyInt());//2 раза потому что два департамента
    }
}
