package com.hnii.es.escore.controller;

import com.hnii.es.escore.dao.EmployeeRepository;
import com.hnii.es.escore.entity.Employee;
import com.hnii.es.escore.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author lilang
 * @vertion 1.0
 * @classname EmployeeController
 * @description TODO
 * @date 2019/6/27 15:53
 */

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    /**
     * 添加员工
     */
    @GetMapping("/es/save")
    public String saveone() {
        Employee employee = new Employee("007", "wade tenoi", 33, "pf", "us", new Date(), 40000000L);

        employeeService.save(employee);
        System.out.println("success");
        return "success";
    }
    /**
     * 添加员工
     */
    @GetMapping("/es/savelist")
    public String savelist() {
        Employee employee = new Employee("001", "james", 33, "pf", "us", new Date(), 40000000L);
        Employee employee2 = new Employee("002", "kebi", 39, "sg", "us", new Date(), 30000000L);
        Employee employee3 = new Employee("003", "wade", 39, "sg", "us", new Date(), 10000000L);
        Employee employee4 = new Employee("004", "boshi", 37, "c", "us", new Date(), 8000000L);
        Employee employee5 = new Employee("005", "yaoming", 40, "c", "us", new Date(), 18000000L);
        Employee employee6 = new Employee("006", "hadeng", 31, "pg", "us", new Date(), 39000000L);

        List<Employee> list = new ArrayList<>();
        list.add(employee);
        list.add(employee2);
        list.add(employee3);
        list.add(employee4);
        list.add(employee5);
        list.add(employee6);

        employeeService.saveall(list);
        System.out.println("success");
        return "success";
    }

    /**
     * 根据id 查找
     */
    @GetMapping("/es/findbyid/{id}")
    public Optional<Employee> findbyid(@PathVariable("id") String id) {
        Optional<Employee> employee = employeeService.findById(id);
        return employee;
    }

    /**
     * 查找所有
     */
    @GetMapping("/es/findall")
    public Page<Employee> findall(int page, int size) {
        List<Sort.Order> orders=new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"age"));//按照年龄降序排列
        orders.add(new Sort.Order(Sort.Direction.DESC,"salary")); //按照salary升序排列
        Sort sort=Sort.by(orders); //使用sort.by  new sort 过期了， 这里按照order加入的先后顺序排序，先全部按照年龄降序，然后年纪相同的按照salary 降序排序
        Page<Employee> employees = employeeService.findAll(PageRequest.of(page-1, size, sort));

//        如果只是单条件排序，可以使用下面一条语句实现。(两种写法都可以实现)
//        Page<Employee> employees = employeeService.findAll(PageRequest.of(page-1, size,Sort.by(Sort.Direction.ASC,"age")));
//        Page<Employee> employees = employeeService.findAll(PageRequest.of(page-1, size,Sort.by(Sort.Order.asc("age"))));
        return employees;
    }

    /**
     * 修改(根据id 修改数据的salary)
     * 测试 http://localhost:8080/es/update?id=001&&salary=45000000      45000000后不能加L ,会被识别成字符串
     */
    @GetMapping("/es/update")
    public String update(String id, long salary) {
        Optional<Employee> employee = employeeService.findById(id);
        employee.get().setSalary(salary);
        employeeService.save(employee.get());
        return "success";
    }

    /**
     * 根据条件查询：薪水大于20000000的。
     */
    @GetMapping("/es/setsalary")
    public List<Employee> salary(long salary) {
        return employeeService.findBySalaryIsGreaterThanEqual(salary);
    }

    /**
     *  测试地址：
     *  http://localhost:8080/es/findbyname?name=wade
     *  这个是没有分页的
     *
     */
    @GetMapping("/es/findbyname")
    public  List<Employee> findByName(String name) {
        List<Employee> employees = employeeRepository.findByName(name);
        return employees;
    }

    /**
     * 这个是不仅分词 还将每个单词拆分成了字母，一般只要开头匹配上了，就都匹配上了，但是不能从单词的中间开始匹配。
     * test ： http://localhost:8080/es/findbynamelike?name=wa
     */
    @GetMapping("/es/findbynamelike")
    public  List<Employee> findByNameLike(String name) {
        List<Employee> employees = employeeRepository.findByNameLike(name);
        return employees;
    }
    /**
     * test：http://localhost:8080/es/findPageByName?page=1&&size=1&&name=wade
     * findByName 是做了分词的，wade  和 wade tenoi 都能查出来 但是wa 查不出来
     * 这个是分页的
     */
    @GetMapping("/es/findPageByName")
    public  Page<Employee> findPageByName(int page,int size,String name) {
        Page<Employee> employees = employeeRepository.findByName(PageRequest.of(page-1,size,Sort.by(Sort.Order.asc("age"))),name);
        return employees;
    }

    @GetMapping("/es/findbynamediy")
    public  List<Employee> findByNameDIY(String name) {
        List<Employee> employees = employeeRepository.findByNameDIY(name);
        return employees;
    }

    //聚合的问题，
    //为什么不能根据id排序
    //我觉的可以不使用service的地方就不使用，一定要自定义使用的复杂查询才使用。
    //如果使用jrebel 的debug 模式，必须重启新的class文件才能生效。run 模式 则可以更新class文件
}
