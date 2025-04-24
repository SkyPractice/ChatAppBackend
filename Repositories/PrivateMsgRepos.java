package com.app.demo.Repositories;

import com.app.demo.Tables.PrivateMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateMsgRepos extends JpaRepository<PrivateMessageEntity, Integer>,
        JpaSpecificationExecutor<PrivateMessageEntity> {
}
