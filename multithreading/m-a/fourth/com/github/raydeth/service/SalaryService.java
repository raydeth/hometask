package com.github.raydeth.service;

import com.github.raydeth.model.Employee;
import com.github.raydeth.model.factory.SalaryFactory;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class SalaryService {

    private Map<Long, Long> salaries = SalaryFactory.getEmployeesWithSalary();

    public CompletableFuture getSalary(Employee employee) {
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            employee.setSalary(salaries.get(employee.getId()));
        });
    }
}
