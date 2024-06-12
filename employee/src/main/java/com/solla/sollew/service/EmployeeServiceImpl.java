package com.solla.sollew.service;

import com.solla.sollew.dao.EmployeeRepository;
import com.solla.sollew.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    //inject EmployeeDAO
    private EmployeeRepository employeeRepository;

    //constructor injection
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        employeeRepository = theEmployeeRepository;
    }


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByEmailAsc();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee theEmployee = null;

        if(result.isPresent()){
            theEmployee = result.get();
        }
        else{
            //employeeId not found
            throw new RuntimeException("Didn't find the employee id of " + id);
        }

        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
