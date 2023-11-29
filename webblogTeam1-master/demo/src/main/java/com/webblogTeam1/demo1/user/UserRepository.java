package com.webblogTeam1.demo1.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<BlogUser, Long> {
    Optional<BlogUser> findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
    Optional<BlogUser> findByUsername(String username);
}
