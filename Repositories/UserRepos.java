package com.app.demo.Repositories;

import com.app.demo.Tables.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepos extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User> {
}
