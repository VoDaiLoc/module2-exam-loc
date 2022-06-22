package com.codegym.service;

import com.codegym.module.Student;
import com.codegym.utils.CSVUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class StudentService implements IStudentService {

    public final static String PATH = "data/students.csv";

    private static StudentService instance;

    private StudentService() {
    }

    public static StudentService getInstance() {
        if (instance == null)
            instance = new StudentService();
        return instance;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        List<String> records = com.codegym.utils.CSVUtils.read(PATH);
        for (String record : records) {
            students.add(Student.parse(record));
        }
        return students;
    }

    @Override
    public void add(Student newStudent) {
        List<Student> students = findAll();
        students.add(newStudent);
        com.codegym.utils.CSVUtils.write(PATH, students);
    }

    @Override
    public List<Student> update(Student newStudent) {
        List<Student> students = findAll();
        for (Student student : students) {
            if (student.getMSV() == newStudent.getMSV()) {
                String name = newStudent.getName();
                if (name != null && !name.isEmpty())
                    student.setName(name);

                int age = newStudent.getAge();
                if (age >= 18)
                    student.setAge(age);

                String gender = newStudent.getGender();
                if (gender != null && gender.isEmpty())
                    student.setGender(gender);

                String address = newStudent.getAddress();
                if (address != null && address.isEmpty())
                    student.setAddress(address);

                double avgScore = newStudent.getAvgScore();
                if (avgScore >= 0 && avgScore <= 10)
                    student.setAvgScore(avgScore);

                CSVUtils.write(PATH, students);
                break;
            }
        }
        return students;
    }

    @Override
    public Student findById(long id) {
        List<Student> students = findAll();
        for (Student student : students) {
            if (student.getMSV() == id)
                return student;
        }
        return null;
    }

    public List<Student> sortByScoreASC() {
        List<Student> students = new ArrayList<>(findAll());
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                double result = o1.getAvgScore() - o2.getAvgScore();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            }
        });
        return students;
    }

    public List<Student> sortByScoreDES() {
        List<Student> students = new ArrayList<>(findAll());
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                double result = o2.getAvgScore() - o1.getAvgScore();
                if (result == 0)
                    return 0;
                return result > 0 ? 1 : -1;
            }
        });
        return students;
    }

    @Override
    public boolean exist(long id) {
        return false;
    }

    @Override
    public boolean existByName(String name) {
        return false;
    }

    @Override
    public boolean existsById(long id) {
        List<Student> products = findAll();
        for (Student student : products) {
            if (student.getMSV() == id)
                return true;
        }
        return false;
    }

    @Override
    public void deleteById(long id) {
        List<Student> product = findAll();

        product.removeIf(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getMSV() == id;
            }

        });
        CSVUtils.write(PATH, product);
    }

    @Override
    public List<Student> findAllOrderByPriceASC() {
        return null;
    }

    @Override
    public List<Student> findAllOrderByPriceDESC() {
        return null;
    }

    @Override
    public Student findMaxPrice() {
        return null;
    }

}
