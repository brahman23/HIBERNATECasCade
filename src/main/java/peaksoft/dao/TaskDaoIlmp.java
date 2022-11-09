package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.util.DBUtil;

import java.util.List;

public class TaskDaoIlmp implements TaskDao {
    private final SessionFactory sessionFactory = DBUtil.creatSessionFactory();

    @Override
    public void saveTask(Long lesson_id, Task task) {
        try {
            Session sessionSave = sessionFactory.openSession();
            sessionSave.getTransaction().begin();
            Lesson lesson = sessionSave.find(Lesson.class,lesson_id);
            task.setLesson(lesson);
            lesson.addTasksToLesson(task);
            sessionSave.merge(lesson);
            sessionSave.getTransaction().commit();
            sessionSave.close();
        }catch (Exception e) {
            System.out.println("not is saved Task");
        }
    }

    @Override
    public void updateTask(Long id, Task task) {
        try {
            Session sessionUpdate = sessionFactory.openSession();
            sessionUpdate.getTransaction().begin();
            Task updatedTask = sessionUpdate.get(Task.class, id);
            if (updatedTask == null) {
                System.out.println("Task is null");
            } else {
                updatedTask.setName(task.getName());
                updatedTask.setTask(task.getTask());
                updatedTask.setDeadLine(task.getDeadLine());
                sessionUpdate.merge(updatedTask);
                sessionUpdate.getTransaction().commit();
                sessionUpdate.close();
            }
        } catch (Exception e) {
            System.out.println("not is update Task ");
        }
    }

    @Override
    public List<Task> getAllTaskLessonId(Long lessn_id) {
        try {
            Session session = sessionFactory.openSession();
            session.getTransaction().begin();
            Lesson lesson = session.find(Lesson.class,lessn_id);
            List<Task> taskList = lesson.getTasks();
            session.getTransaction().commit();
            session.close();
            return taskList;
        } catch (Exception exception) {
            System.out.println("getAllTaskLessonId error");
        }
        return null;
    }

    @Override
    public void deleteTaskById(Long id) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Task task = session.get(Task.class,id);
            task.setLesson(null);
            session.remove(task);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("not is delete TASK");
        }
    }
}
