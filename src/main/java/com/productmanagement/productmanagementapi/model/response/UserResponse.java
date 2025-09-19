package com.productmanagement.productmanagementapi.model.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class UserResponse {

    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private int age;

}
