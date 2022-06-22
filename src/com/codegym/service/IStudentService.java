package com.codegym.service;

import com.codegym.module.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    void add(Student newStudent);

    List<Student> update(Student newStudent);

    Student findById(long id);

    boolean exist(long id);

    boolean existByName(String name);

    boolean existsById(long id);

    void deleteById(long id);

    List<Student> findAllOrderByPriceASC();

    List<Student> findAllOrderByPriceDESC();
    public Student findMaxPrice();
}