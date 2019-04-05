package ro.hacktm.oradea.epiata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(path = "/{id}")
    UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping(path = "create")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void createUser(UserDto userDto) {
        userService.createUser(userDto);
    }

    @PutMapping(path = "/update")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void updateUser(@RequestBody UserDto dto) {
        userService.updateUser(dto);
    }

    @DeleteMapping(path = "/delete/{id}")
    void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
