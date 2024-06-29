package ems.employeemanagementsystem.util;

import ems.employeemanagementsystem.entity.Account;
import ems.employeemanagementsystem.entity.RegistrationRequest;
import ems.employeemanagementsystem.role.Role;

import java.util.ArrayList;

public class AccountMapper {

    public static Account toAccount(RegistrationRequest request){

        Account account = new Account();
        account.setEmail(request.getEmail());
        account.setPassword(request.getPassword());
        account.setRole(Role.ROLE_USER);
        account.setEmployees(new ArrayList<>());
        return account;
    }
}
