package com.rungroup.SpringMVCPt1.mapper;

import com.rungroup.SpringMVCPt1.dto.EventDto;
import com.rungroup.SpringMVCPt1.models.Event;

public class EventMapper {
    public static Event mapToEvent(EventDto eventDto) {
        Event event = Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .startDate(eventDto.getStartDate())
                .endDate(eventDto.getEndDate())
                .type(eventDto.getType())
                .photoURL(eventDto.getPhotoURL())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .build();

        return event;
    }

    public static EventDto mapToEventDto(Event event) {
        EventDto eventDto = EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .type(event.getType())
                .photoURL(event.getPhotoURL())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .build();

        return eventDto;
    }
}
