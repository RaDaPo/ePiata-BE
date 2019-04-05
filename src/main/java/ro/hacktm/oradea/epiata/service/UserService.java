package ro.hacktm.oradea.epiata.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.UserException;
import ro.hacktm.oradea.epiata.model.dto.UserDto;
import ro.hacktm.oradea.epiata.model.entity.User;
import ro.hacktm.oradea.epiata.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDto> getAllUsers() {
        List<User> entity = repository.findAll();
        if (!entity.isEmpty()) {
            return entity.stream().map(User::toDto).collect(Collectors.toList());
        } else {
            throw new UserException("Users not found exception");
        }
    }

    public void createUser(UserDto userDto) {
        User userEntity = new User();
        BeanUtils.copyProperties(userDto, userEntity);
        repository.save(userEntity);
    }

    public UserDto getUser(Long userId) {
        Optional<User> user = repository.findById(userId);
        if (user.isPresent()) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user.get(), userDto);
            return userDto;
        } else {
            throw new UserException("User not found exception");
        }
    }

    public void updateUser(UserDto dto) {
        Optional<User> userEntity = repository.findById(dto.getId());
        if (userEntity.isPresent()) {
            User user = new User();
            BeanUtils.copyProperties(dto, user);
            repository.save(user);
        } else {
            throw new UserException("User cannot be updated exception");
        }
    }

    public void deleteUser(Long id) {
        Optional<User> userEntity = repository.findById(id);
        if (userEntity.isPresent()) {
            repository.delete(userEntity.get());
        } else {
            throw new UserException("User cannot be deleted exception");
        }
    }
}
