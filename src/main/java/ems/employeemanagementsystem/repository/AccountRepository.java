package ems.employeemanagementsystem.repository;

import ems.employeemanagementsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for Account entities.
 * Extends JpaRepository to provide CRUD operations and additional methods for Account persistence.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Finds an account by its email address.
     *
     * @param email The email address to search for.
     * @return An Optional containing the found account, or empty if no account is found.
     */
    Optional<Account> findByEmail(String email);
}
