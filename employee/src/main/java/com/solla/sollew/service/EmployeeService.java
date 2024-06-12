package com.solla.sollew.service;

import com.solla.sollew.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save (Employee theEmployee);

    void deleteById (int id);
}
