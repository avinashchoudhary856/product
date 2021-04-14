package com.smartweb.entities;

import com.smartweb.common.Subject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
//@Table(name="student", indexes = {@Index(name="idx_student_ref_student", columnList="ref_student_id")})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rollNo;
    private String studentName;
    private Set<Subject> subjects;

    private Map<Subject,Teacher> subjectTeacher = new HashMap<>();

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Map<Subject, Teacher> getMap() {
        return subjectTeacher;
    }

    public void setMap(Map<Subject,Teacher> map) {
        this.subjectTeacher = map;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public void addSubjectToTeacher(Subject subject, Teacher teacher){
        subjectTeacher.put(subject,teacher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(rollNo, student.rollNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo);
    }

}