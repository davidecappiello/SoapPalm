package it.prometeo.Repository;

import it.prometeo.Entity.MessageSegment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageSegmentRepository extends JpaRepository<MessageSegment, Integer> {
}

