package com.hari.service;

import com.hari.model.Submission;

import java.util.List;

public interface SubmissionService {

    Submission submitTask(Long taskId,String githubLink,Long userId,String jwt) throws Exception;

    Submission getTaskSubmissionById(Long submissionId) throws Exception;

    List<Submission> getAllTaskSubmissions() throws Exception;

    List<Submission> getTaskSubmissionsByTaskId(Long taskId) throws Exception;

    Submission acceptDeclineSubmission(Long id,String status) throws Exception;

}
