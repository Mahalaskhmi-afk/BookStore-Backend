package com.example.BookShop.Repositories;

import com.example.BookShop.Entities.Dto.UserDto;
import com.example.BookShop.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
}
