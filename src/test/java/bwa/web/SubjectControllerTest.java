package bwa.web;

import bwa.model.Subject;
import bwa.service.SubjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubjectControllerTest {

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private SubjectController subjectController;

    private int subjectId = 10;
    private LocalDate dateFrom = LocalDate.of(2022, 11, 11);
    private LocalDate dateTo = LocalDate.of(2022, 12, 12);
    private double bodyWeight = 71;

    @Test
    public void whenCreateSubject_shouldSave() {

        Subject subject = createSubject();
        subjectController.addSubject(subject);
        assertNotNull(subject);
        assertEquals(subjectId, subject.getSubjectId());
    }

    @Test
    public void sholudReturn_allSubjects() {

        subjectController.addSubject(createSubject());
        subjectController.addSubject(new Subject(11, LocalDate.of(2021, 12, 12), LocalDate.of(2011, 12, 21), 71));
        Map<String, Object> model = new HashMap();
        List<String> subjects = Collections.singletonList(subjectController.listSubjects(model));

        String expected = subjectController.listSubjects(model);

        assertThat(model.size()).isEqualTo(2);
        assertThat(expected).isNotNull();
        assertThat(subjects.size()).isEqualTo(1);
    }

    @Test
    public void whenGivenId_shouldDeleteSubject_ifExists() {
        Subject subject = createSubject();
        subject.setId(1);
        subjectController.addSubject(subject);

        subjectController.deleteSubject((int) subject.getId());
        assertThat(subjectService.findAll()).isEmpty();
        assertThat(subjectService.findSubjectById(1)).isEmpty();
    }

    @Test
    public void whenGivenId_shouldUpdateSubject() {
        Map<String, Object> model = new HashMap();
        Subject subject = createSubject();
        subjectController.updateSubject(subject);

        List<String> subjects = Collections.singletonList(subjectController.listSubjects(model));
        assertThat(subjects.get(0).contentEquals(subject.toString()));
    }

    @Test
    public void whenGivenDate_ShouldFindByDate() {
        LocalDate date = LocalDate.of(2022, 11, 17);
        subjectController.addSubject(createSubject());
        Map<String, Object> model = new HashMap();

        List<String> subjects = Collections.singletonList(subjectController.findSubjectByDate(date, model));

        assertThat(model.size() > 0);
        assertThat(!subjects.isEmpty());
    }

    public Subject createSubject(){
        return new Subject(subjectId, dateFrom, dateTo, (int) bodyWeight);
    }
}