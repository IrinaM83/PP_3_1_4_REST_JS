package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String printUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.printUsers());
        model.addAttribute("allRoles", roleService.printRole());
        return "users";
    }

    @GetMapping
    public String pageAdmin(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.printUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.printRole());
        return "admin";
    }

    @GetMapping("/users/new")
    public String newUser(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("listRoles", roleService.printRole());
        return "new";
    }

    @PostMapping("/users/new")
    public String create(@ModelAttribute("user") User user) {
        roleService.getUserRoles(user);
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/getUserById")
    @ResponseBody
    public User getUserById(@PathParam("id") Long id) {
        return userService.getById(id);

    }

    @PostMapping(value = "/users/edit")
    public String update(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("listRoles", roleService.printRole());
        roleService.getUserRoles(user);
        userService.update(user);
        return "redirect:/admin/users";
    }

    @RequestMapping("/users/delete")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
