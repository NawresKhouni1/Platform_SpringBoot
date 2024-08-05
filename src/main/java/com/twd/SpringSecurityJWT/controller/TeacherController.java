package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.entity.Teachers;
import com.twd.SpringSecurityJWT.repository.TeacherRepo;
import com.twd.SpringSecurityJWT.services.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // Get all teachers
    @GetMapping("/get-all-teachers")
    public ResponseEntity<List<Teachers>> getAllTeachers() {
        List<Teachers> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    // Get teacher by ID
    @GetMapping("/get/teacher-by-id/{id}")
    public ResponseEntity<Teachers> getTeacherById(@PathVariable Long id) {
        Optional<Teachers> teacher = teacherService.getTeacherById(id);
        if (teacher.isPresent()) {
            return ResponseEntity.ok(teacher.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Save teacher
    @PostMapping("/add-teacher")
    public ResponseEntity<Teachers> saveTeacher(@RequestBody Teachers teacher) {
        Teachers savedTeacher = teacherService.saveTeacher(teacher);
        return ResponseEntity.ok(savedTeacher);
    }

    // Update teacher
    @PutMapping("/edit-teacher/{id}")
    public ResponseEntity<Teachers> updateTeacher(@PathVariable Long id, @RequestBody Teachers teacher) {
        Teachers updatedTeacher = teacherService.updateTeacher(id, teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    // Delete teacher
    @DeleteMapping("/delete-teacher/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().build();
    }
}
