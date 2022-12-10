package com.soprasteria.springbatchCSVfileupload2.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.springbatchCSVfileupload2.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
