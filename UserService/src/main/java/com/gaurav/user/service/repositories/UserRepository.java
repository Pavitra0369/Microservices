package com.gaurav.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaurav.user.service.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
