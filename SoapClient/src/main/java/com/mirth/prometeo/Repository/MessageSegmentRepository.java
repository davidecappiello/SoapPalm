package com.mirth.prometeo.Repository;

import com.mirth.prometeo.Entity.MessageSegment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageSegmentRepository extends JpaRepository<MessageSegment, Integer> {
}

