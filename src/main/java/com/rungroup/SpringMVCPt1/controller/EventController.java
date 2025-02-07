package com.rungroup.SpringMVCPt1.controller;

import com.rungroup.SpringMVCPt1.dto.ClubDto;
import com.rungroup.SpringMVCPt1.dto.EventDto;
import com.rungroup.SpringMVCPt1.models.Event;
import com.rungroup.SpringMVCPt1.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //GET endpoint to display a page containing all events
    @GetMapping("/events")
    public String listEvents(Model model) {
        //Get a list of all events from the repository
        List<EventDto> events = eventService.findAllEvents();

        model.addAttribute("events", events);

        return "events-list";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();

        model.addAttribute("event", event);
        model.addAttribute("clubId", clubId);

        return "events-create";
    }

    @PostMapping("events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto eventDto, Model model) {
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }
}
