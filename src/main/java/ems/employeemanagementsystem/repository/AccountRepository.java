package ems.employeemanagementsystem.repository;

import ems.employeemanagementsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
