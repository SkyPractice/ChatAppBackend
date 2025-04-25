package com.app.demo.Repositories;

import com.app.demo.Tables.ChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChannelRepos extends JpaRepository<ChannelEntity, Integer>, JpaSpecificationExecutor<ChannelEntity> {
}
