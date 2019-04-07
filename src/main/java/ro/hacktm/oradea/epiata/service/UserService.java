package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.exception.UserNotFoundException;
import ro.hacktm.oradea.epiata.model.dto.UserDto;
import ro.hacktm.oradea.epiata.model.entity.User;
import ro.hacktm.oradea.epiata.model.external_services.DisplayLocationDto;
import ro.hacktm.oradea.epiata.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

	private final UserRepository repository;
	private final ExternalServices externalServices;

	private static User copyToDao(UserDto from, User to) {
		BeanUtils.copyProperties(from, to);
		return to;
	}

	public List<UserDto> getAllUsers() {
		return repository.findAll()
				.stream()
				.filter(Objects::nonNull)
				.map(User::toDto)
				.collect(Collectors.toList());
	}

	Optional<User> getUserDaoById(Long id) {
		return repository.findById(id);
	}

	public UserDto getUserById(Long id) {
		Optional<User> user = repository.findById(id);

		if (user.isPresent())
			return user.get().toDto();

		throw new UserNotFoundException(id);
	}

	public void createUser(UserDto userDto) {
		User userEntity = copyToDao(userDto, new User());

		DisplayLocationDto coordinates = externalServices.getAddressGeoCode(userDto.getAddress());
		userEntity.getLocation().setLatitude(coordinates.getLatitude());
		userEntity.getLocation().setLongitude(coordinates.getLongitude());

		repository.save(userEntity);
	}

	public void updateUser(UserDto dto) {
		Optional<User> userEntity = repository.findById(dto.getId());

		if (userEntity.isPresent()) {
			User user = new User();
			repository.save(copyToDao(dto, user));
		} else {
			throw new UserNotFoundException(dto.getId());
		}
	}

	public void deleteUser(Long id) {
		Optional<User> userEntity = repository.findById(id);

		if (userEntity.isPresent()) {
			repository.delete(userEntity.get());
		} else {
			throw new UserNotFoundException(id);
		}
	}
}
