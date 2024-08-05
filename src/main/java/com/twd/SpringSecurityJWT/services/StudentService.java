package com.twd.SpringSecurityJWT.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twd.SpringSecurityJWT.entity.Students;
import com.twd.SpringSecurityJWT.repository.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Students updateStudent(Students student) {
        return studentRepo.save(student);
    }

    public Students archiveStudent(Integer id) {
        Students student = studentRepo.findById(id).orElse(null);
        if (student != null) {
            student.setArchived(true);
            return studentRepo.save(student);
        }
        return null;
    }

    public List<Students> getArchivedStudents() {
        return studentRepo.findByArchived(true);
    }
}
