package com.twd.SpringSecurityJWT.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twd.SpringSecurityJWT.dto.EventRequest;
import com.twd.SpringSecurityJWT.entity.Event;
import com.twd.SpringSecurityJWT.repository.EnrollmentRepository;
import com.twd.SpringSecurityJWT.repository.EventRepo;


@Service
public class EventService {

    @Autowired
    private EventRepo eventRepository;

    public Event createEvent(EventRequest eventRequest) {
        Event event = new Event();
        event.setTitle(eventRequest.getTitle());
        event.setDescription(eventRequest.getDescription());
        event.setStartDate(eventRequest.getStartDate());
        event.setEndDate(eventRequest.getEndDate());
        
        return eventRepository.save(event);
    }

    public Event updateEvent(Long eventId, EventRequest eventRequest) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            event.setTitle(eventRequest.getTitle());
            event.setDescription(eventRequest.getDescription());
            event.setStartDate(eventRequest.getStartDate());
            event.setEndDate(eventRequest.getEndDate());
            return eventRepository.save(event);
        }
        return null;
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public Event getEvent(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByDate(LocalDate startDate, LocalDate endDate) {
        return eventRepository.findByStartDateBetween(startDate, endDate);
    }
}

