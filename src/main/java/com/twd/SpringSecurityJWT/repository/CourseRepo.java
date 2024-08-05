package com.twd.SpringSecurityJWT.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.twd.SpringSecurityJWT.entity.Courses;

public interface CourseRepo extends JpaRepository<Courses, Long>{


}
