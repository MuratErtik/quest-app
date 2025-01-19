package com.project.questapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class USer {
    @Id
    Long id;

    String username;
    String password;
}
