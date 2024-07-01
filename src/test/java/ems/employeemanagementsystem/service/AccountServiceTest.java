package ems.employeemanagementsystem.service;

import ems.employeemanagementsystem.entity.Account;
import ems.employeemanagementsystem.entity.RegistrationRequest;
import ems.employeemanagementsystem.exception.DuplicateAccountException;
import ems.employeemanagementsystem.repository.AccountRepository;
import ems.employeemanagementsystem.role.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void registerShouldThrowDuplicateAccountExceptionWhenEmailAlreadyExists(){
        RegistrationRequest request = new RegistrationRequest("existing@example.com", "password");

        when(accountRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new Account()));

        assertThrows(DuplicateAccountException.class, () -> accountService.register(request));

        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    public void registerShouldSaveValidAccount(){

        RegistrationRequest request = new RegistrationRequest("new@example.com", "password");
        Account account = new Account("new@example.com", "encodedPassword", Role.ROLE_USER, null);

        when(accountRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account savedAccount = accountService.register(request);

        assertNotNull(savedAccount);
        assertEquals("new@example.com", savedAccount.getEmail());
        assertEquals("encodedPassword", savedAccount.getPassword());
        assertEquals(Role.ROLE_USER, savedAccount.getRole());

        verify(accountRepository).save(any(Account.class));
    }

}
