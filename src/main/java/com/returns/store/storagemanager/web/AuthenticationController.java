package com.returns.store.storagemanager.web;

import com.returns.store.storagemanager.model.bindings.UserRegistrationBinding;
import com.returns.store.storagemanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegistrationBinding userRegistrationBinding, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()
                || !userRegistrationBinding.getPassword().equals(userRegistrationBinding.getConfirmPassword())
                || this.userService.isUserExists(userRegistrationBinding)) {
            redirectAttributes.addFlashAttribute("userRegistrationBinding", userRegistrationBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBinding", bindingResult);

            return "redirect:register";
        }
        this.userService.registerAndLogin(userRegistrationBinding);

        return "redirect:/";
    }

    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                              RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/user/login";
    }

    @ModelAttribute
    private UserRegistrationBinding registrationBinding() {
        return new UserRegistrationBinding();
    }
}
