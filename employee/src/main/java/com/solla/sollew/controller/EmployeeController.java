package com.solla.sollew.controller;

import com.solla.sollew.entity.Employee;
import com.solla.sollew.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    //constructor injection
    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    //create "/list" and return a list of employees
    @GetMapping("/list")
    public String listEmployees(Model theModel){
        //get the employees from db
        List<Employee> theEmployees = employeeService.findAll();
        //add it to theModel attribute
        theModel.addAttribute("employees",theEmployees);
        return "employees/list-employees";
    }

    @GetMapping("/new")
    public String newEmployees(Model theModel){
        //create new employee
        Employee theEmployee = new Employee();
        //add it to theModel attribute for the UI to consume
        theModel.addAttribute("employee",theEmployee);
        return "employees/new-employees";
    }

    //save the employee and redirect to the home page
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        //save the model into Db
        employeeService.save(theEmployee);

        //use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

    //get the employee base on id and show it on the form
    @GetMapping("/update")
    public String getEmployee(@RequestParam("employeeId") int employeeId,Model theModel){

        Employee theEmployee = employeeService.findById(employeeId);
        //add employee to model
        theModel.addAttribute("employee",theEmployee);


        return "employees/new-employees";
    }

    //get the employee base on id and delete it
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int employeeId,Model theModel){

        //delete employee by Id
        employeeService.deleteById(employeeId);

        //use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

}
