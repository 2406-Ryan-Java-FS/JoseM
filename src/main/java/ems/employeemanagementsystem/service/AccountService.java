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
/**
 * Service class for managing account related operations such as registration and login.
 */
@AllArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user account.
     *
     * @param request The registration request containing user details.
     * @return The registered Account.
     * @throws DuplicateAccountException if an account with the given email already exists.
     */
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

    /**
     * Registers a new admin account.
     *
     * @param request The registration request containing admin details.
     * @return The registered Account.
     * @throws DuplicateAccountException if an account with the given email already exists.
     */
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

    /**
     * Logs in with an existing account.
     *
     * @param request The account details for login.
     * @return The logged-in Account.
     * @throws AccountNotFoundException if an account with the given email is not found.
     * @throws IllegalArgumentException if the password is incorrect.
     */
    public Account login(Account request){

        Account account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AccountNotFoundException("Account with email " + request.getEmail() + " not found."));

        if(!passwordEncoder.matches(request.getPassword(), account.getPassword())){
            throw new IllegalArgumentException("Invalid email or password.");
        }

        return account;

    }


}
