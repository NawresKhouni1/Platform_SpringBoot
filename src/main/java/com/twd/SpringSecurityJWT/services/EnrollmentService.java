package com.twd.SpringSecurityJWT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twd.SpringSecurityJWT.entity.Courses;
import com.twd.SpringSecurityJWT.entity.Enrollment;
import com.twd.SpringSecurityJWT.entity.Students;
import com.twd.SpringSecurityJWT.repository.CourseRepo;
import com.twd.SpringSecurityJWT.repository.EnrollmentRepository;
import com.twd.SpringSecurityJWT.repository.StudentRepo;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private CourseRepo courseRepository;

    public Enrollment enrollStudent(Long studentId, Long courseId) {
        Students student = studentRepository.findById(studentId).orElse(null);
        Courses course = courseRepository.findById(courseId).orElse(null);

        if (student != null && course != null) {
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            return enrollmentRepository.save(enrollment);
        } else {
            return null;
        }
    }

    public List<Enrollment> getStudentEnrollments(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getCourseEnrollments(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
}
