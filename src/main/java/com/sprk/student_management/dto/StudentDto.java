package com.sprk.student_management.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {


    private int rollNo;

    @NotBlank(message = "First Name Cannot Have Empty Space or Blank")
    private String firstName;

    @NotBlank(message = "Last Name Cannot Have Empty Space or Blank")
    private String lastName;

    @Min(value = 5, message = "Ensure age must be greater than 5")
    @Max(value = 50, message = "Ensure age must be below 50")
    private int age;


    private String gender;

    @NotBlank(message = "Address Cannot Be Empty")
    private String address;

    @Min(value = 0, message = "Percentage should be between 0 to 100")
    @Max(value = 100, message = "Percentage should be between 0 to 100")
    private double percentage;


    @Email(message = "Please enter correct email")
    @NotBlank(message = "Email Cannot Be Empty")
    private String email;
}
