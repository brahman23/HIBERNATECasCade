package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.util.DBUtil;

import java.util.List;

public class LessonDaoIlmp implements LessonDao {
    private final SessionFactory sessionFactory = DBUtil.creatSessionFactory();
    @Override
    public void saveLesson(Long course_id, Lesson lesson) {

        try {
            Session sessionSave = sessionFactory.openSession();

            sessionSave.beginTransaction();
            Course course = sessionSave.get(Course.class,course_id);
            if (course == null) {
                System.out.println("(Lessonlmp)course is null");
            } else {
                lesson.setCourse(course);
                sessionSave.merge(lesson);
            }
            sessionSave.getTransaction().commit();
            sessionSave.close();
        } catch (Exception exception) {
            System.out.println("not is saved Lesson");
        }
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        try {
            Session sessionUpdate = sessionFactory.openSession();
            sessionUpdate.getTransaction().begin();
            Lesson updatedLesson = sessionUpdate.get(Lesson.class, id);
            if (updatedLesson == null) {
                System.out.println("lesson is null");
            } else {
                updatedLesson.setName(lesson.getName());
                updatedLesson.setVideoLink(lesson.getVideoLink());
                updatedLesson.setTasks(lesson.getTasks());
                updatedLesson.setCourse(lesson.getCourse());
                sessionUpdate.merge(updatedLesson);
                sessionUpdate.getTransaction().commit();
                sessionUpdate.close();
            }
        } catch (Exception e) {
            System.out.println("not is update lesson");
        }
    }



    @Override
    public Lesson getLessonById(Long id) {
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            Lesson lesson = session.find(Lesson.class, id);
            if (lesson == null) {
                System.out.println("lesson is null");
            }
            session.getTransaction().commit();
            session.close();
            return lesson;
        } catch (Exception e) {
            System.out.println(" getLessonById error");
        }
        return null;
    }

    @Override
    public List<Lesson> getLessonByCourseId(Long course_id) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Course course = session.find(Course.class,course_id);
            List<Lesson> lessons = course.getLessons();
            session.getTransaction().commit();
            session.close();
            return lessons;
        } catch(Exception e){
            System.out.println("getLessonByCourseId error");
        }
        return null;
    }
}
