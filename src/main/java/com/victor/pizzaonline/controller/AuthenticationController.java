package com.victor.pizzaonline.controller;

import com.victor.pizzaonline.domain.address.Address;
import com.victor.pizzaonline.domain.cliente.AuthenticationDTO;
import com.victor.pizzaonline.domain.cliente.Cliente;
import com.victor.pizzaonline.domain.cliente.LoginResponseDTO;
import com.victor.pizzaonline.domain.cliente.RegisterDTO;
import com.victor.pizzaonline.infra.security.TokenService;
import com.victor.pizzaonline.repository.AddressRepository;
import com.victor.pizzaonline.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Cliente) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        if (this.clienteRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        Address address = new Address(
                data.address().getCep(),
                data.address().getState(),
                data.address().getCity(),
                data.address().getNeighborhood(),
                data.address().getStreet()
        );
        addressRepository.save(address);

        Cliente cliente = new Cliente(data.name(), data.phoneNumber(), data.email(), encryptedPassword, data.role(), address);

        this.clienteRepository.save(cliente);

        return ResponseEntity.ok().body(cliente);
    }
}
