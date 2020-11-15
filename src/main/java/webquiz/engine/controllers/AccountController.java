package webquiz.engine.controllers;

import webquiz.engine.entity.User;
import webquiz.engine.repository.UserRepository;
import webquiz.engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserRepository userRepository;

    @PostMapping(path = "/api/register", consumes = "application/json")
    public ResponseEntity<User> registerUser(@Valid @NotNull @RequestBody User user) {
       return userService.addUser(user);
    }

}