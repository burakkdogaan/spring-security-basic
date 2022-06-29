package com.example.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User1, Long> {

    User1 findByUsername(String username);


}
