package com.example.loanApp.repository;

import com.example.loanApp.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo,Long> {
public Optional<UserInfo> findByName(String s);
}
