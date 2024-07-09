package com.mirth.prometeo.Repository;

import com.mirth.prometeo.Entity.EventTimeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MessageEventTimelineRepository extends JpaRepository<EventTimeline, Integer> {
}
