package ro.hacktm.oradea.epiata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.model.dto.UserDto;
import ro.hacktm.oradea.epiata.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "create")
    void createUser(UserDto userDto) {
        userService.createUser(userDto);
    }
}
