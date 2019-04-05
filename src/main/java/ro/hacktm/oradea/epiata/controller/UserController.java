package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

	@GetMapping(path = "/{id}")
	UserDto getUser(@PathVariable Long userId) {
		return userService.getUser(userId);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void createUser(@RequestBody UserDto userDto) {
		userService.createUser(userDto);
	}

	@PutMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void updateUser(@RequestBody UserDto dto) {
		userService.updateUser(dto);
	}

	@DeleteMapping(path = "/{id}")
	void deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
