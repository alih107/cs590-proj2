package cs590proj2.authservice.service;

import cs590proj2.authservice.entity.User;
import cs590proj2.authservice.model.LoginDto;
import cs590proj2.authservice.model.LoginResponseDto;
import cs590proj2.authservice.model.RegisterDto;
import cs590proj2.authservice.model.VerifyDto;
import cs590proj2.authservice.repo.RoleRepo;
import cs590proj2.authservice.repo.UserRepo;
import cs590proj2.authservice.security.JwtHelper;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    @Override
    public ResponseEntity<LoginResponseDto> login(LoginDto loginDto) {
        String username = loginDto.getUsername();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                            loginDto.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        User user = userRepo.findByUsername(username);
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("username", username);
        claims.put("user_id", user.getId());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("email", user.getEmail());
        claims.put("shippingAddress", user.getShippingAddress());
        claims.put("paymentMethod", user.getPaymentMethod());
        claims.put("role", user.getRole().getName());
        return ResponseEntity.ok().body(new LoginResponseDto(jwtHelper.generateToken(claims)));
    }

    @Override
    public User register(RegisterDto registerDto) {
        User user = new User(
                0,
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                registerDto.getFirstName(),
                registerDto.getLastName(),
                registerDto.getEmail(),
                registerDto.getShippingAddress(),
                registerDto.getPaymentMethod(),
                roleRepo.findById(registerDto.getRoleId()).get());
        return userRepo.save(user);
    }

    @Override
    public ResponseEntity<VerifyDto> verify(String authorizationHeader) {
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            try {
                Claims claims = jwtHelper.getClaims(token);
                if (claims != null) {
                    VerifyDto verifyDto = new VerifyDto(
                            true,
                            claims.get("user_id", Integer.class),
                            claims.get("username", String.class),
                            claims.get("firstName", String.class),
                            claims.get("lastName", String.class),
                            claims.get("email", String.class),
                            claims.get("shippingAddress", String.class),
                            claims.get("paymentMethod", String.class),
                            claims.get("role", String.class)

                    );
                    return ResponseEntity.ok().body(verifyDto);
                }
            } catch (Exception ignored){}

        }

        return ResponseEntity.ok().body(new VerifyDto(false));
    }
}
