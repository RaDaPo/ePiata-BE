package ro.hacktm.oradea.epiata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.model.dto.UserDto;
import ro.hacktm.oradea.epiata.model.entity.User;
import ro.hacktm.oradea.epiata.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDto> getAllUsers() {
        List<User> entity = repository.findAll();
        return entity.stream().map(User::toDto).collect(Collectors.toList());
    }
}
