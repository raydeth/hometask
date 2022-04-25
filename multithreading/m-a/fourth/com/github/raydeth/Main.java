package com.github.raydeth;

import com.github.raydeth.model.Employee;
import com.github.raydeth.service.EmployeeService;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        EmployeeService service = new EmployeeService();
        List<Employee> employees = service.hiredEmployees();
        employees.forEach(e -> System.out.printf("Employee id: %d with salary: %d\n", e.getId(), e.getSalary()));
        System.out.println("Time: " + (System.currentTimeMillis() - startTime) / 1000);
    }
}
