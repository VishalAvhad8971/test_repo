package com.test.restAPI.Exam.RestAPI.Design.model;

import java.io.Serializable;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lead implements Serializable {

    @Id
    @NotNull(message = "Lead ID is mandatory")
    @Column(unique = true)
    private Integer leadId;

    @NotBlank(message = "First Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First Name should contain only alphabets")
    private String firstName;

    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Middle Name should contain only alphabets")
    private String middleName;

    @NotBlank(message = "Last Name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last Name should contain only alphabets")
    private String lastName;

    @NotNull(message = "Mobile Number is mandatory")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Mobile Number")
    private String mobileNumber;

    @NotBlank(message = "Gender is mandatory")
    @Pattern(regexp = "^(Male|Female)$", message = "Invalid Gender")
    private String gender;

    @NotNull(message = "Date of Birth is mandatory")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid Email")
    private String email;


}
