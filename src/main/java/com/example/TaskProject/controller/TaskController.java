package com.example.TaskProject.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.TaskProject.payload.TaskDto;
import com.example.TaskProject.service.TaskService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskController {
	
	private final TaskService taskService;
	
	@PostMapping("/{userId}/task")
	ResponseEntity<TaskDto> createTask(@PathVariable("userId") int userId,@RequestBody TaskDto taskDto){
		
		return new ResponseEntity<>(taskService.createTask(userId, taskDto),HttpStatus.CREATED);
	}
	
	@PreAuthorize(value="ROLE_ADMIN")
	@GetMapping("{userid}/tasks")
	ResponseEntity<List<TaskDto>> getAllTask(@PathVariable(name="userid")int userid){
		return new ResponseEntity<>(taskService.findAllTasks(userid),HttpStatus.FOUND);
	}
	@GetMapping("{userid}/task/{taskId}")
	ResponseEntity<TaskDto> getTaskById(@PathVariable(name="userid")int userid,@PathVariable(name="taskId")int taskId){
		return new ResponseEntity<>(taskService.findTaskById(userid, taskId),HttpStatus.FOUND);
	}
	@DeleteMapping("{userid}/task/{taskId}")
	ResponseEntity<String> deleteTask(@PathVariable(name="userid")int userid,@PathVariable(name="taskId")int taskId){
		taskService.deleteTask(userid, taskId);
		return new ResponseEntity<>(String.format("From user %d task %d is deleted", userid,taskId),HttpStatus.OK);
	}

}
