package ro.hacktm.oradea.epiata.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return entity.stream().map(User::toDto).collect(Collectors.toList());
    }

    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    public void save(User user) {
        repository.save(user);
    }

    public void createUser(UserDto userDto) {
        User userEntity = new User();
        BeanUtils.copyProperties(userDto, userEntity);
        repository.save(userEntity);
    }

}
