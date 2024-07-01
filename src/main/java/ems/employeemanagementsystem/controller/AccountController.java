package ems.employeemanagementsystem.controller;

import ems.employeemanagementsystem.entity.Account;
import ems.employeemanagementsystem.entity.RegistrationRequest;
import ems.employeemanagementsystem.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody RegistrationRequest request) {

        return ResponseEntity.ok(accountService.register(request));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<Account> registerAdmin(@RequestBody RegistrationRequest request){

        return ResponseEntity.ok(accountService.registerAdmin(request));
    }
}
