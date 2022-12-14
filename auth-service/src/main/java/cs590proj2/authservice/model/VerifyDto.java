package cs590proj2.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyDto {

    public VerifyDto(boolean success) {
        this.success = success;
    }

    private boolean success;
    private int userId;
    private String username;

    private String firstName;
    private String lastName;
    private String email;
    private String shippingAddress;
    private String paymentMethod;
    private String role;
}
