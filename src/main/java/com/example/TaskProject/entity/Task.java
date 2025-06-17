package com.example.TaskProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String taskName;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;
}
