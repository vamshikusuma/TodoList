package com.example.TaskProject.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskProject.Exception.TaskNotFound;
import com.example.TaskProject.Exception.UserNotFound;
import com.example.TaskProject.entity.Task;
import com.example.TaskProject.entity.User;
import com.example.TaskProject.payload.TaskDto;
import com.example.TaskProject.repo.TaskRepository;
import com.example.TaskProject.repo.UserRepository;
import com.example.TaskProject.service.TaskService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService{

	private final  UserRepository userRepo;

	private final TaskRepository taskRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TaskDto createTask(int userid, TaskDto taskDto) {
		// TODO Auto-generated method stub
		User user =userRepo.findById(userid).orElseThrow(()->
				new UserNotFound(String.format("user id %d is not found",userid)));
		Task task =modelMapper.map(taskDto, Task.class);
		    task.setUser(user);
		Task savedTask =taskRepo.save(task);
		return  modelMapper.map(savedTask, TaskDto.class);
	}

	@Override
	public List<TaskDto> findAllTasks(int userid) {
	userRepo.findById(userid).orElseThrow(()->
				new UserNotFound(String.format("User %d is not found",userid))
		);
		List<Task> tasks = taskRepo.findAllByUserUserId(userid);
		log.info("Tasks found for user {}: {}",tasks);
		return tasks.stream().map(task->modelMapper
				.map(task,TaskDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public TaskDto findTaskById(int userId, int taskId){
		userRepo.findById(userId).orElseThrow(()-> new UserNotFound(String.format("User %d is not found",userId)));
		Task task = taskRepo.findById(taskId).orElseThrow(()-> new TaskNotFound(String.format("Task %d is not found",taskId)));
		if(task.getUser().getUserId()==userId){
			return modelMapper.map(task, TaskDto.class);
		}
		throw new TaskNotFound(String.format("Task %d is not  belongs to this User %d",taskId ,userId));
	}
	@Override
public 	void deleteTask(int userId, int taskId){
		userRepo.findById(userId).orElseThrow(()-> new UserNotFound(String.format("User %d is not found",userId)));
		Task task = taskRepo.findById(taskId).orElseThrow(()-> new TaskNotFound(String.format("Task %d is not found",taskId)));
		if(task.getUser().getUserId()==userId){
			taskRepo.deleteById(taskId);

		}else {
		throw new TaskNotFound(String.format("Task %d is not  belongs to this User %d",taskId ,userId));}
	}
}
