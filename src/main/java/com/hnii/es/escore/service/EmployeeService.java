package com.hnii.es.escore.service;


import com.hnii.es.escore.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> findById(String id);

    Employee save(Employee employee);

    List saveall(List<Employee> list);

    void delete(Employee employee);

    void delete(String id);

    boolean existsById(String id);

    Page<Employee> findAll(Pageable pageable);

    List<Employee> findBySalaryIsGreaterThanEqual(long salary);

    Page<Employee> findByName(Pageable pageable,String name);
}
