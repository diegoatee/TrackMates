package com.rungroup.SpringMVCPt1.repository;

import com.rungroup.SpringMVCPt1.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
