package com.productmanagement.productmanagementapi.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @NotBlank(message = "Customer name is required")
    @Schema(example = "rota")
    private String customerName;

    @Pattern(regexp = "^[0-9]{8,15}$", message = "Phone number must be 8-15 digits")
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date of birth must be in the past")
    @Schema(example = "2000-09-22")
    private LocalDate dob;

}
