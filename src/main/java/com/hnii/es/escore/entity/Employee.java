package com.hnii.es.escore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

/**
 * @author lilang
 * @vertion 1.0
 * @classname Employee
 * @description 员工实体类
 * @date 2019/6/27 15:43
 * @Document(indexName = "company",type = "employee", shards = 1,replicas = 0, refreshInterval = "-1")
 */
@Document(indexName = "company",type = "employee")
public class Employee {

    @Id
    private String id;
    @Field
    private String name;
    @Field
    private Integer age;
    @Field
    private String position;
    @Field
    private String country;
    @Field
    private Date join_date;
    @Field
    private long salary;

    public Employee(String id, String name, Integer age, String position, String country, Date join_date, long salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
        this.country = country;
        this.join_date = join_date;
        this.salary = salary;
    }

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}
