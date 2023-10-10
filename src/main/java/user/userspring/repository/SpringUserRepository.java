package user.userspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import user.userspring.domain.User;

import java.util.Optional;

public interface SpringUserRepository extends JpaRepository<User, Long>, UserRepository {

    /* name 찾기 */
    @Override
    Optional<User> findByName(String name);

    /* email 찾기 */
    @Override
    Optional<User> findByEmail(String email);

    /* hp 찾기 */
    @Override
    Optional<User> findByHp(String hp);
}
