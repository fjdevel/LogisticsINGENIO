package com.ingenio.logisticIngenio.repositories;

import com.ingenio.logisticIngenio.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

    boolean existsByUsername(String username);

    AppUser findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

}