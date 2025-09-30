package com.productmanagement.productmanagementapi.model.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class CustomerResponse {

    private long id;
    private String customerName;
    private String phoneNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private int age;

}
