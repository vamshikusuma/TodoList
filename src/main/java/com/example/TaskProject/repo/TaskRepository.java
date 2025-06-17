package com.example.TaskProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.TaskProject.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>{
    List<Task> findAllByUserUserId(int userid);
}
