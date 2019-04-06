package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.exception.UserNotFoundException;
import ro.hacktm.oradea.epiata.model.dto.UserDto;
import ro.hacktm.oradea.epiata.model.entity.UserDao;
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

	private static UserDao copyToDao(UserDto from, UserDao to) {
		BeanUtils.copyProperties(from, to);
		return to;
	}

	public List<UserDto> getAllUsers() {
		return repository.findAll()
				.stream()
				.filter(Objects::nonNull)
				.map(UserDao::toDto)
				.collect(Collectors.toList());
	}

	Optional<UserDao> getUserDaoById(Long id) {
		return repository.findById(id);
	}

	public UserDto getUserById(Long id) {
		Optional<UserDao> user = repository.findById(id);

		if (user.isPresent())
			return user.get().toDto();

		throw new UserNotFoundException(id);
	}

	public void createUser(UserDto userDto) {
		UserDao userEntity = copyToDao(userDto, new UserDao());

		DisplayLocationDto coordinates = externalServices.getAddressGeoCode(userDto.getAddress());
		userEntity.getLocation().setLatitude(coordinates.getLatitude());
		userEntity.getLocation().setLongitude(coordinates.getLongitude());

		repository.save(userEntity);
	}

	public void updateUser(UserDto dto) {
		Optional<UserDao> userEntity = repository.findById(dto.getId());

		if (userEntity.isPresent()) {
			UserDao user = new UserDao();
			repository.save(copyToDao(dto, user));
		} else {
			throw new UserNotFoundException(dto.getId());
		}
	}

	public void deleteUser(Long id) {
		Optional<UserDao> userEntity = repository.findById(id);

		if (userEntity.isPresent()) {
			repository.delete(userEntity.get());
		} else {
			throw new UserNotFoundException(id);
		}
	}
}
