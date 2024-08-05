package com.twd.SpringSecurityJWT.repository;

import com.twd.SpringSecurityJWT.entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teachers, Long> {
}
