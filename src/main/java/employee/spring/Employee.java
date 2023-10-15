package employee.spring;

import java.util.Objects;


public class Employee {

//    private static int counter = 1;

    private final String firstName;
    private final String lastName;
//    private final String middleName;
//    private int salary;
//    private int department;
//    private final int id;


    public Employee(String firstName, String lastName /*, String middleName, int salary, int department*/) {
        this.firstName = firstName;
        this.lastName = lastName;
//        this.middleName = middleName;
//        this.salary = salary;
//        this.department = department;
//        id = counter++;
    }

//    public void setSalary(int salary) {
//        this.salary = salary;
//    }
//
//    public void setDepartment(int department) {
//        this.department = department;
//    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Employee:\n" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '.';
    }

    //    public String getMiddleName() {
//        return middleName;
//    }
//
//    public int getSalary() {
//        return salary;
//    }
//
//    public int getDepartment() {
//        return department;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public static int getCount() {
//        return counter;
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}