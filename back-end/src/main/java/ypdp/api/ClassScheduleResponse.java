package ypdp.api;

import java.util.List;

public class ClassScheduleResponse {

    private List<Course> classes;

    public List<Course> getClasses() {
        return classes;
    }

    public void setClasses(List<Course> classes) {
        this.classes = classes;
    }
}
