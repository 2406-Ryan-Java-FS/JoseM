package ems.employeemanagementsystem.service;

import ems.employeemanagementsystem.entity.Account;
import ems.employeemanagementsystem.entity.RegistrationRequest;
import ems.employeemanagementsystem.exception.AccountNotFoundException;
import ems.employeemanagementsystem.exception.DuplicateAccountException;
import ems.employeemanagementsystem.repository.AccountRepository;
import ems.employeemanagementsystem.role.Role;
import ems.employeemanagementsystem.util.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.channels.AcceptPendingException;

@AllArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account register(RegistrationRequest request) {

        if (accountRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateAccountException("Account with email "
                    + request.getEmail() + " already exists.");
        }

        Account account = AccountMapper.toAccount(request);
        account.setRole(Role.ROLE_USER);
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        return accountRepository.save(account);
    }

    public Account registerAdmin(RegistrationRequest request) {

        if (accountRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateAccountException("Account with email "
                    + request.getEmail() + " already exists.");
        }

        Account account = AccountMapper.toAccount(request);
        account.setRole(Role.ROLE_ADMIN);
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        return accountRepository.save(account);
    }

    public Account login(Account request){

        Account account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AccountNotFoundException("Account with email " + request.getEmail() + " not found."));

        if(!passwordEncoder.matches(request.getPassword(), account.getPassword())){
            throw new IllegalArgumentException("Invalid email or password.");
        }

        return account;

    }


}
