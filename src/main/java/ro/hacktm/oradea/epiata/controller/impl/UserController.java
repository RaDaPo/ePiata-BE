package ro.hacktm.oradea.epiata.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.controller.UsersApi;
import ro.hacktm.oradea.epiata.model.dto.UserDto;
import ro.hacktm.oradea.epiata.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController implements UsersApi {

	private final UserService userService;

	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}

	public UserDto getUser(Long userId) {
		return userService.getUser(userId);
	}

	public void createUser(UserDto userDto) {
		userService.createUser(userDto);
	}

	public void updateUser(UserDto dto) {
		userService.updateUser(dto);
	}

	public void deleteUser(Long id) {
		userService.deleteUser(id);
	}
}
