package com.github.raydeth.model.factory;

import java.util.HashMap;
import java.util.Map;

public class SalaryFactory {
    public static final Map<Long, Long> getEmployeesWithSalary() {
        Map<Long, Long> salaries = new HashMap<>() {{
            put(1L, 100L);
            put(2L, 200L);
            put(3L, 300L);
            put(4L, 400L);
            put(5L, 500L);
        }};

        return salaries;
    }
}
