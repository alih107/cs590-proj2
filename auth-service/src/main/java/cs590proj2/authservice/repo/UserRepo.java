package cs590proj2.authservice.repo;

import cs590proj2.authservice.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
