package com.soft_delete;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveAccountRepository extends JpaRepository<ActiveAccount, Long> {
}
