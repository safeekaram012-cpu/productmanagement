package com.example.ProductManagement.Entity;

import jakarta.persistence.*;
import lombok.Data;

import com.example.ProductManagement.Enum.Role;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
