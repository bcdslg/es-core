package com.hnii.es.escore.dao;

import com.hnii.es.escore.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lilang
 * @vertion 1.0
 * @classname EmployeeRepository
 * @description TODO
 * @date 2019/6/27 15:50
 */
@Component
public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {
    Page<Employee> findAll(Pageable pageable);

    List<Employee> findBySalaryIsGreaterThanEqual(long salary); //这个和jpa一样，只要命名根据提示并符合规范就可以。

    Page<Employee> findByName(Pageable pageable,String name);

    List<Employee> findByName(String name);

    List<Employee> findByNameLike(String name);

    /**
     * 自定义的查询： 这个可以在kibana中写好查询语句，再复制过来即可。
     * 复制的时候要从query后的{} 复制起走。 将复制好的内容，放在@query("") 中即可。
     * 如果有参数，用问号替换，第一个参数就是?0, 第二个参数就是?1  以此类推。
     */
    @Query("{\n" +
            "    \"match\": {\n" +
            "      \"name\": \"?0\"\n" +
            "    }\n" +
            "  }")
    List<Employee> findByNameDIY(String name);
}
