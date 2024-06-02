package com.hari.controller;

import com.hari.model.Task;
import com.hari.model.TaskStatus;
import com.hari.model.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {
    @GetMapping("/tasks")
    public ResponseEntity<String > getAssignedUserTask() throws Exception {

        return  new ResponseEntity<>("Welcome to task service", HttpStatus.OK);

    }
}
