package com.github.raydeth.service;

import com.github.raydeth.model.Employee;
import com.github.raydeth.rest.EmployeeRest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class EmployeeService {

    private final EmployeeRest rest = new EmployeeRest();
    private final SalaryService salaryService = new SalaryService();

    public List<Employee> hiredEmployees() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Employee>> hiredEmployeesCf = rest.hiredEmployees();
        CompletableFuture<List<CompletableFuture>> salariesFc = hiredEmployeesCf
                .thenApply(employees -> employees.stream().map(salaryService::getSalary).collect(Collectors.toList()));
        salariesFc.get()
                .forEach(CompletableFuture::join);
        return hiredEmployeesCf.get();
    }
}
