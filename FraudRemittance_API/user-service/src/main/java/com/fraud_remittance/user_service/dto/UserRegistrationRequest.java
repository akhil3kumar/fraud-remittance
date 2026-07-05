package com.fraud_remittance.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserRegistrationRequest(

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email,

        @Pattern(
                regexp = "^[0-9]{10}$",
                message = "Phone number must contain 10 digits")
        String phoneNumber,

        @Size(min = 8, message = "Password must be at least 8 characters")
        String password

) {}
