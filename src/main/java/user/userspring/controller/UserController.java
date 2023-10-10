package user.userspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import user.userspring.domain.User;
import user.userspring.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* 회원 메인 */
    @GetMapping("/")
    public String home() {
        return "/user/index";
    }

    /* 회원가입 - View */
    @GetMapping("/user/join")
    public String join() {
        return "/user/join";
    }

    /* 회원가입 - Process */
    @PostMapping("/user/create")
    public String create(UserForm form) {
        User user = new User();

        user.setName(form.getName());
        user.setPw(form.getPw());
        user.setEmail(form.getEmail());
        user.setHp(form.getHp());
        user.setAddress(form.getAddress());

        userService.join(user);

        return "redirect:/";
    }

    /* 회원List */
    @GetMapping("/user/list")
    public String list(Model model) {
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "/user/list";
    }

    /* 회원 View */
    @GetMapping("/user/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        Optional<User> user = userService.findOne(id);
        model.addAttribute("user", user.get());
        return "/user/view";
    }

    /* 회원 수정 - view */
    @GetMapping("/user/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        Optional<User> user = userService.findOne(id);
        model.addAttribute("user", user.get());
        return "/user/update";
    }

    /* 회원 수정 - Process */
    @PutMapping("/user/update")
    public String update(User user) {
        userService.update(user);

        return "redirect:/user/list";
    }

    /* 회원 삭제 */
    @DeleteMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);

        return "redirect:/user/list";
    }
}
