package com.wcc.cricket;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class Player {
    @Id
    @GeneratedValue
    public Integer id;
    @Column(nullable = true)
    public String firstName;
    @Column(nullable = true)
    public String lastName;
    @Column(nullable = true)
    public String email;
    @Column(nullable = true)
    public String phoneNumber;
}
