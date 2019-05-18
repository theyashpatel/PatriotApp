package ypdp.service;

import ypdp.api.*;

import java.util.List;

public interface CourseService {

    AllCoursesResponse allCourses();
    void registerClass(RegisterClassRequest rcr);
    void dropClass(DropClassRequest dcr);
    List<Course> classSchedule(ClassScheduleRequest csr);
}