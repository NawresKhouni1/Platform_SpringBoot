package com.twd.SpringSecurityJWT.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

import com.twd.SpringSecurityJWT.dto.EventRequest;
import com.twd.SpringSecurityJWT.entity.Event;
import com.twd.SpringSecurityJWT.services.EventService;

@RestController
@RequestMapping("/admin")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create-event")
    public ResponseEntity<Event> createEvent(@RequestBody EventRequest eventRequest) {
        Event createdEvent = eventService.createEvent(eventRequest);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }
   


    @PutMapping("/update-event/{eventId}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long eventId,
            @RequestBody EventRequest eventRequest) {
        Event event = eventService.updateEvent(eventId, eventRequest);
        return new ResponseEntity<>(event, 
            event != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/delete-event/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get-event/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable Long eventId) {
        Event event = eventService.getEvent(eventId);
        return new ResponseEntity<>(event, 
            event != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @GetMapping("/get-all-events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> eventList = eventService.getAllEvents();
        return new ResponseEntity<>(eventList, HttpStatus.OK);
}


    @GetMapping("/get-events-by-date")
    public ResponseEntity<List<Event>> getEventsByDate(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
    
        List<Event> events = eventService.getEventsByDate(startDate.toLocalDate(), endDate.toLocalDate());
        return new ResponseEntity<>(events, HttpStatus.OK);
            }
    }





