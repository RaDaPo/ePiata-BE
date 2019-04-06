package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.UserException;
import ro.hacktm.oradea.epiata.model.dto.UserDto;
import ro.hacktm.oradea.epiata.model.entity.UserDao;
import ro.hacktm.oradea.epiata.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

	private final UserRepository repository;

	public List<UserDto> getAllUsers() {
		List<UserDao> entity = repository.findAll();
		if (!entity.isEmpty()) {
			return entity.stream().map(UserDao::toDto).collect(Collectors.toList());
		} else {
			throw new UserException("Users not found exception");
		}
	}

	public Optional<UserDao> getUserById(Long id) {
		return repository.findById(id);
	}

	public void save(UserDao user) {
		repository.save(user);
	}

	public void createUser(UserDto userDto) {
		UserDao userEntity = new UserDao();
		BeanUtils.copyProperties(userDto, userEntity);
		repository.save(userEntity);
	}

	public UserDto getUser(Long userId) {
		Optional<UserDao> user = repository.findById(userId);
		if (user.isPresent()) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user.get(), userDto);
			return userDto;
		} else {
			throw new UserException("User not found exception");
		}
	}

	public void updateUser(UserDto dto) {
		Optional<UserDao> userEntity = repository.findById(dto.getId());
		if (userEntity.isPresent()) {
			UserDao user = new UserDao();
			BeanUtils.copyProperties(dto, user);
			repository.save(user);
		} else {
			throw new UserException("User cannot be updated exception");
		}
	}

	public void deleteUser(Long id) {
		Optional<UserDao> userEntity = repository.findById(id);
		if (userEntity.isPresent()) {
			repository.delete(userEntity.get());
		} else {
			throw new UserException("User cannot be deleted exception");
		}
	}
}
