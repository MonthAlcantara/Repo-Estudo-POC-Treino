package io.github.monthalcantara.EstudoMongoDB.controller;

import io.github.monthalcantara.EstudoMongoDB.dto.request.CadastraUserDto;
import io.github.monthalcantara.EstudoMongoDB.dto.response.UserDto;
import io.github.monthalcantara.EstudoMongoDB.model.User;
import io.github.monthalcantara.EstudoMongoDB.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody CadastraUserDto userDto) {

        User user = userDto.toModel();

        User userSalvo = repository.save(user);

        UserDto response = userSalvo.toResponse();

        return status(HttpStatus.CREATED).body(response);


    }

    @Transactional
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {

        List<User> users = repository.findAll();

        List<UserDto> userDtos = users.stream().map(User::toResponse).collect(Collectors.toList());

        return status(HttpStatus.OK).body(userDtos);


    }

    @Transactional
    @GetMapping("{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {

        Optional<User> optionalUser = repository.findById(id);

        if (optionalUser.isPresent()) {
            return new ResponseEntity(optionalUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
