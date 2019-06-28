package com.hnii.es.escore.service.impl;

import com.hnii.es.escore.dao.EmployeeRepository;
import com.hnii.es.escore.entity.Employee;
import com.hnii.es.escore.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Optional<Employee> findById(String id) {
        //CrudRepository中的方法
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> saveall(List<Employee> list) {
        return (List<Employee>) employeeRepository.saveAll(list);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return employeeRepository.existsById(id);
    }


    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }


    @Override
    public List<Employee> findBySalaryIsGreaterThanEqual(long salary) {
        return employeeRepository.findBySalaryIsGreaterThanEqual(salary);
    }

    @Override
    public Page<Employee> findByName(Pageable pageable,String name) {
        return employeeRepository.findByName(pageable,name);
    }


}