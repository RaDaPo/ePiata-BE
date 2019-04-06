package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.api.UsersApi;
import ro.hacktm.oradea.epiata.model.dto.UserDto;
import ro.hacktm.oradea.epiata.service.UserService;

import static ro.hacktm.oradea.epiata.utility.Utility.getResponseEntityOk;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController implements UsersApi {

	private final UserService userService;

	public ResponseEntity getAllUsers() {
		return getResponseEntityOk(userService.getAllUsers());
	}

	public ResponseEntity getUser(Long id) {
		return getResponseEntityOk(userService.getUserById(id));
	}

	public ResponseEntity createUser(UserDto userDto) {
		userService.createUser(userDto);
		return getResponseEntityOk();
	}

	public ResponseEntity updateUser(UserDto dto) {
		userService.updateUser(dto);
		return getResponseEntityOk();
	}

	public ResponseEntity deleteUser(Long id) {
		userService.deleteUser(id);
		return getResponseEntityOk();
	}
}
