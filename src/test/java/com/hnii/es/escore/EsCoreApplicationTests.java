package com.hnii.es.escore;

import com.hnii.es.escore.dao.EmployeeRepository;
import com.hnii.es.escore.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsCoreApplicationTests {
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void contextLoads() {
    }

    public void findByName(){
        Page<Employee> employee = employeeRepository.findByName(PageRequest.of(0,2, Sort.by(Sort.Order.asc("age"))),"wade");

        System.out.println(employee.getTotalElements());

    }
}
