package com.twd.SpringSecurityJWT.repository;

import com.twd.SpringSecurityJWT.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepos extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long studentId);
    List<Attendance> findByCourseId(Long courseId);
    List<Attendance> findByDate(LocalDate date);
    List<Attendance> findByIsPresent(boolean isPresent);

}
