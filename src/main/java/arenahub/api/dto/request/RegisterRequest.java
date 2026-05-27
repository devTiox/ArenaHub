package arenahub.api.dto.request;

public record RegisterRequest(
        String email,
        String contactEmail,
        String name,
        String phone,
        String password
) {}
