package com.twd.SpringSecurityJWT.services;

import com.twd.SpringSecurityJWT.entity.Attendance;
import com.twd.SpringSecurityJWT.entity.Courses;
import com.twd.SpringSecurityJWT.entity.Students;
import com.twd.SpringSecurityJWT.repository.AttendanceRepos;
import com.twd.SpringSecurityJWT.repository.CourseRepo;
import com.twd.SpringSecurityJWT.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepos attendanceRepository;

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private CourseRepo courseRepository;

    public Attendance markAttendance(Long studentId, Long courseId, LocalDate date, boolean isPresent) {
        Students student = studentRepository.findById(studentId).orElse(null);
        Courses course = courseRepository.findById(courseId).orElse(null);

        System.out.println("isPresent value received: " + isPresent);  // Debugging line

        if (student != null && course != null) {
            Attendance attendance = new Attendance();
            attendance.setStudent(student);
            attendance.setCourse(course);
            attendance.setDate(date);
            attendance.setIsPresent(isPresent);

        
            System.out.println("isPresent value set in attendance object: " + attendance.isIsPresent());  // Debugging line

            return attendanceRepository.save(attendance);
        } else {
            return null;
        }
    }

    public List<Attendance> getStudentAttendance(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public List<Attendance> getCourseAttendance(Long courseId) {
        return attendanceRepository.findByCourseId(courseId);
    }

    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByDate(date);
    }

    public List<Attendance> getAttendanceByIsPresent(boolean isPresent) {
        List<Attendance> attendanceList = attendanceRepository.findByIsPresent(isPresent);
        System.out.println("Retrieved attendance list: " + attendanceList);  // Debugging line
        return attendanceList;
    }
    
}


