package com.github.raydeth.model.factory;

import com.github.raydeth.model.Employee;

import java.util.List;

public class EmployeeFactory {
    public static final List<Employee> getEmployeesWithoutSalary() {
        List<Employee> employees = List.of(new Employee(1L), new Employee(2L), new Employee(3L));
        return employees;
    }
}
