package com.Logiweb.LoginApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Logiweb.LoginApp.Entities.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, Integer> {

	Optional<UserAccount> findByUserEmail(String emailId);

}
