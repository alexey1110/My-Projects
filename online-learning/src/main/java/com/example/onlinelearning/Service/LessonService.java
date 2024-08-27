package com.example.onlinelearning.Service;

import com.example.onlinelearning.Entity.Course;
import com.example.onlinelearning.Entity.Lesson;
import com.example.onlinelearning.POJO.PojoCourse;
import com.example.onlinelearning.POJO.PojoLesson;
import com.example.onlinelearning.Repository.CourseRepository;
import com.example.onlinelearning.Repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseRepository courseRepository;
    public List<PojoLesson> getAllLessons(int courseId) {
        List<Lesson> lessons = lessonRepository.findByCourseCourseId(courseId);
        List<PojoLesson> pojoLessons = new ArrayList<>(lessons.size());
        for (Lesson lesson : lessons) {
            pojoLessons.add(PojoLesson.fromEntity(lesson));
        }
        return pojoLessons;
    }

    public PojoLesson getLessonById(long courseId, long lessonId) {
        try {
            Lesson lesson = lessonRepository.findByLessonIdAndCourseCourseId(lessonId, courseId);
            return PojoLesson.fromEntity(lesson);
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public PojoLesson createLesson(long courseId, PojoLesson pojoLesson) {
        Lesson lesson = PojoLesson.toEntity(pojoLesson);
        Course course = courseRepository.findById(courseId).get();
        lesson.setCourse(course);
        return PojoLesson.fromEntity(lessonRepository.save(lesson));
    }

    public PojoLesson updateLesson(long courseId, long lessonId, PojoLesson pojoLesson) {
        try {
            Lesson lesson = lessonRepository.findByLessonIdAndCourseCourseId(courseId, lessonId);
            lesson.setLessonTitle(pojoLesson.getLessonTitle());
            lesson.setContent(pojoLesson.getContent());
            return PojoLesson.fromEntity(lessonRepository.save(lesson));
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public void deleteLesson(long courseId, long lessonId) {
        lessonRepository.deleteByLessonIdAndCourseCourseId(lessonId, courseId);
    }

//    public List<PojoLesson> findLessonByTitle(String title) {
//        List<Lesson> lessons = lessonRepository.findByLessonTitleContainingIgnoreCase(title);
//        List<PojoLesson> pojoLessons = new ArrayList<>();
//        for (Lesson lesson : lessons) {
//            pojoLessons.add(PojoLesson.fromEntity(lesson));
//        }
//        return pojoLessons;
//    }
//    public List<PojoLesson> findLessonByTitle(long courseId, String title) {
//        List<Lesson> lessons = lessonRepository.findByCourseCourseIdAndLessonTitleContainingIgnoreCase(courseId, title);
//        List<PojoLesson> pojoLessons = new ArrayList<>(lessons.size());
//        for (Lesson lesson : lessons) {
//            pojoLessons.add(PojoLesson.fromEntity(lesson));
//        }
//        return pojoLessons;
//    }
public List<PojoLesson> findLessonByCourseId(long courseId) {
    List<Lesson> lessons = lessonRepository.findByCourseCourseId(courseId);
    List<PojoLesson> pojoLessons = new ArrayList<>(lessons.size());
    for (Lesson lesson : lessons) {
        pojoLessons.add(PojoLesson.fromEntity(lesson));
    }
    return pojoLessons;
}

}