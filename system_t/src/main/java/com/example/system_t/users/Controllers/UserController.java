package com.example.system_t.users.Controllers;


import com.example.system_t.exception.ApiErrorException;
import com.example.system_t.users.Dtos.AuthenticationDTO;
import com.example.system_t.users.Dtos.LoginResponseDTO;
import com.example.system_t.users.Dtos.RegisterDTO;
import com.example.system_t.users.Repositorys.UserRepository;
import com.example.system_t.users.User;
import com.example.system_t.users.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            if (token != null) {
                return ResponseEntity.ok(new LoginResponseDTO(token));
            } else {
                throw new ApiErrorException("Token can't be null");
            }
        } catch (ApiErrorException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login not authorized");
        }
    }


        @PostMapping("/register")
        public ResponseEntity register (@RequestBody @Valid RegisterDTO data){
            if (this.repository.findByLogin(data.login()) != null) throw new ApiErrorException("User can't be null");
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.login(), encryptedPassword, data.role());

            this.repository.save(newUser);
            throw new ApiErrorException("Register successfully");
        }
    }



