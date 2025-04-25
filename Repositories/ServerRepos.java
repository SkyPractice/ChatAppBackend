package com.app.demo.Repositories;

import com.app.demo.Tables.ServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServerRepos extends JpaRepository<ServerEntity, Integer>, JpaSpecificationExecutor<ServerEntity> {
}
