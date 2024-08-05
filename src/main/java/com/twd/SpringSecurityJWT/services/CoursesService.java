package com.twd.SpringSecurityJWT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twd.SpringSecurityJWT.entity.Courses;
import com.twd.SpringSecurityJWT.repository.CourseRepo;

@Service
public class CoursesService {

    @Autowired
    private CourseRepo courseRepository;

    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }

    public Courses getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Courses addCourse(Courses course) {
        return courseRepository.save(course);
    }

    public Courses updateCourse(Long id, Courses courseDetails) {
        Courses course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            course.setName(courseDetails.getName());
            course.setDescription(courseDetails.getDescription());
            course.setSchedule(courseDetails.getSchedule());
            return courseRepository.save(course);
        }
        return null;
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}
