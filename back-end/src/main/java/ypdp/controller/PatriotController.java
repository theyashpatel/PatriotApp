package ypdp.controller;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ypdp.api.*;
import ypdp.service.CourseService;
import ypdp.service.LoginService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@Transactional
public class PatriotController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest lr) {
        return loginService.login(lr);
    }

    @RequestMapping("/courses/list")
    public AllCoursesResponse getAllCourses() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }
        return courseService.allCourses();
    }

    @RequestMapping("/class/register")
    public GeneralResponse registerClass(@RequestHeader(value = "Authorization") String authToken, @RequestBody RegisterClassRequest rcr) {
        rcr.setAuthToken(authToken);
        courseService.registerClass(rcr);
        return new GeneralResponse("SUCCESS");
    }

    @RequestMapping("/class/drop")
    public GeneralResponse dropClass(@RequestHeader(value = "Authorization") String authToken, @RequestBody DropClassRequest dcr) {
        dcr.setAuthToken(authToken);
        courseService.dropClass(dcr);
        return new GeneralResponse("SUCCESS");
    }

    @RequestMapping("/class/schedule")
    public ClassScheduleResponse classSchedule(@RequestHeader(value = "Authorization") String authToken) {
        ClassScheduleRequest csRequest = new ClassScheduleRequest();
        csRequest.setAuthToken(authToken);
        List<Course> schedules = courseService.classSchedule(csRequest);

        ClassScheduleResponse csResponse = new ClassScheduleResponse();
        csResponse.setClasses(schedules);
        return csResponse;
    }
}
