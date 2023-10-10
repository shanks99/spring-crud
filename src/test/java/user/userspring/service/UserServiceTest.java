package user.userspring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import user.userspring.domain.User;
import user.userspring.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
//    @Commit
    void join() {
        // given
        User user = new User();
        user.setName("성춘향");
        user.setPw("2222");
        user.setEmail("aaa@gmail.com");
        user.setHp("01022223333");
        user.setAddress("서울시 OO구");

        // when
        Long saveId = userService.join(user);

        // then
        User findUser = userService.findOne(saveId).get();
        assertThat(user.getName()).isEqualTo(findUser.getName());
    }

    @Test
    void dupCheckUser() {
        // given
        User user1 = new User();
        user1.setName("성춘향");
        user1.setPw("2222");
        user1.setEmail("aaa@gmail.com");
        user1.setHp("01022223333");
        user1.setAddress("서울시 OO구");

        User user2 = new User();
        user2.setName("성춘향");
        user2.setPw("3333");
        user2.setEmail("bbb@gmail.com");
        user2.setHp("01022223333");
        user2.setAddress("서울시 OO구");

        // when
        userService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.join(user2));

        assertThat(e.getMessage()).isEqualTo("Dup : 이미 존재하는 회원 입니다");

        // then
    }

    @Test
    @Commit
    void update() {
        User user = new User();

        user.setId(32L);
        user.setName("A성춘향");
        user.setPw("A3333");
        user.setEmail("Abbb@gmail.com");
        user.setHp("A01022223333");
        user.setAddress("A서울시 OO구");

        userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패 하였습니다.");
        });

        userRepository.save(user);
    }

    @Test
    void delete() {
        Long id = 32L;

        userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("삭제에 실패 하였습니다.");
        });

        userRepository.deleteById(id);
    }
}