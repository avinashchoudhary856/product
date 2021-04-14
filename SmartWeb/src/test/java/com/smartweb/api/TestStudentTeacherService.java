package com.smartweb.api;

import com.smartweb.common.Subject;
import com.smartweb.entities.Student;
import com.smartweb.entities.Teacher;
import com.smartweb.services.StudentService;
import com.smartweb.services.TeacherService;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestStudentTeacherService extends BaseTest{
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;


    @Before
    public void setup(){
        super.setup();
    }

    @Test
    public void testStudentAndTeacher(){

        createStudent("101","Akshay Kumar",Subject.A,Subject.B);
        createStudent("102","Tarun Chhipa",Subject.C,Subject.A);
        createStudent("103","Vikas Sharma",Subject.A,Subject.B);
        createStudent("104","Manoj Sharma",Subject.C,Subject.B);

        createTeacher("Abhinay Sharma",Subject.A,Subject.B);
        createTeacher("Gagan Pratap", Subject.B,Subject.C);

        studentService.assignTeacherToStudent();
        Student akshay = studentService.getStudent("101");
        studentService.printStudentsData();
        Assert.assertEquals("Abhinay Sharma",akshay.getMap().get(Subject.A).getTeacherName());

    }

    private void createStudent(String rollNo, String name, Subject ...subjects){
        Student s = new Student();
        s.setStudentName(name);
        s.setRollNo(rollNo);
        Set<Subject> subjects2 = new HashSet<>(Arrays.asList(subjects));
        s.setSubjects(subjects2);
        studentService.saveStudent(s);

    }

    private void createTeacher(String name, Subject... subjects) {
        Teacher t = new Teacher();
        t.setTeacherName(name);
        Set<Subject> subjects1 = new HashSet<>(Arrays.asList(subjects));
        t.setSubjects(subjects1);
        teacherService.saveTeacher(t);
    }

}