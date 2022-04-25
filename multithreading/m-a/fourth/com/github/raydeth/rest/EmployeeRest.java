package com.github.raydeth.rest;

import com.github.raydeth.model.Employee;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.github.raydeth.model.factory.EmployeeFactory.getEmployeesWithoutSalary;

public class EmployeeRest {
    public CompletableFuture<List<Employee>> hiredEmployees() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

           return getEmployeesWithoutSalary();
        });
    }
}
