package peaksoft.service;

import peaksoft.dao.LessonDao;
import peaksoft.dao.LessonDaoIlmp;
import peaksoft.entity.Lesson;

import java.util.List;

public class LessonService implements LessonDao {

    LessonDaoIlmp lessonDaoIlmp = new LessonDaoIlmp();
    @Override
    public void saveLesson(Long course_id, Lesson lesson) {
        lessonDaoIlmp.saveLesson(course_id,lesson);
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        lessonDaoIlmp.updateLesson(id,lesson);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonDaoIlmp.getLessonById(id);
    }

    @Override
    public List<Lesson> getLessonByCourseId(Long course_id) {
        return lessonDaoIlmp.getLessonByCourseId(course_id);
    }
}
