package cs590proj2.authservice.model;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
