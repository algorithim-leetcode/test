package com.toy.truffle.user.repository;

import com.toy.truffle.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
