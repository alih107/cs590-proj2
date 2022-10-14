package cs590proj2.authservice.model;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String email;
    private String shippingAddress;
    private String paymentMethod;

    private int roleId;
}
