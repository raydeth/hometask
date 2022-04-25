package com.github.raydeth.model;

public class Employee {
    private Long id;
    private Long salary;

    public Employee() {
    }

    public Employee(Long id, Long salary) {
        this.id = id;
        this.salary = salary;
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
