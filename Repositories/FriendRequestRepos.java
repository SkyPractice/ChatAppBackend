package com.app.demo.Repositories;

import com.app.demo.Tables.FriendRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepos extends JpaRepository<FriendRequestEntity, Integer>,
        JpaSpecificationExecutor<FriendRequestEntity> {
}
