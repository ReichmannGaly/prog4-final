package school.hei.prog4.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import school.hei.prog4.entity.UserEntity;
import school.hei.prog4.service.UserService;

@Controller
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user", new UserEntity());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") UserEntity user){
        userService.createUser(user);
        return "login";
    }
}
