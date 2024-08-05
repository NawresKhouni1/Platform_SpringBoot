package com.twd.SpringSecurityJWT.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twd.SpringSecurityJWT.entity.Students;

@Repository
public interface StudentRepo extends JpaRepository<Students, Long>  {
    List<Students> findByNiveau(String niveau);
    Optional<Students> findById(Integer id);
    List<Students> findByArchived(boolean archived);
    Optional<Students> findByFirstName(String firstName);






}