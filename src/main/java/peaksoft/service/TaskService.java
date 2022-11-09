package peaksoft.service;

import peaksoft.dao.InstructorDaoIlmp;
import peaksoft.dao.TaskDao;
import peaksoft.dao.TaskDaoIlmp;
import peaksoft.entity.Task;

import java.util.List;

public class TaskService implements TaskDao {

    TaskDaoIlmp taskDaoIlmp = new TaskDaoIlmp();

    @Override
    public void saveTask(Long lesson_id, Task task) {
        taskDaoIlmp.saveTask(lesson_id,task);

    }

    @Override
    public void updateTask(Long id, Task task) {
        taskDaoIlmp.updateTask(id,task);
    }

    @Override
    public List<Task> getAllTaskLessonId(Long lessn_id) {
        return taskDaoIlmp.getAllTaskLessonId(lessn_id);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskDaoIlmp.deleteTaskById(id);

    }
}
