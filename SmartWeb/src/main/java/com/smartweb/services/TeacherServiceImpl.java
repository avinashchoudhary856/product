package com.smartweb.services;

import com.smartweb.common.Subject;
import com.smartweb.entities.Student;
import com.smartweb.entities.Teacher;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TeacherServiceImpl implements TeacherService{
    Set<Teacher> teachers = new HashSet<>();

    @Override
    public void saveTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public Teacher getTeacher(String name) {
        for(Teacher teacher : teachers){
            if(teacher.getTeacherName().equals(name)){
                return teacher;
            }
        }
        return null;
    }

    @Override
    public Teacher findTeacher(Subject subjectOfStudent) {
        Set<Teacher> teacherOfSubject = new HashSet<>();
        for(Teacher teacher : teachers){
            Set<Subject> subjectsOfTeachers = teacher.getSubjects();
            for(Subject subjectOfTeacher : subjectsOfTeachers){
                if(subjectOfTeacher == subjectOfStudent){
                    teacherOfSubject.add(teacher);
                }
            }
        }
        if(teacherOfSubject.isEmpty()) {
            return null;
        }
        if(teacherOfSubject.size() == 1){
            Teacher[] teachers = {};
            return teacherOfSubject.toArray(teachers)[0];
        }
        int sizeOfSelected = -1;
        Teacher selectedTeacher = null;
        for (Teacher teacher : teacherOfSubject) {
            Set<Student> subjectStudents = teacher.getSubjectStudents().get(subjectOfStudent);
            int n = subjectStudents == null ? 0 : subjectStudents.size();
            if(sizeOfSelected == -1 || n < sizeOfSelected){
                sizeOfSelected = n;
                selectedTeacher = teacher;
            }
        }
        return selectedTeacher;
    }

}