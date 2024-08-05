package com.twd.SpringSecurityJWT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twd.SpringSecurityJWT.entity.Courses;
import com.twd.SpringSecurityJWT.services.CoursesService;

@RestController
@RequestMapping("/admin")
public class CourseController {

    @Autowired
    private CoursesService coursesService;

    @GetMapping("get-all-courses")
    public ResponseEntity<List<Courses>> getAllCourses() {
        List<Courses> courses = coursesService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/get-course-By-Id/{id}")
    public ResponseEntity<Courses> getCourseById(@PathVariable Long id) {
        Courses course = coursesService.getCourseById(id);
        return new ResponseEntity<>(course, course != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-course")
    public ResponseEntity<Courses> addCourse(@RequestBody Courses course) {
        Courses newCourse = coursesService.addCourse(course);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @PutMapping("/edit-course-By-Id/{id}")
    public ResponseEntity<Courses> updateCourse(@PathVariable Long id, @RequestBody Courses courseDetails) {
        Courses updatedCourse = coursesService.updateCourse(id, courseDetails);
        return new ResponseEntity<>(updatedCourse, updatedCourse != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-course/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        coursesService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

