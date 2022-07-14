package bwa.service;

import bwa.model.Subject;
import bwa.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Transactional
    public Subject save (Subject subject){
        return subjectRepository.save(subject);
    }

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    @Transactional
    public void deleteSubject(long id){
        subjectRepository.deleteById(id);
    }

    public List<Subject> findByDate(LocalDate date){
        return subjectRepository.findByDate(date);
    }
}
