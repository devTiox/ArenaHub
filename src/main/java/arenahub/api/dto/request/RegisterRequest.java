package arenahub.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record RegisterRequest(
        @Email
        @NotBlank
        String email,
        @Email
        @NotBlank
        String contactEmail,
        @NotBlank
        String name,
        @NotBlank
        String phone,
        @NotBlank
        String password
) {}
