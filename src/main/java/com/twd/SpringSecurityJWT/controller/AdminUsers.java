package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.dto.ReqRes;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.entity.Product;
import com.twd.SpringSecurityJWT.entity.Students;
import com.twd.SpringSecurityJWT.repository.ProductRepo;
import com.twd.SpringSecurityJWT.repository.StudentRepo;
import com.twd.SpringSecurityJWT.services.StudentService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUsers {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentService studentService;


    @GetMapping("/public/product")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok(productRepo.findAll());
    }

    // @PostMapping("/admin/saveproduct")
    // public ResponseEntity<Object> signUp(@RequestBody ReqRes productRequest){
    //     Product productToSave = new Product();
    //     productToSave.setName(productRequest.getName());
    //     return ResponseEntity.ok(productRepo.save(productToSave));
    // }

    @GetMapping("/public/student")
    public ResponseEntity<Object> getAllStudents(){
        return ResponseEntity.ok(studentRepo.findAll());
    }
    @PostMapping("/admin/savestudent")
    public ResponseEntity<Object> signUp(@RequestBody ReqRes studentRequest){
        Students studentToSave = new Students();
        studentToSave.setFirstName(studentRequest.getFirstName());
        // set other properties
        studentToSave.setLastName(studentRequest.getLastName());
        studentToSave.setPhone(studentRequest.getPhone());
        studentToSave.setCin(studentRequest.getCin());
        studentToSave.setNiveau(studentRequest.getNiveau());
        studentToSave.setEmail(studentRequest.getEmail());
        studentToSave.setAdresse(studentRequest.getAdresse());
        return ResponseEntity.ok(studentRepo.save(studentToSave));
    }

    @PutMapping("/admin/editsavestudent/{id}")
    public ResponseEntity<Students> editStudent(@PathVariable Integer id, @RequestBody ReqRes studentRequest) {
        Optional<Students> studentOptional = studentRepo.findById(id);
        if (studentOptional.isPresent()) {
            Students existingStudent = studentOptional.get();
            existingStudent.setFirstName(studentRequest.getFirstName());
            existingStudent.setLastName(studentRequest.getLastName());
            existingStudent.setPhone(studentRequest.getPhone());
            existingStudent.setCin(studentRequest.getCin());
            existingStudent.setNiveau(studentRequest.getNiveau());
            existingStudent.setEmail(studentRequest.getEmail());
            existingStudent.setAdresse(studentRequest.getAdresse());
            
            Students updatedStudent = studentRepo.save(existingStudent);
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    


    
        
    @GetMapping("/admin/bylevel/{niveau}")
    public ResponseEntity<List<Students>> getStudentsByLevel(@PathVariable String niveau) {
        List<Students> students = studentRepo.findByNiveau(niveau);
        return ResponseEntity.ok(students);
    }

    @PutMapping("/admin/archive/{id}")
    public ResponseEntity<Students> archiveStudent(@PathVariable Integer id) {
        Students archivedStudent = studentService.archiveStudent(id);
        return new ResponseEntity<>(archivedStudent, archivedStudent != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/admin/archived")
    public ResponseEntity<List<Students>> getArchivedStudents() {
        List<Students> archivedStudents = studentService.getArchivedStudents();
        return new ResponseEntity<>(archivedStudents, HttpStatus.OK);
    }
    



    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone(){
        return ResponseEntity.ok("USers alone can access this ApI only");
    }

    @GetMapping("/adminuser/both")
    public ResponseEntity<Object> bothAdminaAndUsersApi(){
        return ResponseEntity.ok("Both Admin and Users Can  access the api");
    }

    // /** You can use this to get the details(name,email,role,ip, e.t.c) of user accessing the service*/
    // @GetMapping("/public/email")
    // public String getCurrentUserEmail() {
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     System.out.println(authentication); //get all details(name,email,password,roles e.t.c) of the user
    //     System.out.println(authentication.getDetails()); // get remote ip
    //     System.out.println(authentication.getName()); //returns the email because the email is the unique identifier
    //     return authentication.getName(); // returns the email
    // }
    /** You can use this to get the details (id, name, password, roles, e.t.c) of the user accessing the service */
@GetMapping("/public/userDetails")
public ResponseEntity<Object> getCurrentUserDetails() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    System.out.println(authentication); // Get all details (name, email, password, roles, etc.) of the user
    System.out.println(authentication.getDetails()); // Get remote IP
    System.out.println(authentication.getName()); // Returns the email because the email is the unique identifier
    OurUsers userDetails = (OurUsers) authentication.getPrincipal();
    return ResponseEntity.ok(userDetails); // Returns the authenticated user details
}


   
}
