package peaksoft.service;

import peaksoft.dao.CourseDao;
import peaksoft.dao.CourseDaoIlmp;
import peaksoft.entity.Course;

import java.util.List;

public class CourseService implements CourseDao {

    CourseDaoIlmp courseDaoIlmp = new CourseDaoIlmp();
    @Override
    public void saveCourse(Course course) {
        courseDaoIlmp.saveCourse(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseDaoIlmp.getCourseById(id);
    }

    @Override
    public List<Course> getallCourse() {
        return courseDaoIlmp.getallCourse();
    }

    @Override
    public void updateCourse(Long id, Course course) {
        courseDaoIlmp.updateCourse(id,course);
    }

    @Override
    public void deleteCourseById(Long id) {
        courseDaoIlmp.deleteCourseById(id);
    }

    @Override
    public Course getCourseByName(String name) {
        return courseDaoIlmp.getCourseByName(name);
    }
}
