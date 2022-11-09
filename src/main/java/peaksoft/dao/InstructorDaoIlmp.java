package peaksoft.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.util.DBUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InstructorDaoIlmp implements InstructorDao {
    private final EntityManagerFactory managerFactory = DBUtil.creatSessionFactory();
    private final SessionFactory sessionFactory = DBUtil.creatSessionFactory();
    @Override
    public void saveInstructor(Instructor instructor) {
        try {
            EntityManager entityManager1 = managerFactory.createEntityManager();
            entityManager1.getTransaction().begin();
            entityManager1.persist(new Instructor(instructor.getFirstName(), instructor.getLastName(), instructor.getEmail(), instructor.getPhoneNumber()));
            entityManager1.getTransaction().commit();
            entityManager1.close();
        } catch (Exception e) {
            System.out.println("not save Instructor");
        }
    }


    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        try {
            Session sessionUpdate = sessionFactory.openSession();
            sessionUpdate.beginTransaction();
            Instructor updatedInstructor = sessionUpdate.find(Instructor.class, id);
            updatedInstructor.setCourse(instructor.getCourse());
            updatedInstructor.setEmail(instructor.getEmail());
            updatedInstructor.setFirstName(instructor.getFirstName());
            updatedInstructor.setLastName(instructor.getLastName());
            updatedInstructor.setPhoneNumber(instructor.getPhoneNumber());
            sessionUpdate.merge(updatedInstructor);
            sessionUpdate.getTransaction().commit();
            sessionUpdate.close();
        } catch (Exception e) {
            System.out.println("not is update Instructor");
        }
    }

    @Override
    public Instructor getInstructorById(Long id) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Instructor instructor = session.find(Instructor.class, id);
            if (instructor==null){
                System.out.println("instructor is null");
            }
            session.getTransaction().commit();
            session.close();
            return instructor;
        } catch (Exception e) {
            System.out.println("getInstructorById error");
        }
        return null;
    }

    @Override
    public List<Instructor> getInstructorByCourseId(Long course_id) {
        try { Session session = sessionFactory.openSession();
            session.beginTransaction();
            Course course = session.find(Course.class, course_id);
            List<Instructor> instructors = course.getInstructors();
            session.getTransaction().commit();
            session.close();
            return instructors;
        } catch (Exception e) {
            System.out.println(" getInstructorByCourseId error");
        }
        return null;
    }

    @Override
    public void deleteInctructorById(Long id) {
        try {
            Session sessionDelect = sessionFactory.openSession();
            sessionDelect.beginTransaction();
            Instructor instructor = sessionDelect.find(Instructor.class, id);
            sessionDelect.remove(instructor);
            sessionDelect.getTransaction().commit();
            sessionDelect.close();
        } catch (Exception e) {
            System.out.println("not is delete Instructor");
        }
    }

    @Override
    public void assignInstructorToCourse(Long id, Long course_id) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Course course = session.get(Course.class, course_id);
            if (course == null) {
                System.out.println("Course is null");
            } else {
                Instructor instructor = session.get(Instructor.class,id);
                if (instructor == null){
                    System.out.println("instructor is null");
                } else {
                    instructor.getCourse().add(course);
                    session.merge(instructor);
                }
            }
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("assignInstructorToCourse error");
        }
    }
}
