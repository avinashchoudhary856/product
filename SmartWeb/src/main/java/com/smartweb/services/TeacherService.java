package com.smartweb.services;

import com.smartweb.common.Subject;
import com.smartweb.entities.Teacher;

public interface TeacherService {

    public void saveTeacher(Teacher teacher);

    public Teacher getTeacher(String name);

    public Teacher findTeacher(Subject subject);

}
