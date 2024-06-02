package com.hari.service.impl;

import com.hari.model.Submission;
import com.hari.model.TaskDto;
import com.hari.repository.SubmissionRepository;
import com.hari.service.SubmissionService;
import com.hari.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private TaskService taskService;

    @Override
    public Submission submitTask(Long taskId, String githubLink, Long userId,String jwt) throws Exception {
        TaskDto task=taskService.getTaskById(taskId,jwt);
        if(task!=null){
            Submission submission=new Submission();
            submission.setTaskId(task.getId());
            submission.setGithubLink(githubLink);
            submission.setUserId(userId);
            submission.setSubmissionTime(LocalDateTime.now());
            return submissionRepository.save(submission);
        }
       throw new Exception("Task not found with id "+taskId);
    }

    @Override
    public Submission getTaskSubmissionById(Long submissionId) throws Exception {
        return submissionRepository.findById(submissionId).orElseThrow(()->new Exception("Task not found with id "+submissionId));
    }

    @Override
    public List<Submission> getAllTaskSubmissions() throws Exception {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getTaskSubmissionsByTaskId(Long taskId) throws Exception {
        return submissionRepository.findByTaskId(taskId);
    }

    @Override
    public Submission acceptDeclineSubmission(Long id, String status) throws Exception {
        Submission submission=getTaskSubmissionById(id);
        submission.setStatus(status);
        if(status.equals("ACCEPT")){
            taskService.completeTask(submission.getTaskId());
        }

        return submissionRepository.save(submission);
    }
}
