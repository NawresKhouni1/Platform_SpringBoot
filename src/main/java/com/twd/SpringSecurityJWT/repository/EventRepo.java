package com.twd.SpringSecurityJWT.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurityJWT.entity.Event;

public interface EventRepo extends JpaRepository<Event, Long>{
    Optional<Event> findById(Long id);
    List<Event> findByStartDateBetween(LocalDate startDate, LocalDate endDate);



}
