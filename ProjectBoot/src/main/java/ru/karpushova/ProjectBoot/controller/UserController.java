package ru.karpushova.ProjectBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.karpushova.ProjectBoot.model.User;
import ru.karpushova.ProjectBoot.service.UserService;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "showUsers";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/all";
    }

    @PostMapping("/pages")
    public String create(@ModelAttribute("user") User user) throws Exception {
        userService.saveUser(user);
        return "redirect:/all";

    }

    @GetMapping("/{id}/editUser")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @GetMapping("views/{id}")
    public String find(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "findUser";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) throws Exception {
        userService.updateUser(user);
        return "redirect:/all";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        ex.printStackTrace(); // Можно вывести ошибку в лог

        String errorMessage = ex.getMessage();
        model.addAttribute("errorMessage", errorMessage);

        return "error";
    }
}
