package cs590proj2.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String email;
    private String shippingAddress;
    private String paymentMethod;

    @ManyToOne
    private Role role;
}
