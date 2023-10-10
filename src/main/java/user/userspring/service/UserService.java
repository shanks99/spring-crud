package user.userspring.service;

import org.springframework.transaction.annotation.Transactional;
import user.userspring.domain.User;
import user.userspring.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /* 회원가입 검증 */
    public Long join(User user) {
        dupCheckUser(user);
        userRepository.save(user);
        return user.getId();
    }

    /* 중복 name 검증 */
    public void dupCheckUser(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재 하는 이름 입니다");
                });
    }

    /* 단일 회원 정보 */
    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }
    
    /* 회원 내역 */
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    /* 회원 수정 */
    public Long update(User user) {
        userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패 하였습니다.");
        });

        userRepository.save(user);
        return user.getId();
    }

    /* 회원 삭제 */
    public void delete(Long id) {
        userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("삭제에 실패 하였습니다.");
        });

        userRepository.deleteById(id);
    }
}
