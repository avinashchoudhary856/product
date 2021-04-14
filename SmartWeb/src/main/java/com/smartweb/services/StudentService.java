package com.smartweb.services;

import com.smartweb.entities.Student;

public interface StudentService {
    //in interface all methods are implicitly public and abstract
    void saveStudent(Student student);

    Student getStudent(String rollNo);

    void assignTeacherToStudent();

    void printStudentsData();
}
