package com.mirth.prometeo.Repository;

import com.mirth.prometeo.Entity.MessageEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageEventRepository extends JpaRepository<MessageEvent, Integer> {
}
