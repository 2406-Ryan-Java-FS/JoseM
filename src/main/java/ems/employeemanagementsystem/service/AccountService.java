package ems.employeemanagementsystem.service;

import ems.employeemanagementsystem.entity.Account;
import ems.employeemanagementsystem.entity.RegistrationRequest;
import ems.employeemanagementsystem.exception.DuplicateAccountException;
import ems.employeemanagementsystem.repository.AccountRepository;
import ems.employeemanagementsystem.role.Role;
import ems.employeemanagementsystem.util.AccountMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public Account register(RegistrationRequest request) {

        if (accountRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateAccountException("Account with email "
                    + request.getEmail() + " already exists.");
        }

        Account account = AccountMapper.toAccount(request);
        account.setRole(Role.ROLE_USER);

        return accountRepository.save(account);
    }


}
