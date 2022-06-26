package io.github.choijy.example.security.account;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description : Account Repository class.
 *
 * Created by jychoi on 2022/06/27.
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findByUsername(String username);
}
