package com.app.demo.Repositories;

import com.app.demo.Tables.PublicMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicMsgRepos extends JpaRepository<PublicMessageEntity, Integer>,
        JpaSpecificationExecutor<PublicMessageEntity> {
}
