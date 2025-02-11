package com.rungroup.SpringMVCPt1.controller;

import com.rungroup.SpringMVCPt1.dto.ClubDto;
import com.rungroup.SpringMVCPt1.dto.EventDto;
import com.rungroup.SpringMVCPt1.models.Event;
import com.rungroup.SpringMVCPt1.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    //GET endpoint to display a specific event's details
    @GetMapping("/events/{eventId}")
    public String eventDetails(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);

        return "events-detail";
    }

    //GET endpoint to show a "create event" form to the user
    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();

        model.addAttribute("event", event);
        model.addAttribute("clubId", clubId);

        return "events-create";
    }

    //POST endpoint to save an event to the event table
    @PostMapping("events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute("event") EventDto eventDto, BindingResult result) {
        if (result.hasErrors()) {
            return "events-create";
        }

        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    //Create a GET endpoint that returns the form to edit an event
    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findEventById(eventId);

        model.addAttribute("event", eventDto);

        return "events-edit";
    }

    //Create a POST endpoint that will update an event by its id
    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId, @Valid @ModelAttribute("event") EventDto eventDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "events-edit";
        }
        //Just in case
        EventDto eventDtoCopy = eventService.findEventById(eventId);
        eventDto.setId(eventId);
        eventDto.setCreatedOn(eventDtoCopy.getCreatedOn());
        eventDto.setClub(eventDtoCopy.getClub());

        eventService.updateEvent(eventDto);

        return "redirect:/events";
    }
}
