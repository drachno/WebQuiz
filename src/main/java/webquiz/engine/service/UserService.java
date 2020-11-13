package webquiz.engine.service;

import webquiz.engine.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<User> addUser(User user);
}
