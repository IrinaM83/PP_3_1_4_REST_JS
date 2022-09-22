package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String printUsers(Model model) {
        model.addAttribute("user", userService.printUsers());
        return "users";
    }

    @GetMapping("/users/user_info/{id}")
    public String user(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user_info";
    }
    @GetMapping
    public String pageAdmin(Principal principal, Model model) {
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("user", user);
        return "admin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PostMapping("/users/edit/{id}")//
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(user);
        return "redirect:/admin/users";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.delete(user);
        return "redirect:/admin/users";
    }
}
