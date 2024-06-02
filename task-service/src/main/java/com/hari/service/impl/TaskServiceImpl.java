package com.hari.service.impl;

import com.hari.model.Task;
import com.hari.model.TaskStatus;
import com.hari.repository.TaskRepository;
import com.hari.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    public TaskRepository taskRepository;

    @Override
    public Task createTask(Task task, String requestRole) throws Exception {
        if (!requestRole.equals("ROLE_ADMIN")){
            throw new Exception("Only admin can create task");

        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(()-> new Exception("Task not found id"+id));
    }

    @Override
    public List<Task> getAllTask(TaskStatus status) {
        List<Task> allTask=taskRepository.findAll();
        return allTask.stream().filter(
                task -> status==null||task.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());
    }

    @Override
    public Task updateTask(Long id, Task updateTask, Long userId) throws Exception {
        Task existingTask = getTaskById(id);
        if (updateTask.getTitle()!=null) existingTask.setTitle(updateTask.getTitle());

        if (updateTask.getDescription()!=null) existingTask.setDescription(updateTask.getDescription());

        if (updateTask.getDeadline()!=null)existingTask.setDeadline(updateTask.getDeadline());

        if(updateTask.getImage()!=null)existingTask.setImage(updateTask.getImage());

        if (updateTask.getStatus()!=null)existingTask.setStatus(updateTask.getStatus());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) throws Exception {
        getTaskById(id);
        taskRepository.deleteById(id);


    }

    @Override
    public Task assignedTask(Long userId, Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setAssigneeUserId(userId);
        task.setStatus(TaskStatus.DONE);


        return taskRepository.save(task);
    }

    @Override
    public List<Task> assignedUserTask(Long userId, TaskStatus status) throws Exception {
        List<Task> allTask=taskRepository.findByAssigneeUserId(userId);
        return allTask.stream().filter(
                task -> status==null||task.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());
    }

    @Override
    public Task completedTask(Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setStatus(TaskStatus.DONE);
        return taskRepository.save(task);
    }
}
