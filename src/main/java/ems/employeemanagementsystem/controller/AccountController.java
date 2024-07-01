package ems.employeemanagementsystem.controller;

import ems.employeemanagementsystem.entity.Account;
import ems.employeemanagementsystem.entity.RegistrationRequest;
import ems.employeemanagementsystem.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing account related operations such as registration and login.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    /**
     * Endpoint for registering a new user account.
     *
     * @param request The registration request containing user details (email and password).
     * @return A ResponseEntity containing the registered Account.
     */
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody RegistrationRequest request) {

        return ResponseEntity.ok(accountService.register(request));
    }

    /**
     * Endpoint for registering a new admin account.
     *
     * @param request The registration request containing admin details (email and password).
     * @return A ResponseEntity containing the registered Account.
     */
    @PostMapping("/register/admin")
    public ResponseEntity<Account> registerAdmin(@RequestBody RegistrationRequest request){

        return ResponseEntity.ok(accountService.registerAdmin(request));
    }

    /**
     * Endpoint for logging in with an existing account.
     *
     * @param account The account details for login.
     * @return A ResponseEntity containing the logged-in Account.
     */

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account){

        return ResponseEntity.ok(accountService.login(account));
    }
}
