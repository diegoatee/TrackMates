package com.rungroup.SpringMVCPt1.service.impl;

import com.rungroup.SpringMVCPt1.dto.EventDto;
import com.rungroup.SpringMVCPt1.models.Club;
import com.rungroup.SpringMVCPt1.models.Event;
import com.rungroup.SpringMVCPt1.repository.ClubRepository;
import com.rungroup.SpringMVCPt1.repository.EventRepository;
import com.rungroup.SpringMVCPt1.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(ClubRepository clubRepository, EventRepository eventRepository) {
        this.clubRepository = clubRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);

        eventRepository.save(event);
    }

    private Event mapToEvent(EventDto eventDto) {
        Event event = Event.builder()
                        .id(eventDto.getId())
                        .name(eventDto.getName())
                        .startDate(eventDto.getStartDate())
                        .endDate(eventDto.getEndDate())
                        .type(eventDto.getType())
                        .photoURL(eventDto.getPhotoURL())
                        .createdOn(eventDto.getCreatedOn())
                        .updatedOn(eventDto.getUpdatedOn())
                        .club(null)
                        .build();

        return event;
    }
}
