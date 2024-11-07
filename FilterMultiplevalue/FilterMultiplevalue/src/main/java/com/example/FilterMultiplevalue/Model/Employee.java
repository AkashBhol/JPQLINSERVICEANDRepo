package com.example.FilterMultiplevalue.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    private String name;

    private String department;

    private String minage;

    private String maxAge;

    private String role;
}
