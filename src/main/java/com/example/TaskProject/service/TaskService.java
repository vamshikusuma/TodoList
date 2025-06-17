package com.example.TaskProject.service;

import com.example.TaskProject.payload.TaskDto;

import java.util.List;

public interface TaskService {

	 TaskDto createTask(int userid,TaskDto taskDto);

	 List<TaskDto> findAllTasks(int userid);

	 TaskDto findTaskById(int userId, int taskId);

	 void deleteTask(int userId, int taskId);
}
