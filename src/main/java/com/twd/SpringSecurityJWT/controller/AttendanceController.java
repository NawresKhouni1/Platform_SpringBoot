package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.entity.Attendance;
import com.twd.SpringSecurityJWT.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/add-attendance")
    public ResponseEntity<Attendance> markAttendance(
            @RequestParam Long studentId,
            @RequestParam Long courseId,
            @RequestBody AttendanceRequest request
    ) {
        Attendance attendance = attendanceService.markAttendance(studentId, courseId, request.getDate(), request.isPresent());
        return new ResponseEntity<>(attendance, attendance != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-student-attendance/{studentId}")
    public ResponseEntity<List<Attendance>> getStudentAttendance(@PathVariable Long studentId) {
        List<Attendance> attendanceList = attendanceService.getStudentAttendance(studentId);
        return new ResponseEntity<>(attendanceList, HttpStatus.OK);
    }

    @GetMapping("/get-course-attendance/{courseId}")
    public ResponseEntity<List<Attendance>> getCourseAttendance(@PathVariable Long courseId) {
        List<Attendance> attendanceList = attendanceService.getCourseAttendance(courseId);
        return new ResponseEntity<>(attendanceList, HttpStatus.OK);
    }

    @GetMapping("/get-attendance-by-date")
    public ResponseEntity<List<Attendance>> getAttendanceByDate(@RequestParam LocalDate date) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByDate(date);
        return new ResponseEntity<>(attendanceList, HttpStatus.OK);
    }

    @GetMapping("/get-attendance-by-isPresent")
    public ResponseEntity<List<Attendance>> getAttendanceByIsPresent(@RequestParam boolean isPresent) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByIsPresent(isPresent);
        return new ResponseEntity<>(attendanceList, HttpStatus.OK);
}


    public static class AttendanceRequest {
        private LocalDate date;
        private boolean isPresent;

        // Getters and setters

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public boolean isPresent() {
            return isPresent;
        }

        public void setPresent(boolean present) {
            isPresent = present;
        }
    }
}
