package peaksoft.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.util.DBUtil;

import java.util.List;

public class CourseDaoIlmp implements CourseDao {
    private final EntityManagerFactory managerFactory = DBUtil.creatSessionFactory();
    private final SessionFactory sessionFactory = DBUtil.creatSessionFactory();

    @Override
    public void saveCourse(Course course) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(new Course(course.getName(), course.getDuration(), course.getCreateAt(), course.getImageLink(), course.getDescription()));
            session.getTransaction().commit();
        }
    }

    @Override
    public Course getCourseById(Long id) {
        try {
            Session sessionSave = sessionFactory.openSession();
            sessionSave.beginTransaction();
            Course course = sessionSave.find(Course.class, id);
            if (course == null) {
                System.out.println("course is null");
            }
            sessionSave.getTransaction().commit();
            sessionSave.close();
            return course;
        } catch (Exception e) {
            System.out.println("course non is save");
            System.out.println(e.getStackTrace());
        }
        return null;
    }
    @Override
    public List<Course> getallCourse() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Course> courses = session.createQuery("select c from Course c order by c.createAt").list();
            session.getTransaction().commit();
            session.close();
            return courses;
        } catch (Exception e){
            System.out.println("erorr");
        }
        return null;
    }

    @Override
    public void updateCourse(Long id, Course course) {
        try {
            Session sessionUpdate = sessionFactory.openSession();
            sessionUpdate.beginTransaction();
            Course updatedCourse = sessionUpdate.find(Course.class, id);
            updatedCourse.setName(course.getName());
            updatedCourse.setDescription(course.getDescription());
            updatedCourse.setDuration(course.getDuration());
            updatedCourse.setLessons(course.getLessons());
            updatedCourse.setCreateAt(course.getCreateAt());
            updatedCourse.setImageLink(course.getImageLink());
            updatedCourse.setInstructors(course.getInstructors());
            sessionUpdate.merge(updatedCourse);
            sessionUpdate.getTransaction().commit();
            sessionUpdate.close();
        } catch (Exception e) {
            System.out.println("course not is update");
        }
    }

    @Override
    public void deleteCourseById(Long id) {
        try {
            EntityManager entityManager = managerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class,id);
            for (Instructor instructor : course.getInstructors()) {
                instructor.setCourse(null);
            }
            entityManager.remove(course);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            System.out.println("COURSE NOT IS DELETE");
        }
    }

    @Override
    public Course getCourseByName(String name) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Course> courses = session.createQuery("select c from Course c", Course.class).list();
            for ( Course course : courses) {
                if(course.getName().equals(name)){
                    System.out.println("course is faund");
                    return course;
                } else {
                    System.out.println("Course is not find");
                }
            }
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("getCourseByName error");
        }
        return null;
    }
}

