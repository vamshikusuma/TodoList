package com.example.TaskProject.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table( uniqueConstraints = {@UniqueConstraint(columnNames = {"userEmail"})})
public class User {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int userId;
@Column(nullable =false)
private String userName;
@Column(nullable =false)
private String userEmail;
@Column(nullable =false)
private String userPassword;
}
