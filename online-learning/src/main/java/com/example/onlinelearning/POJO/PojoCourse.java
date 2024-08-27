package com.example.onlinelearning.POJO;

import com.example.onlinelearning.Entity.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PojoCourse {
    private Long courseId;
    private String courseName;
    private String description;

    public static PojoCourse fromEntity(Course course) {
        var pojo = new PojoCourse();
        pojo.setCourseId(course.getCourseId());
        pojo.setCourseName(course.getCourseName());
        pojo.setDescription(course.getDescription());
        return pojo;
    }

    public static Course toEntity(PojoCourse pojoCourse) {
        var course = new Course();
        course.setCourseId(pojoCourse.getCourseId());
        course.setCourseName(pojoCourse.getCourseName());
        course.setDescription(pojoCourse.getDescription());
        return course;
    }
}
