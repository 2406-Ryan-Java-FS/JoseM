package ems.employeemanagementsystem.util;

import ems.employeemanagementsystem.entity.Account;
import ems.employeemanagementsystem.entity.RegistrationRequest;
import ems.employeemanagementsystem.role.Role;

import java.util.ArrayList;


/**
 * Utility class for mapping RegistrationRequest objects to Account entities.
 */
public class AccountMapper {

    /**
     * Converts a RegistrationRequest to an Account entity.
     *
     * @param request The registration request containing user details.
     * @return A new Account entity populated with the details from the registration request.
     */
    public static Account toAccount(RegistrationRequest request){

        Account account = new Account();
        account.setEmail(request.getEmail());
        account.setPassword(request.getPassword());
        account.setRole(Role.ROLE_USER);
        account.setEmployees(new ArrayList<>());
        return account;
    }
}
