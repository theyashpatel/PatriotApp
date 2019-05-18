package ypdp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ypdp.api.*;
import ypdp.api.base.SecuredRequest;
import ypdp.dao.ClassRegistration;
import ypdp.dao.Classes;
import ypdp.dao.Session;
import ypdp.dao.repository.ClassRegistrationRepository;
import ypdp.dao.repository.ClassesRepository;
import ypdp.dao.repository.SessionRepository;
import ypdp.service.CourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private ClassRegistrationRepository classRegistrationRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public AllCoursesResponse allCourses() {
        List<Classes> classes = classesRepository.findAll();
        AllCoursesResponse acr = new AllCoursesResponse();
        acr.setClasses(toCourses(classes));
        return acr;
    }

    @Override
    public void registerClass(RegisterClassRequest rcr) {
        String authToken = rcr.getAuthToken();
        Session session = sessionRepository.getOne(authToken);

        ClassRegistration cr = new ClassRegistration();
        cr.setId(UUID.randomUUID().toString());
        cr.setUserId(session.getUserId());
        cr.setClassId(rcr.getClassId());

        classRegistrationRepository.save(cr);
    }

    @Override
    public void dropClass(DropClassRequest dcr) {
        String authToken = dcr.getAuthToken();
        Session session = sessionRepository.getOne(authToken);
        classRegistrationRepository.deleteByClassIdAndUserId(dcr.getClassId(), session.getUserId());
    }

    @Override
    public List<Course> classSchedule(ClassScheduleRequest csr) {
        String userId = getUserId(csr);
        List<ClassRegistration> classRegistrations = classRegistrationRepository.findByUserId(userId);
        return getCourses(classRegistrations);
    }

    private List<Course> getCourses(List<ClassRegistration> classRegistrations) {
        List<Course> courses = new ArrayList<>();
        for (ClassRegistration cr : classRegistrations) {
            Classes cl = classesRepository.findById(cr.getClassId()).get();
            courses.add(toCourse(cl));
        }
        return courses;
    }

    private String getUserId(SecuredRequest sr) {
        String authToken = sr.getAuthToken();
        Session session = sessionRepository.getOne(authToken);
        return session.getUserId();
    }

    private List<Course> toCourses(List<Classes> classes) {
        List<Course> courses = new ArrayList<>();
        for (Classes cl: classes) {
            courses.add(toCourse(cl));
        }
        return courses;
    }

    private Course toCourse(Classes cl) {
        List<Course> courses = new ArrayList<>();

        Course c = new Course();
        c.setClassId(cl.getId());
        c.setTitle(cl.getTitle());
        c.setSubject(cl.getSubject());
        c.setCourse(cl.getCourse());
        c.setSection(cl.getSection());
        c.setInstructor(cl.getInstructor());
        c.setRateMyProfessor(cl.getRateMyProfessor());
        c.setCredit(cl.getCredit());
        c.setLevel(cl.getLevel());
        c.setLocation(cl.getLocation());
        c.setCampus(cl.getCampus());
        c.setDays(cl.getDays());
        c.setTime(cl.getTime());

        int capacity = cl.getCapacity();
        int actual = (int) classRegistrationRepository.countByClassId(cl.getId());
        int remaining = capacity - actual;

        c.setCapacity(capacity);
        c.setActual(actual);
        c.setRemaining(remaining);

        courses.add(c);
        return c;
    }
}
