package com.hcc.controllers;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    AssignmentService assignmentService;

    //TODO: Get assignment by User
    @GetMapping
    ResponseEntity<Set<Assignment>> getAssignmentByUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(assignmentService.findAssignmentByUser(user), HttpStatus.OK);
    }
    //TODO: Get assignment by id
    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(assignmentService.findAssignmentById(id), HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Todo: Update Assignment by id
    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@RequestBody Assignment assignment,
                                                       @PathVariable Long id,
                                                       @AuthenticationPrincipal User user) {
        Assignment assignments = assignmentService.findAssignmentById(id);
        if (assignments == null){
            return ResponseEntity.notFound().build();
        }

        assignments.setId(id);
        assignmentService.saveUpdatedAssignment(assignment);
        return ResponseEntity.ok(assignments);
    }

    //TODO: New Assignment
    @PostMapping
    public ResponseEntity<Assignment> postAssignment(@AuthenticationPrincipal User user, @RequestBody List<String> fields) {
        Assignment assignment = assignmentService.saveAssignment(user);
        return new ResponseEntity<>(assignment, HttpStatus.OK);
    }


    //TODO: Delete Assignment by id
    @DeleteMapping("/{id}")
    public void deleteAssignmentId(@PathVariable Long id, @AuthenticationPrincipal User user) {
        assignmentService.deleteAssignmentById(id);
    }

}
