package com.hari.service;

import com.hari.model.Task;
import com.hari.model.TaskStatus;

import java.util.List;

public interface TaskService {
    Task createTask(Task task,String requestRole)throws Exception;

    Task getTaskById(Long id) throws Exception;

    List<Task> getAllTask(TaskStatus status);

    Task updateTask(Long id,Task updateTask,Long userId) throws Exception;

    void deleteTask(Long id) throws Exception;

    Task assignedTask(Long userId, Long taskId) throws Exception;

    List<Task> assignedUserTask(Long userId,TaskStatus status)throws Exception;

    Task completedTask(Long taskId)throws Exception;
}
