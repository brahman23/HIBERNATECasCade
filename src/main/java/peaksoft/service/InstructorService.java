package peaksoft.service;

import peaksoft.dao.CourseDao;
import peaksoft.dao.InstructorDao;
import peaksoft.dao.InstructorDaoIlmp;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;

import java.util.List;

public class InstructorService implements InstructorDao {
    InstructorDaoIlmp instructorDaoIlmp = new InstructorDaoIlmp();


    @Override
    public void saveInstructor(Instructor instructor) {
        instructorDaoIlmp.saveInstructor(instructor);
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        instructorDaoIlmp.updateInstructor(id,instructor);
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorDaoIlmp.getInstructorById(id);
    }

    @Override
    public List<Instructor> getInstructorByCourseId(Long course_id) {
        return instructorDaoIlmp.getInstructorByCourseId(course_id);
    }

    @Override
    public void deleteInctructorById(Long id) {
        instructorDaoIlmp.deleteInctructorById(id);
    }

    @Override
    public void assignInstructorToCourse(Long id, Long course_id) {
        instructorDaoIlmp.assignInstructorToCourse(id,course_id);
    }
}
