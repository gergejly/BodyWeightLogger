package bwa.web;

import bwa.model.Subject;
import bwa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectRestController {

    @Autowired
    SubjectService subjectService;

    @GetMapping
    public List <Subject> getAll(){
        List<Subject> subjects=subjectService.findAll();
        return subjects;
    }

    @GetMapping("/{date}")
    public List <Subject> findByDate( @RequestParam(value = "date") LocalDate date){ //@PathVariable LocalDate date,
       List<Subject> subjects = subjectService.findByDate(date);
        return subjects;
    }
}
