package com.twd.SpringSecurityJWT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twd.SpringSecurityJWT.entity.Enrollment;
import com.twd.SpringSecurityJWT.services.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/{studentId}/{courseId}")
    public ResponseEntity<Enrollment> enrollStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        return new ResponseEntity<>(enrollment, enrollment != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getStudentEnrollments(@PathVariable Long studentId) {
        List<Enrollment> enrollments = enrollmentService.getStudentEnrollments(studentId);
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getCourseEnrollments(@PathVariable Long courseId) {
        List<Enrollment> enrollments = enrollmentService.getCourseEnrollments(courseId);
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }
}

