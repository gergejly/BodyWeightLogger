package bwa.web;


import bwa.model.Subject;
import bwa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/subjects")
public class SubjectRestController {

    @Autowired
    SubjectService subjectService;


    @GetMapping
    public List<Subject> getAll() {
        return subjectService.findAll();
    }

    @GetMapping("/{id}")
    public Subject getById(@PathVariable long id) {
        return subjectService.findSubjectById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectService.save(subject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> modifySubject(@PathVariable long id, @RequestBody Subject subject) {
        subject.setId(id);
        try {
            Subject savedSubject = subjectService.updateSubject(subject);
            return ResponseEntity.ok(savedSubject);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable long id) {
        subjectService.deleteSubject(id);
    }
}
