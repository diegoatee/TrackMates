package com.rungroup.SpringMVCPt1.service;

import com.rungroup.SpringMVCPt1.dto.EventDto;

import java.util.List;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findEventById(Long eventId);

    void updateEvent(EventDto eventDto);
}
