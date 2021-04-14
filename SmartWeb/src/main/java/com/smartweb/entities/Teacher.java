package com.smartweb.entities;

import com.smartweb.common.Subject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teacherName;
    private Set<Subject> subjects;
    private Map<Subject,Set<Student>> subjectStudents = new HashMap<>();
    
    public String getTeacherName() {
        return teacherName;
    }

    public Map<Subject, Set<Student>> getSubjectStudents() {
        return subjectStudents;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addStudentToSubject(Subject subject,Student student){
        Set<Student> students = subjectStudents.get(subject);
        if(students == null){
            students = new HashSet<>();
        }
        students.add(student);
        subjectStudents.put(subject, students);
    }

}