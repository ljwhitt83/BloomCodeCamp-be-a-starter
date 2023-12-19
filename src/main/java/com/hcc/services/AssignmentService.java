package com.hcc.services;

import com.hcc.entities.Assignment;
import com.hcc.entities.User;
import com.hcc.enums.AssignmentStatusEnum;
import com.hcc.enums.AuthorityEnum;
import com.hcc.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;


    //TODO: find assignment by user
    public Set<Assignment> findAssignmentByUser(User user) {
        boolean hasReviewerRole = user.getAuthorities().stream().
                anyMatch(auth -> AuthorityEnum.CODE_REVIEWER_ROLE.name()
                        .equals(auth.getAuthority()));
        if (hasReviewerRole) {
            return  assignmentRepository.findAssignmentsByCodeReview(user);
        }
        else {
            return assignmentRepository.findAssignmentsByUser(user);
        }
    }

    //TODO: find assignment by id
    public Assignment findAssignmentById(Long id) {
        return assignmentRepository.findById(id)
                .orElse(new Assignment());
    }

    //TODO: save assignment
    public Assignment saveAssignment(User user) {

        Assignment assignment = new Assignment();
        assignment.setUser(user);
        assignment.setStatus(AssignmentStatusEnum.IN_PROGRESS.getAssignmentStatusMessage());
        assignment.setNumber(nextAssignmentToSubmit(user));

        return assignmentRepository.save(assignment);
    }

    public Assignment saveUpdatedAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }
    //TODO: edit assignment
    public Assignment editAssignment(User user, Long id, List<String> fields) {
        if (user.getAuthorities().contains(AuthorityEnum.CODE_REVIEWER_ROLE.name())) {
            Assignment assignment = assignmentRepository.getReferenceById(id);
            assignment.setCodeReview(user);
            assignment.setStatus(fields.get(0));
            assignment.setReviewVidueoUrl(fields.get(1));
            return  assignmentRepository.save(assignment);
        }
        else{
            Assignment assignment = assignmentRepository.getReferenceById(id);
            assignment.setStatus(AssignmentStatusEnum.SUBMITTED.name());
            assignment.setGithubUrl(fields.get(0));
            assignment.setBranch(fields.get(1));
            return assignmentRepository.save(assignment);
        }
    }
    //TODO: delete by id
    public void deleteAssignmentById(Long id) {
        assignmentRepository.deleteById(id);
    }

    private Integer nextAssignmentToSubmit(User user) {

        Set<Assignment> assignmentSet = assignmentRepository.findAssignmentsByUser(user);

        if (assignmentSet == null) {
            return 1;
        }

        Optional<Integer> sortedAssignment = assignmentSet.stream()
                .sorted((a1, a2) -> {
                    return Integer.valueOf(a2.getNumber()).compareTo(Integer.valueOf(a1.getNumber()));
                })
                .map(assignment -> assignment.getNumber() + 1)
                .findFirst();
        return sortedAssignment.orElse(1);
    }

}
