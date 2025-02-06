package com.rungroup.SpringMVCPt1.service;

import com.rungroup.SpringMVCPt1.dto.EventDto;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
}
