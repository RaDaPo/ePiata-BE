package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.model.dto.UserDto;
import ro.hacktm.oradea.epiata.model.entity.User;
import ro.hacktm.oradea.epiata.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository repository;

    public List<UserDto> getAllUsers() {
        List<User> entity = repository.findAll();
        return entity.stream().map(User::toDto).collect(Collectors.toList());
    }

    public void createUser(UserDto userDto) {
        User userEntity = new User();
        BeanUtils.copyProperties(userDto, userEntity);
        repository.save(userEntity);
    }

}
