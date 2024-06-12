package com.solla.sollew.controller;

import com.solla.sollew.entity.Employee;
import com.solla.sollew.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    //constructor injection
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //exposes "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> getALlEmployees(){
        return employeeService.findAll();
    }

    //add getmapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null){
            throw new RuntimeException("Employee id is not found - " + employeeId);
        }
        return theEmployee;
    }
    //POST /employees - add new employee
    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee theEmployee){

        //set id = 0 force a save of new item instead of update
        theEmployee.setId(0);

        return employeeService.save(theEmployee);
    }
    //PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){

        return employeeService.save(theEmployee);
    }

    //DELETE /employees - update existing employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null) {
            throw new RuntimeException("Employee id is not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);

        return "Deleted the employee id - " + employeeId;
    }
}
