package com.smartweb.services;

import com.smartweb.common.Subject;
import com.smartweb.entities.Student;
import com.smartweb.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private Set<Student> students = new HashSet<>();

    @Autowired
    TeacherService teacherService;

    @Override
    public void saveStudent(Student student) {
        students.add(student);
    }

    @Override
    public Student getStudent(String rollNo) {
        for(Student student : students){
            if(student.getRollNo().equals(rollNo)){
                return student;
            }
        }
        return null;
    }

    @Override
    public void printStudentsData(){
        for(Student student : students){
            Set<Map.Entry<Subject, Teacher>> map =  student.getMap().entrySet();
            for (Map.Entry<Subject, Teacher> data : map ) {
                System.out.println("Roll No. : "+ student.getRollNo()+", Name : "+student.getStudentName()+", "+data.getKey()+", "+data.getValue().getTeacherName());
            }
        }
    }

    public void assignTeacherToStudent() {
        for (Student student : students) {
            Set<Subject> subjects = student.getSubjects();
            for (Subject subject : subjects) {
                Teacher teacher = teacherService.findTeacher(subject);
                teacher.addStudentToSubject(subject,student);
                student.addSubjectToTeacher(subject,teacher);
            }
        }
    }

}