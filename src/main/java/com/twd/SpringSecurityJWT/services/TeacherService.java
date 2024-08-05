package com.twd.SpringSecurityJWT.services;

import com.twd.SpringSecurityJWT.entity.Teachers;
import com.twd.SpringSecurityJWT.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    // Get all teachers
    public List<Teachers> getAllTeachers() {
        return teacherRepo.findAll();
    }

    // Get teacher by ID
    public Optional<Teachers> getTeacherById(Long id) {
        return teacherRepo.findById(id);
    }

    // Save teacher
    public Teachers saveTeacher(Teachers teacher) {
        return teacherRepo.save(teacher);
    }

    // Update teacher
    public Teachers updateTeacher(Long id, Teachers teacherDetails) {
        Teachers teacher = teacherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found for id: " + id));
        
        teacher.setFirstName(teacherDetails.getFirstName());
        teacher.setLastName(teacherDetails.getLastName());
        teacher.setEmail(teacherDetails.getEmail());
        teacher.setPhone(teacherDetails.getPhone());
        teacher.setAddress(teacherDetails.getAddress());

        return teacherRepo.save(teacher);
    }

    // Delete teacher
    public void deleteTeacher(Long id) {
        teacherRepo.deleteById(id);
    }
}
