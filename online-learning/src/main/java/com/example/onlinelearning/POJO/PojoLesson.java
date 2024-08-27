package com.example.onlinelearning.POJO;

import com.example.onlinelearning.Entity.Lesson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PojoLesson {

    private Long lessonId;
    private String lessonTitle;
    private String content;
    private Long courseId;

    public static PojoLesson fromEntity(Lesson lesson) {
        var pojo = new PojoLesson();
        pojo.setLessonId(lesson.getLessonId());
        pojo.setLessonTitle(lesson.getLessonTitle());
        pojo.setContent(lesson.getContent());
        pojo.setCourseId(lesson.getCourseId());
        return pojo;
    }

    public static Lesson toEntity(PojoLesson pojoLesson) {
        var lesson = new Lesson();
        lesson.setLessonId(pojoLesson.getLessonId());
        lesson.setLessonTitle(pojoLesson.getLessonTitle());
        lesson.setContent(pojoLesson.getContent());
        return lesson;
    }
}
