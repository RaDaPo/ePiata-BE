package ro.hacktm.oradea.epiata.apis;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.hacktm.oradea.epiata.model.dto.UserDto;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/users")
public interface UsersApi {

	@GetMapping
	List<UserDto> getAllUsers();

	@GetMapping(path = "/{id}")
	UserDto getUser(@PathVariable("id") Long userId);

	@PostMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void createUser(@RequestBody UserDto userDto);

	@PutMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void updateUser(@RequestBody UserDto dto);

	@DeleteMapping(path = "/{id}")
	void deleteUser(@PathVariable Long id);
}
