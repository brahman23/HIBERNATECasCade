package peaksoft;

import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;
import peaksoft.service.LessonService;
import peaksoft.service.TaskService;
import peaksoft.util.DBUtil;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        DBUtil.creatSessionFactory();
        InstructorService instructorService = new InstructorService();
        CourseService courseService = new CourseService();
        LessonService lessonService = new LessonService();
        TaskService taskService = new TaskService();

        Course course = new Course("Java","SSD", "2022.01.01","Link","HHD");
        Course course2 = new Course("JS","JSD","2022.01.01" ,"Link","JSHD");
        Course course3 = new Course("Kava","KSD","2022.01.01","Link","KHD");
        courseService.saveCourse(course2);
        System.out.println(courseService.getallCourse());
        courseService.getCourseByName("JS");
        courseService.saveCourse(course3);

        Instructor instructor = new Instructor("Maha","Islamidinov","maha@gmail.com","0774121212");
        Instructor instructor2 = new Instructor("Baha","Bslamidinov","Baha@gmail.com","0774121213");
        Instructor instructor3 = new Instructor("Taha","Tslamidinov","Taha@gmail.com","0774121214");
        instructorService.deleteInctructorById(2l);
        instructorService.assignInstructorToCourse(1l,1l);
        instructorService.saveInstructor(instructor);
        instructorService.saveInstructor(instructor2);
        instructorService.saveInstructor(instructor3);
        instructorService.assignInstructorToCourse(3l,1l);
        System.out.println(instructorService.getInstructorByCourseId(2l));


        Lesson lesson = new Lesson("freeLesson","6min");
        Lesson lesson2 = new Lesson("2freeLesson","45min");
        Lesson lesson3 = new Lesson("3freeLesson","50min");
        lessonService.saveLesson(1l,lesson2);
        lessonService.saveLesson(2l,lesson);

        lessonService.getLessonByCourseId(1l);
        lessonService.getLessonById(2l);
        lessonService.updateLesson(3l,lesson3);


        Task task = new Task("oop","2022.12.01","OOP");
        Task task2 = new Task("oop","2022.11.01","OOP");
        Task task3 = new Task("oop","2021.12.01","OOP");
        taskService.saveTask(1l,task3);
        taskService.saveTask(1l,task);
        taskService.saveTask(2l,task2);
        taskService.deleteTaskById(1L);
        System.out.println(taskService.getAllTaskLessonId(1L));
        taskService.updateTask(2l,task);





















    }





}
