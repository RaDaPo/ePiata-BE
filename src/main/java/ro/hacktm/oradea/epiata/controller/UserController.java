package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.model.dto.UserDto;
import ro.hacktm.oradea.epiata.service.UserService;

import java.util.List;

@RestController
@SuppressWarnings("unused")
@RequestMapping(path = "/api/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping
    List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "create")
    void createUser(UserDto userDto) {
        userService.createUser(userDto);
    }
}
