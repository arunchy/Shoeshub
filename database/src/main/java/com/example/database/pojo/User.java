package com.example.database.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    @Column(unique=true)
    private String email;
    private String password;
    @Column (unique = true)
    private String phoneNumber;
    private String city;
    private String state;
    private String streetAddress;
    private String profilePicture;
    private Date dateOfBirth;
    private String gender;
    private Date registrationDate;
    private Date lastLogin;
    private String userType;

}
