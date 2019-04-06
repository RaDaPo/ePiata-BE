package ro.hacktm.oradea.epiata.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.hacktm.oradea.epiata.model.dto.UserDto;

@CrossOrigin
@RequestMapping("/api/users")
public interface UsersApi {

	@GetMapping
	ResponseEntity getAllUsers();

	@GetMapping(path = "/{id}")
	ResponseEntity getUser(@PathVariable("id") Long userId);

	@PostMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	ResponseEntity createUser(@RequestBody UserDto userDto);

	@PutMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	ResponseEntity updateUser(@RequestBody UserDto dto);

	@DeleteMapping(path = "/{id}")
	ResponseEntity deleteUser(@PathVariable Long id);
}
