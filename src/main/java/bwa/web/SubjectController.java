package bwa.web;

import bwa.model.Subject;
import bwa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/subjects")
    public String listSubjects (Map<String, Object> model){
        model.put("subjects", subjectService.findAll());
        model.put("newSubject", new Subject());
        return "subjects";
    }

    @PostMapping("/subjects")
    public String addSubject(Subject subject){
        subjectService.save(subject);
        return "redirect:subjects";
    }

    @GetMapping("/deleteSubject/{id}")
    public String deleteSubject(@PathVariable int id){
        //subjectService.findAll().removeIf(e -> e.getId()==id);
        subjectService.deleteSubject(id);
        return "redirect:/subjects";
    }

    @GetMapping("/findSubjectByDate")
    public String findSubjectByDate( @RequestParam(value="date", required = false) LocalDate date){

        //subjectService.findByDate(date);
        List filteredSubjects=subjectService.findByDate(date);
       // model.put("subjects",filteredSubjects);

        return "subjects";
    }
}
