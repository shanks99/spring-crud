package user.userspring.repository;

import user.userspring.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findByHp(String hp);

    List<User> findAll();

    void deleteById(Long id);
}
