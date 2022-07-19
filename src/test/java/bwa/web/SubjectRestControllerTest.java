package bwa.web;

import bwa.model.Subject;
import bwa.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
@AutoConfigureTestDatabase
@DataJpaTest
public class SubjectRestControllerTest {
//
//    @Autowired
//    private SubjectRepository subjectRepository;
//
//
//    @Test
//    public void testCreateSubject() {
//
//        int subjectId = 10;
//        LocalDate dateFrom = LocalDate.of(2022, 11, 11);
//        LocalDate dateTo = LocalDate.of(222, 12, 12);
//        double bodyWeight = 71;
//
//        Subject subject = new Subject(subjectId, dateFrom, dateTo, (int) bodyWeight);
//
//        subjectRepository.save(subject);
//    }
}