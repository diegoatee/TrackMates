package com.rungroup.SpringMVCPt1.service.impl;

import com.rungroup.SpringMVCPt1.dto.EventDto;
import com.rungroup.SpringMVCPt1.mapper.EventMapper;
import com.rungroup.SpringMVCPt1.models.Club;
import com.rungroup.SpringMVCPt1.models.Event;
import com.rungroup.SpringMVCPt1.repository.ClubRepository;
import com.rungroup.SpringMVCPt1.repository.EventRepository;
import com.rungroup.SpringMVCPt1.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Event event = EventMapper.mapToEvent(eventDto);
        event.setClub(club);

        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();

        return events.stream().map((event) -> EventMapper.mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findEventById(Long eventId) {
        Event event = eventRepository.findById(eventId).get();

        return EventMapper.mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = EventMapper.mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
