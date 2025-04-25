package com.app.demo.Repositories;

import com.app.demo.Tables.ChannelMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChannelMsgRepos extends JpaRepository<ChannelMessageEntity, Integer>,
        JpaSpecificationExecutor<ChannelMessageEntity> {
}
