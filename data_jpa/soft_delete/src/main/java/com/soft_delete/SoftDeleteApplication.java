package com.soft_delete;

import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoftDeleteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftDeleteApplication.class, args);
    }

    @Bean
    CommandLineRunner startUp(AccountRepository accountRepository, ActiveAccountRepository activeAccountRepository) {
        return arg -> {
            val account = new Account("Vasia");
            System.out.println(accountRepository.save(account));
            // Account(id=1, name=Vasia)

            val accountActive = new ActiveAccount("Active Vasia");
            System.out.println(activeAccountRepository.save(accountActive));
            // ActiveAccount(id=1, name=Active Vasia)

            val getAccountFromDB = accountRepository.findById(account.getId()).get();
            accountRepository.deleteById(getAccountFromDB.getId());

            val getActiveAccountFromDB = activeAccountRepository.findById(account.getId()).get();
            activeAccountRepository.deleteById(getActiveAccountFromDB.getId());
        };
    }
}
