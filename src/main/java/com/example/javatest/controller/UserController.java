package com.example.javatest.controller;

import com.example.javatest.dto.UserResponseDTO;
import com.example.javatest.model.Phone;
import com.example.javatest.model.User;
import com.example.javatest.repository.UserRepository;
import com.example.javatest.service.JwtService;
import com.example.javatest.service.PhoneService;
import com.example.javatest.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private JwtService jwtService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        // TODO agregar a validaciones
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                String field = error.getField();
                String message = error.getDefaultMessage();
                errors.put(field, message);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        // Valida si el correo ya existe
        if(userRepository.existsByEmail(user.getEmail())) {
            Map<String, String> error = new HashMap<>();
            error.put("email", "El correo ya existe");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        List<Phone> phones = user.getPhones();  // Guardar los teléfonos temporalmente
        user.setPhones(null);

        // Generar token
        String token =  jwtService.generateToken(user);
        user.setToken(token);
        // Guarda sólo al usuario para obtener uuid
        User createdUser = userService.createUser(user);

        if (phones != null && !phones.isEmpty()) {
            for (Phone phone : phones) {
                System.out.println("Procesando teléfono: " + phone.getNumber());
                phone.setUser(createdUser);  // Asocia el teléfono al usuario con uuid
                phoneService.createPhone(phone);  // Guarda el teléfono
            }
        }

        // Crea el DTO de respuesta
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(createdUser.getId());
        userResponseDTO.setName(createdUser.getName());
        userResponseDTO.setEmail(createdUser.getEmail());
        userResponseDTO.setToken(token);
        userResponseDTO.setIsActive(true);
        userResponseDTO.setLastLogin(LocalDateTime.now());

        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/status")
    public String getStatus() {
        return "El servidor está corriendo correctamente";
    }

}